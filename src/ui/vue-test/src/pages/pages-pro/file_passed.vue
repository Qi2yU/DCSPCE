
<template>
<div id="file_backed">
<br>
<!-- 筛选 -->
<!-- 按地区选择 -->
<el-form :inline="true" :model="CheckValue" class="demo-form-inline">
  <el-form-item class="myselect" label="选择地区">
        <el-select clearable @change="submit" class="el-select" v-model="values" placeholder="请选择地区">
            <el-option value="0" label="全部" >全部</el-option>
            <el-option value="1" label="昆明市">昆明市</el-option>
            <el-option value="2" label="曲靖市">曲靖市</el-option>
            <el-option value="3" label="玉溪市">玉溪市</el-option>
            <el-option value="4" label="丽江市">丽江市</el-option>
            <el-option value="5" label="普洱市">普洱市</el-option>
            <el-option value="6" label="保山市">保山市</el-option>
            <el-option value="7" label="邵通市">邵通市</el-option>
            <el-option value="8" label="临沧市">临沧市</el-option>
        </el-select>
  </el-form-item>
  <!-- 按时间选择 -->
  <!-- <el-form-item label="选择时间" class="myselect2">
    <el-date-picker format="yyyy-MM-dd" value-format="yyyy-MM-dd" class="el-data-picker" v-model="data1" type="date" placeholder="选择日期"></el-date-picker>
    <h>--</h>
    <el-date-picker format="yyyy-MM-dd" value-format="yyyy-MM-dd" class="el-data-picker" v-model="data2" type="date" placeholder="选择日期"></el-date-picker>
  </el-form-item> -->
  <!-- 按关键字选择 -->
  <!-- <el-form-item class="myselect3" label="关键字">
    <el-input class="el-input" v-model="CheckValue.key" placeholder="企业名称"></el-input>
  </el-form-item> -->
  <!-- 查询按钮 -->
  <el-button type="primary" size="middle" @click="CheckOut()"><h4>查询</h4></el-button>
  <!-- 导出按钮 -->
  <!-- <el-button type="primary" size="middle" @click="Export()"><h4>导出</h4></el-button> -->
  <download-excel
    class="export-excel-wrapper inline-block"
    :data="exportData"
    :fields="json_fields"
    :header="title"
    name="备案审核通过的企业列表.xls">
  <!-- 上面可以自定义自己的样式，还可以引用其他组件button -->
    <el-button type="primary" size="middle" style="margin-left:10px;" @click="Export()"><h4>导出</h4></el-button>
  </download-excel>

</el-form>

<!-- 表格 -->
  <el-table
    :data="tableData"
    :header-cell-style="{'text-align':'center'}"
    :cell-style="{'text-align':'center'}"
    height="500"
    border
    stripe
    style="width: 100%">
    <el-table-column
        prop="seq"
        label="序号"
        width="80">
        <template slot-scope="scope">
	        {{ scope.$index + 1}}
        </template>
    </el-table-column>
    <el-table-column
        prop="company_name"
        label="企业名称"
        width="250">
    </el-table-column>
    <el-table-column
        prop="user_id"
        label="企业账号"
        width="250">
    </el-table-column>
    <el-table-column
        prop="city"
        label="企业地区"
        width="250">
    </el-table-column>
    <el-table-column
        prop="phone"
        label="联系电话"
        width="250">
    </el-table-column>
    <el-table-column
    prop="detail"
    label="具体信息">
      <template slot-scope="scope">
         <el-button type="primary" size="small" @click="CheckDetail(scope.row)">查看</el-button>
      </template>
    </el-table-column>

</el-table>
<router-view></router-view>
</div>

</template>

<style>
.flex-container{
    display:flex;
    justify-content: space-between;
}
.myselect .el-select {
  width: 150px;
}
.myselect2 .el-data-picker {
  width: 150px;
}
.myselect3 .el-input {
  width: 150px;
}
.inline-block {
  display: inline-block;
}
</style>

<script>
import axios from 'axios';

var $this = {};
export default{
    name: 'File_backed',

    created:function () {
      axios.get("http://localhost:8090/company_info/passed").then((response)=>{
        this.tableData = response.data;
        this.exportData = response.data;
      })
    },

data() {
return {

    // 筛选框下拉菜单
    CheckValue:{
        geo:'',
        data1:'',
        date2:'',
        key:'',
        delivery: false,
        type: []
    },
    options: [],
    // 表格
    tableData: [],
    values:'',
    data1:'',
    data2:'',
    exportData:[],
    title:"已通过企业",
    json_fields:{
      "企业名称":"company_name",
      "组织机构代码":"user_id",
      "联系人":"linksman",
      "联系电话":"phone",
      "所属地区":"city",
      "联系地址":"linaddress",
      "邮政编码":"post_num",
      "企业性质":"company_character",
      "所属行业":"company_industry",
      "传真":"fax_num",
      "主要经营业务":"company_business",
      "Email":"email"
    }
}
},

beforeCreate() {
 $this = this;},
methods:{
    CheckOut() {
        console.log('CheckOut!');
        console.log(this.values);
        console.log(this.data1);
        console.log(this.data2);
        if(this.data1=='')
        {
          this.data1=null;
        }
        if(this.data2=='')
        {
          this.data2=null;
        }
        if(this.values=='')
        {
          this.values="0";
          console.log(this.values);
        }
        axios.post("http://localhost:8090/company_info/selectbycity_passed",
      this.values,{'Content-Type': 'text/plain'}).then((response)=>{
        this.tableData = response.data;
        this.exportData = response.data;
      })
      },
    Export(){
        console.log('Export!');
        console.log(this.exportData);
        if(this.exportData.length==0)
        {
          window.alert("暂无数据！");
        }
    },
    CheckDetail(row){
        this.$router.push(
            {path: '/government-pro/file_detail',
             query:{flag: '已通过',
            id:row.user_id}}).catch(error => error);
    },
    submit()
    {
      console.log(this.values)
    }
}
}
</script>
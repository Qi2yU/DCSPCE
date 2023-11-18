
<template>
  <div id="file_backed">
    <br>
<!-- 筛选 -->
<!-- 按地区选择 -->
<el-form :inline="true" :model="CheckValue" class="demo-form-inline">
  <el-form-item class="myselect" label="选择地区">
        <el-select clearable @change="submit" class="el-select" v-model="values" placeholder="请选择地区">
            <el-option value="0" label="全部" >全部</el-option>
            <el-option value="01" label="昆明市">昆明市</el-option>
            <el-option value="02" label="曲靖市">曲靖市</el-option>
            <el-option value="03" label="玉溪市">玉溪市</el-option>
            <el-option value="04" label="丽江市">丽江市</el-option>
            <el-option value="05" label="普洱市">普洱市</el-option>
            <el-option value="06" label="保山市">保山市</el-option>
            <el-option value="07" label="邵通市">邵通市</el-option>
            <el-option value="08" label="临沧市">临沧市</el-option>
            <el-option value="09" label="楚雄彝族自治州">楚雄彝族自治州</el-option>
            <el-option value="10" label="红河哈尼族彝族自治州">红河哈尼族彝族自治州</el-option>
            <el-option value="11" label="文山壮族苗族自治州">文山壮族苗族自治州</el-option>
            <el-option value="12" label="西双版纳傣族自治州">西双版纳傣族自治州</el-option>
            <el-option value="13" label="大理白族自治州">大理白族自治州</el-option>
            <el-option value="14" label="德宏傣族景颇族自治州">德宏傣族景颇族自治州</el-option>
            <el-option value="15" label="怒江傈僳族自治州">怒江傈僳族自治州</el-option>
            <el-option value="16" label="迪庆藏族自治州">迪庆藏族自治州</el-option>
        </el-select>
  </el-form-item>
  <!-- 按时间选择
  <el-form-item label="选择时间" class="myselect2">
    <el-date-picker class="el-data-picker" type="date" placeholder="选择日期" v-model="CheckValue.date1" ></el-date-picker>
    <h>--</h>
    <el-date-picker class="el-data-picker" type="date" placeholder="选择日期" v-model="CheckValue.date2" ></el-date-picker>
  </el-form-item> -->
  <!-- 按关键字选择 -->
  <!-- <el-form-item class="myselect3" label="关键字">
    <el-input class="el-input" v-model="CheckValue.key" placeholder="企业名称"></el-input>
  </el-form-item> -->
  <!-- 查询按钮 -->
  <el-button type="primary" size="middle" @click="CheckOut"><h4>查询</h4></el-button>
  <!-- 导出按钮 -->
  <download-excel
    class="export-excel-wrapper inline-block"
    :data="exportData"
    :fields="json_fields"
    :header="title"
    name="备案待审核的企业列表.xls">
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
     name: 'File_be_checked',

     created:function () {
      axios.get("http://localhost:8090/company_info/be_checked").then((response)=>{
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
    exportData:[],
    title:"待审核企业",
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
 $this = this;

},
methods:{
    CheckOut() {
        console.log('CheckOut!');
        if(this.values=='')
        {
          this.values="0";
          console.log(this.values);
        }
        axios.post("http://localhost:8090/company_info/selectbycity_bechecked",
      this.values,{'Content-Type': 'text/plain'}).then((response)=>{
        this.tableData = response.data;
        this.exportData = response.data;})
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
             query:{flag: '待审核',
            id:row.user_id}}).catch(error => error);
        console.log('CheckDetail!');
    },
    submit()
    {
      console.log(this.values)
    }
}
}
</script>

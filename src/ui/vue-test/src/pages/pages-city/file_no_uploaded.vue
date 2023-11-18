
<template>
<div id="file_backed">
  <br>
<!-- 筛选 -->
<!-- 按地区选择 -->
<el-form :inline="true" :model="CheckValue" class="demo-form-inline">
  <!-- <el-form-item class="myselect" label="选择地区" >
        <el-select @change="submit" class="el-select" v-model="values" placeholder="请选择地区">
            <el-option value="昆明市">昆明市</el-option>
            <el-option value="曲靖市">曲靖市</el-option>
            <el-option value="玉溪市">玉溪市</el-option>
            <el-option value="丽江市">丽江市</el-option>
            <el-option value="普洱市">普洱市</el-option>
            <el-option value="保山市">保山市</el-option>
            <el-option value="邵通市">邵通市</el-option>
            <el-option value="临沧市">临沧市</el-option>

        </el-select>
  </el-form-item>

  <el-form-item label="选择时间" class="myselect2">
    <el-date-picker class="el-data-picker" type="date" placeholder="选择日期" v-model="CheckValue.date1" ></el-date-picker>
    <h>--</h>
    <el-date-picker class="el-data-picker" type="date" placeholder="选择日期" v-model="CheckValue.date2" ></el-date-picker>
  </el-form-item>

  <el-form-item class="myselect3" label="关键字">
    <el-input class="el-input" v-model="CheckValue.key" placeholder="企业名称"></el-input>
  </el-form-item>

  <el-button type="primary" size="middle" @click="CheckOut"><h4>查询</h4></el-button> -->

  <el-button type="primary" size="middle" @click="Export"><h4>导出</h4></el-button>
</el-form>

<!-- <h1>所选数据为：{{ this.refs.selectvalue.selected.label }}</h1> -->
<!-- 表格 -->
  <br>
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
        prop="user_name"
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
        width="250">暂无
    </el-table-column>
    <el-table-column
        prop="phone"
        label="联系电话"
        width="250">暂无
    </el-table-column>
    <el-table-column
    prop="detail"
    label="具体信息">
    <template slot-scope="scope">
         <el-button type="primary" size="small" @click="CheckDetail(scope.row)" disabled>查看</el-button>
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
</style>

<script>
import axios from 'axios';
var $this = {};

export default{
     name: 'File_backed',

     created:function () {
      axios.get("http://localhost:8090/user_accounts/multi").then((response)=>{
        this.tableData = response.data
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
    // options: [{
    //       value: '选项1',
    //       label: '丽江市'
    //     }, {
    //       value: '选项2',
    //       label: '昆明市'
    //     }, {
    //       value: '选项3',
    //       label: '大理州'
    //     }, {
    //       value: '选项4',
    //       label: '曲靖市'
    //     }, {
    //       value: '选项5',
    //       label: '玉溪市'
    //     }],
    // 表格
    tableData:[],
    values:[]
}
},

beforeCreate() {
 $this = this;},
methods:{
    CheckOut() {
        console.log('CheckOut!');
      },
    Export(){
        console.log('Export!');
    },
    CheckDetail(){
        this.$router.push(
            {path: '/government-pro/file_detail',
             query:{flag: '未上报'}}).catch(error => error);
        console.log('CheckDetail!');
    },
    // submit()
    // {
    //   console.log(this.values)
    // }
}
}
</script>
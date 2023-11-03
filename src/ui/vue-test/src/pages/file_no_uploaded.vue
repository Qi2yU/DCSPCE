
<template>
<div id="file_backed">
  <br>
<h3>未上报企业数量：100</h3>
<br>
<!-- 筛选 -->
<!-- 按地区选择 -->
<el-form :inline="true" :model="CheckValue" class="demo-form-inline">
  <el-form-item class="myselect" label="选择地区">
        <el-select class="el-select" v-model="CheckValue.geo" multiple placeholder="请选择地区">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
  </el-form-item>
  <!-- 按时间选择 -->
  <el-form-item label="选择时间" class="myselect2">
    <el-date-picker class="el-data-picker" type="date" placeholder="选择日期" v-model="CheckValue.date1" ></el-date-picker>
    <h>--</h>
    <el-date-picker class="el-data-picker" type="date" placeholder="选择日期" v-model="CheckValue.date2" ></el-date-picker>
  </el-form-item>
  <!-- 按关键字选择 -->
  <el-form-item class="myselect3" label="关键字">
    <el-input class="el-input" v-model="CheckValue.key" placeholder="关键字"></el-input>
  </el-form-item>
  <!-- 查询按钮 -->
  <el-button type="primary" size="middle" @click="CheckOut"><h4>查询</h4></el-button>
  <!-- 导出按钮 -->
  <el-button type="primary" size="middle" @click="Export"><h4>导出</h4></el-button>
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
    </el-table-column>
    <el-table-column
        prop="name"
        label="企业名称"
        width="250">
    </el-table-column>
    <el-table-column
        prop="account"
        label="企业账号"
        width="250">
    </el-table-column>
    <el-table-column
        prop="address"
        label="企业地区"
        width="250">
    </el-table-column>
    <el-table-column
        prop="time"
        label="更新时间"
        width="250">
    </el-table-column>
    <el-table-column
    prop="detail"
    label="具体信息">
    <el-button type="primary" size="small"  @click="CheckDetail('file_detail')">查看</el-button>
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
var $this = {};

export default{
     name: 'File_backed',

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
    options: [{
          value: '选项1',
          label: '丽江市'
        }, {
          value: '选项2',
          label: '昆明市'
        }, {
          value: '选项3',
          label: '大理州'
        }, {
          value: '选项4',
          label: '曲靖市'
        }, {
          value: '选项5',
          label: '玉溪市'
        }],
    // 表格
    tableData: [{
    seq: '1',
    name: '企业1',
    account: '1111111111',
    address: '丽江市',
    time: '2023-10-25',
    detail:'check'
}, {
    seq: '2',
    name: '企业2',
    account: '2222222222',
    address: '丽江市',
    time: '2023-10-24',
    detail:'check'
},{
    seq: '3',
    name: '企业3',
    account: '3333333333',
    address: '丽江市',
    time: '2023-10-24',
    detail:'check'
},{
    seq: '4',
    name: '企业4',
    account: '4444444444',
    address: '丽江市',
    time: '2023-10-23',
    detail:'check'
},{
    seq: '5',
    name: '企业5',
    account: '5555555555',
    address: '丽江市',
    time: '2023-10-21',
    detail:'check'
},{
    seq: '6',
    name: '企业6',
    account: '6666666666',
    address: '丽江市',
    time: '2023-10-20',
    detail:'check'
},]
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
            {path: '/layout/file_detail',
             query:{flag: '未上报'}}).catch(error => error);
        console.log('CheckDetail!');
    }
}
}
</script>
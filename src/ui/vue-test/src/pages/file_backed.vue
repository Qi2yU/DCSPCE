<style>
</style>
<template>
<div id="file_backed">
  <br>
<h3>已驳回企业数量：30</h3>
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
    <el-button type="primary" size="small" >查看</el-button>
</el-table-column>

</el-table>
<router-view></router-view>
</div>

</template>

<script>
var $this = {};

export default{
     name: 'File_backed',

data() {
return {
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
    file_detail() {
    this.$router.push("/layout/file_check/file_detail").catch(error => error);
    console.log("get file detail");
    }
}
}
</script>
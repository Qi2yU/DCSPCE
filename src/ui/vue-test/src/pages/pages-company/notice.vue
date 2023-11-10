<style>
</style>
<template>
  <div id="user">
    <h1>查看通知</h1><br/>
    <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    @selection-change="handleSelectionChange"
    row-class-name="tableRowClassName"
    style="width: 100%"
    height="500">
    <el-table-column
      type="selection"
      width="100">
    </el-table-column>
    <el-table-column
      fixed
      prop="date"
      label="日期"
      width="150">
      <template slot-scope="scope">{{ scope.row.time }}</template>
    </el-table-column>
    <el-table-column
      prop="name"
      label="发布用户"
      width="150">
    </el-table-column>
    <el-table-column
      prop="province"
      label="标题"
      width="300">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          type="primary"
          size="mini"
          @click="handleLook(scope.$index, scope.row)">查看</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
  export default {
    name: 'User',
    data() {
      return {
        tableData: [],
        multipleSelection: []
      }
    },
    created(){

      this.$http.get("/gov_notice/get_all").then((response)=>{
        console.log(response);
        console.log("notice 页面 初始化结束");
      });
    },
    methods: {
      handleLook($index, row){
        this.$router.push("/company/readNotice");
      }
    }
  }
</script>
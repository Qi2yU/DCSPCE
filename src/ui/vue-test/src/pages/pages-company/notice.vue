<style>
</style>
<template>
  <div id="user">
    <h1>查看通知</h1><br/>
    <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    row-class-name="tableRowClassName"
    style="width: 100%"
    height="500">
    <el-table-column
      fixed
      prop="gov_which"
      label="发布机构"
      width="200">
      <template slot-scope="scope">{{ scope.row.gov_which }}</template>
    </el-table-column>
    <el-table-column
      prop="msg_title"
      label="标题"
      width="250">
    </el-table-column>
    <el-table-column
      prop="msg_content"
      label="发布内容"
      width="450">
    </el-table-column>
    <el-table-column
      prop="msg_time"
      label="发布时间"
      width="150">
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
        multipleSelection: [],
        id: '',
      }
    },
  mounted() {
    this.fetchAllNotice();
  },
  methods: {
      fetchAllNotice() {
        this.$http({
          url: '/gov_notice/get_all',
          method: 'post',
          data:JSON.stringify({
            userId: this.$http.userid,
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          const return_value = response.data;
          this.tableData = return_value;
          console.log(this.tableData);
          console.log(return_value);
        });
      },
    }
  }
</script>
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
    <!-- <el-table-column
      type="selection"
      width="100">
    </el-table-column> -->
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
      width="200">
    </el-table-column>
    <el-table-column
      prop="msg_content"
      label="发布内容"
      width="300">
    </el-table-column>
    <el-table-column
      prop="msg_time"
      label="发布时间"
      width="100">
    </el-table-column>
    
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          type="primary"
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          type="danger"
          size="mini"
          @click="deleteEdit(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  
  </el-table>
  
    <div style="margin-top: 20px">
      <el-button type="success" @click="jump_add()">发布新通知</el-button>
      <!-- <el-button @click="toggleSelection()">取消选择</el-button> -->
      <!-- <el-button type="danger" @click="toggleSelection_deleteRow(tableData)">删除</el-button> -->
    </div>
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
            userId: "53000000000"
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
      jump_add(){
        this.$router.push("/government-pro/addNotice");
      },
      handleEdit(index, row){
        this.$http({
          url: '/gov_notice/findOne',
          method: 'post',
          data:JSON.stringify({
            gov_which: row.gov_which,
            msg_title: row.msg_title,
            msg_content: row.msg_content,
            msg_time: row.msg_time,
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          this.id = response.data;
          this.$router.push({
          path: '/government-pro/editNotice',
          query: {
            msg_title: row.msg_title,
            msg_content: row.msg_content,
            id: response.data,
          },
        });
        });
        // this.$router.push({
        //   path: '/government-pro/editNotice',
        //   query: {
        //     msg_title: row.msg_title,
        //     msg_content: row.msg_content,
        //     id: this.id,
        //   },
        // });
      },
      deleteEdit(index, row) {
        this.$http({
          url: '/gov_notice/deleteNotice',
          method: 'post',
          data:JSON.stringify({
            gov_which: row.gov_which,
            msg_title: row.msg_title,
            msg_content: row.msg_content,
            msg_time: row.msg_time,
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          this.tableData.splice(index, 1);
          this.$message.success('删除成功');
          console.log(row);
        });
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      // addidtoTableData(lists){
      //   var lens, i, temp, tempSpan;
      //   lens = lists.length;
      //   for (i = 0; i < lens; i++) {
      //       temp = i+1;           
      //       tempSpan = document.createElement("id");
      //       tempSpan.innerHTML =  temp + " ";
      //       lists[i].insertBefore(tempSpan, lists[i].childNodes[0]);
      //   }
      // },
      tableRowClassName ({ row, rowIndex }) {
          row.rowIndex = rowIndex;
      },
      toggleSelection_deleteRow(rows) {
        const idsToDelete = this.multipleSelection.map((row)=> row.id);
        this.tableData = this.tableData.filter((row) => !idsToDelete.includes(row.id));
        this.multipleSelection=[];
        this.fetchAllNotice();
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }
</script>
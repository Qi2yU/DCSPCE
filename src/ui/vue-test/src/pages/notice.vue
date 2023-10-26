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
      <template slot-scope="scope">{{ scope.row.date }}</template>
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
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px">
    <el-button type="success" @click="jump_add()">新建</el-button>
    <el-button @click="toggleSelection()">取消选择</el-button>
    <el-button type="danger" @click="toggleSelection_deleteRow(tableData)">删除</el-button>
  </div>
  </div>
</template>

<script>
  export default {
    name: 'User',
    data() {
      return {
        tableData: [{
          id: '1',
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
        }, {
          id: '2',
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
        }, {
          id: '3',
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
        }, {
          id: '4',
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
        }, {
          id: '5',
          date: '2016-05-08',
          name: '王小虎',
          province: '上海',
        }, {
          id: '6',
          date: '2016-05-06',
          name: '王小虎',
          province: '上海',
        }, {
          id: '7',
          date: '2016-05-07',
          name: '王小虎',
          province: '上海',
        }],
        multipleSelection: []
      }
    },

  methods: {
      jump_add(){
        this.$router.push("/layout/addNotice");
      },
      handleEdit($index, row){
        this.$router.push("/layout/editNotice");
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

      tableRowClassName ({ row, rowIndex }) {
          row.rowIndex = rowIndex;
      },
      toggleSelection_deleteRow(rows) {
        const idsToDelete = this.multipleSelection.map((row)=> row.id);
        this.tableData = this.tableData.filter((row) => !idsToDelete.includes(row.id));
        this.multipleSelection=[];
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }
</script>
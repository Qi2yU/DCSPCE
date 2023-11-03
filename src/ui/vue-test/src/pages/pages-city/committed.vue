<template>
    <div>
        <h2>已上报</h2>
        <el-table
        :data="tableData"
        border
        style="width: 100%">
            <el-table-column
                prop="userID"
                label="企业编号"
                width="240">
            </el-table-column>
            <el-table-column
                prop="name"
                label="企业名称"
                width="288">
            </el-table-column>
            <el-table-column
                prop="flag1"
                label="审核状态"
                width="180">
            </el-table-column>
            <el-table-column
                prop="flag2"
                label="上报状态"
                width="180">
            </el-table-column>
            <el-table-column
                 label="操作"
                 width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    
  </template>
  
  <script>
    export default {
      data() {
        return {
          tableData: [{
            userID: '1',
            name: 'c1',
            flag1: '未审核',
            flag2: '未上报'
          }, {
            userID: '2',
            name: 'c2',
            flag1: '审核通过',
            flag2: '未上报'
          }, {
            userID: '3',
            name: 'c3',
            flag1: '未审核',
            flag2: '未上报'
          },{
            userID: '4',
            name: 'c4',
            flag1: '审核通过',
            flag2: '已上报'
          },{
            userID: '5',
            name: 'c5',
            flag1: '未审核',
            flag2: '未上报'
          }]
        }
      },
      methods: {
      handleClick(row) {
        console.log(row);
        this.$router.push(
          {path:'/layout/detail',
        query:{
          userID:row.userID,
          flag1:row.flag1,
          flag2:row.flag2
        }}
        );
      }
    },
    beforeMount(){
      console.log("准备重新渲染了");
    }
    }
  </script>
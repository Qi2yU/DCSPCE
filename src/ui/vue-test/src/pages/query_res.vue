<template>
    <div>
        <h2>查询结果</h2>
        <el-table
        :data="tableData"
        border
        style="width: 100%">
            <el-table-column
                prop="num"
                label="企业编号"
                width="240">
            </el-table-column>
            <el-table-column
                prop="name"
                label="企业名称"
                width="288">
            </el-table-column>
            <el-table-column
                prop="stime"
                label="调查期开始时间"
                width="180">
            </el-table-column>
            <el-table-column
                prop="etime"
                label="调查期结束时间"
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
            num: '1',
            name: 'c1',
            stime: '2023-9-9',
            etime: '2023-10-9'
          }, {
            num: '2',
            name: 'c2',
            stime: '2023-9-9',
            etime: '2023-10-9'
          }, {
            num: '3',
            name: 'c3',
            stime: '2023-9-9',
            etime: '2023-10-9'
          },{
            num: '4',
            name: 'c4',
            stime: '2023-9-9',
            etime: '2023-10-9'
          },{
            num: '5',
            name: 'c5',
            stime: '2023-9-9',
            etime: '2023-10-9'
          }]
        }
      },
      methods: {
      handleClick(row) {
        console.log(row);
        this.$router.push(
          {path:'/detail',
        query:{
          stime:row.stime,
          etime:row.etime
        }}
        );
      }
    },
    beforeMount(){
      console.log("准备重新渲染了");
    }
    }
  </script>
<template>
    <div>
        <h2 style="font-size: 30px;">{{city}}-已上报</h2>
        
        <el-button type="primary" size="medium" style="float: right" @click="commit">批量上报</el-button>
        <el-table
        :data="tableData"
        border
        style="width: 100%; margin:auto" >
            <el-table-column
                prop="companyId"
                label="企业编号"
                width="240">
            </el-table-column>
            <el-table-column
                prop="companyName"
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
                 >
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    
  </template>
  
  <script>
    export default {
      created(){
        console.log("重新加载数据了");
        this.$http.get("/committed",{
            params:{
                city: this.city,
            }
        }).then((response)=>{
            this.tableData=response.data;
    });
      },
      inject: ['reload'],
      data() {
        return {

          city:this.$route.query.city,
          tableData:[],
          // tableData: [{
          //   companyId: '1',
          //   companyName: 'c1',
          //   flag1: '未审核',
          //   flag2: '未上报'
          // }, {
          //   companyId: '2',
          //   companyName: 'c2',
          //   flag1: '审核通过',
          //   flag2: '未上报'
          // }, {
          //   companyId: '3',
          //   companyName: 'c3',
          //   flag1: '未审核',
          //   flag2: '未上报'
          // },{
          //   companyId: '4',
          //   companyName: 'c4',
          //   flag1: '审核通过',
          //   flag2: '已上报'
          // },{
          //   companyId: '5',
          //   companyName: 'c5',
          //   flag1: '未审核',
          //   flag2: '未上报'
          // }]
        }
      },
      methods: {
      handleClick(row) {
        console.log(row);
        this.$router.push(
          {path:'/government-pro/detail',
        query:{
          id:row.companyId,
          flag1:row.flag1,
          flag2:row.flag2
        }}
        );

      },
      commit(){
        this.$http.get("/batchupbypro",{
            params:{
                city: this.city,
            }
        }).then((response)=>{});
        this.reload();
        
      }
    },
    beforeMount(){
      console.log("准备重新渲染了");
      console.log(this.city);
    }
    }
  </script>
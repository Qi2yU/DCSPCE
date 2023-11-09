<template>
  <div>
      <h2>查询结果</h2>
      <el-table
      :data="tableData"
      border
      style="width: 100%">
          <el-table-column
              prop="userId"
              label="企业编号"
              width="240">
          </el-table-column>
          <el-table-column
              prop="companyName"
              label="企业名称"
              width="288">
          </el-table-column>
          <el-table-column
              prop="start_time"
              label="调查期开始时间"
              width="180">
          </el-table-column>
          <el-table-column
              prop="end_time"
              label="调查期结束时间"
              width="180">
          </el-table-column>
          <el-table-column
              prop="tableName"
              label="调查期编号"
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
    created(){
      console.log("来了")
      console.log(this.date1);
      this.$http.post("/dataquery",{
          
              companyName:this.companyName,
              userId:this.userId,
              character:this.character,
              industry:this.industry,
              city:this.city,
              district:this.district,
              start_time:this.date1,
              end_time:this.date2
          
      }).then((response)=>{
          console.log(response);
          this.tableData=response.data;
  });
    },
    data() {
      return {
        companyName:this.$route.query.companyName,
        userId:this.$route.query.userId,
        character:this.$route.query.character,
        industry:this.$route.query.industry,
        city:this.$route.query.city,
        district:this.$route.query.district,
        date1:this.$route.query.date1,
        date2:this.$route.query.date2,
        tableData:[],
        // tableData: [{
        //   num: '1',
        //   name: 'c1',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // }, {
        //   num: '2',
        //   name: 'c2',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // }, {
        //   num: '3',
        //   name: 'c3',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // },{
        //   num: '4',
        //   name: 'c4',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // },{
        //   num: '5',
        //   name: 'c5',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // }]
      }
    },
    methods: {
    handleClick(row) {
      console.log(row);
      this.$router.push(
        {path:'/government-city/queryres2',
      query:{
          tableName:row.tableName,
          userId:row.userId,
          
      }}
      );
    }
  },
  beforeMount(){
    console.log("准备重新渲染了");
    
  }
  }
</script>
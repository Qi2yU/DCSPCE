<template>
    <div>
        <h2>数据审核</h2>
        <h1>调查期：</h1>
        <el-table
            :data="tableData"
            stripe
            
            @cell-click="show"
            style="width: 65%">
            <el-table-column
                prop="province"
                label="市"
                width="204">
            </el-table-column>
            <el-table-column  
                prop="committed"
                label="已上报"
                width="220">
            </el-table-column>
            <el-table-column
                prop="uncommitted"
                label="未上报"
                width="220 ">
            </el-table-column>
        </el-table>
    </div>
    
    </template>
      
      <script>
        export default {
          created(){
            this.$http.get("/count").then((response)=>{
            this.tableData=response.data;
    });
          },
          data() {
            return {
              tableData:[],
              // tableData: [{
              //   province: '玉溪市',
              //   committed: '24',
              //   uncommitted: '8'
              // }, {
              //   province: '昆明市',
              //   committed: '30',
              //   uncommitted: '23'
              // }, {
              //   province: '曲靖市',
              //   committed: '24',
              //   uncommitted: '23'
              // }, {
              //   province: '昭通市',
              //   committed: '24',
              //   uncommitted: '6'
              // }],
              currentCell:null,
              currentcol:null
            }
          },
          methods:{
            show(val,data){
                console.log(val);
                console.log(data);
                if(data.property=="committed")
                this.$router.push('/layout/committed');
                else if(data.property=="uncommitted")
                this.$router.push('/layout/uncommitted');
            }
          }
        }
      </script>
<template>
    <div>
        <h2>数据审核</h2>
        <h1>调查期：</h1>
        <el-table
            :data="tableData"
            stripe
            style="width:80%;margin: auto;font-size: large;"
            :header-row-style="{height:'80px'}"
            :row-style="{height: '80px'}"
            
            @cell-click="show">
            
            <el-table-column
                prop="province"
                label="市"
                >
            </el-table-column>
            <el-table-column  
                prop="committed"
                label="已上报"
                
                >
            </el-table-column>
            <el-table-column
                prop="uncommitted"
                label="未上报"
                >
            </el-table-column>
        </el-table>
    </div>
    
    </template>
      
      <script>
        export default {
          created(){
            this.$http.get("/getcount",{
              params:{
                city: this.city,
              }
            }).then((response)=>{
            console.log(response);
            this.tableData=response.data;
    });
          },
          data() {
            return {
              city:'澄江市',
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
                this.$router.push({path:'/government-city/committed',
                  query:{city:this.city}});
                else if(data.property=="uncommitted")
                this.$router.push({path:'/government-city/uncommitted',
                  query:{city:this.city}});
            }
          }
        }
      </script>
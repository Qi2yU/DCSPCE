<template>
    <div>

        <h1>调查期：{{ start_time }}-{{ end_time }}</h1>
        <el-table
            :data="tableData"
            stripe
            
           
            style="width: 80%; margin: auto;font-size: large">
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
            this.$http.get("/count").then((response)=>{
            this.tableData=response.data;
    });
            this.$http.get("/table").then((response)=>{
              this.start_time=response.data.start_time;
              this.end_time=response.data.end_time;
            })
          },

          data() {
            return {
              start_time:'',
              end_time:'',
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
                console.log(val.province);
                if(data.property=="committed")
                this.$router.push({path:'/government-pro/committed',
                  query: {city:val.province,}
              });
                else if(data.property=="uncommitted")
                this.$router.push({path:'/government-pro/uncommitted',
                  query:{city:val.province}
              });
            }
          }
        }
      </script>
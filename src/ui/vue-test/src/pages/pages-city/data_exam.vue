<template>
    <div>
        <h2>数据审核</h2>
        <h1>调查期：{{ start_time }}-{{ end_time }}</h1>
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
            this.getCity();
            this.$http.get("/getcount",{
              params:{
                city: this.city,
              }
            }).then((response)=>{
            console.log(response);
            this.tableData=response.data;
    });
    this.$http.get("/table").then((response)=>{
              this.start_time=response.data.start_time;
              this.end_time=response.data.end_time;
            })
          },
          data() {
            return {
              userId:this.$route.query.userId,
              city:'',
              start_time:'',
              end_time:'',
              tableData:[],
              cityMapping: {
                '5301': '昆明市',
                '5303':'曲靖市',
                '5304':'玉溪市',
                '5305':'保山市',
                '5306':'昭通市',
                '5307':'丽江市',
                '5308':'普洱市',
                '5309':'临沧市',
                '5323':'楚雄彝族自治州',
                '5325':'红河哈尼族彝族自治州',
                '5326':'文山壮族苗族自治州',
                '5328':'西双版纳傣族自治州',
                '5329':'大理白族自治州',
                '5331':'德宏傣族景颇族自治州',
                '5333':'怒江傈僳族自治州',
                '5334':'迪庆藏族自治州'
                
              },
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
            getCity() {
      // 截取前4位
      const prefix = this.userId.substring(0, 4);

      // 查找对照表中的映射
      this.city = this.cityMapping[prefix] || '未知城市';
    },
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
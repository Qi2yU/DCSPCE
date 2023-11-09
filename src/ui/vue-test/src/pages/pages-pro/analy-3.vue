<style>
</style>


<template>
  <div id="user">
    <h1>取样分析</h1>
    <choice_button @query-event = "Query_fun" ></choice_button>

    <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="name"
      label = "地区"
      width="180">
    </el-table-column>
    <el-table-column
      prop="value"
      label="企业数量">
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px">
    <el-button @click="toggleSelection()">取消选择</el-button>
  </div>
  <div id="main" style="width: 600px; height: 400px"></div>
  <el-button type="primary"    class = "download">导出</el-button>
  </div>
</template>

<script>
import choice_button from '../../components/components-pro/analy3_components/analy3_buttons.vue'


export default {
  data(){
    return{
      showData:[

      ],
      tableData:[],
      queryData:[],
      value:"",
      radio:"1",
    
    }
  },
  components:{
    choice_button,
   
  },
  mounted(){
    getPieData();
  },
  methods: {
    toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },

    handleSelectionChange(val) {
        this.multipleSelection = val;
    },

    Query_fun(value){
      console.log(value)
     
      this.queryData = []
      for(let i = 0;i < this.tableData.length; i++){
        this.queryData.push(this.tableData[i])
      }
    },
    drawChart(){
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据  
      console.log(pieData);
        var option = {
            series: [
                {
                    type: 'pie',
                    data: tableData
                }
            ]
        }
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    async getPieData(){
      this.$http.get("http://localhost:8070/government-pro/sample/mounted").
      then((response)=>{ 
          this.tableData = response.data
          console.log(response)
      })
    },
   
  },


}
</script>
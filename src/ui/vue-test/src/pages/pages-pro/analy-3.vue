<style>
</style>


<template>
  <div id="user">
    <h1>取样分析</h1>

    <el-select v-model="value" clearable @clear="delValue()" placeholder="地区选择">
      <el-option
        v-for="item in options_city"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
    <el-button type="success"  @click = "Query_fun()">查询</el-button>

    <el-table
    ref="multipleTable"
    :data="showData"
    border
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
      showData:[],
      tableData:[],
      options_city:[],
      value:"",
    }
  },
  components:{
    choice_button,
   
  },
  mounted(){
    this.$http.get("http://localhost:8070/government-pro/sample/mounted").
      then((response)=>{ 
          this.tableData = response.data
          this.showData = this.tableData
          this.drawChart();
          this.setOptions_city();
      })
    console.log(this)
  
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
    Query_fun(){
      
      let name = Object.values(this.options_city.at(this.value))[0]
      let obj = this.tableData.filter((data_single)=>Object.values(data_single)[0]  == name)
      console.log(this.showData)
      this.showData = obj
  
     
    },
    setOptions_city(){

      for(let i = 0;i < this.tableData.length; i++){
       
        var name = this.tableData[i]["name"]
        let obj = {"label": name, "value" : i}
        console.log(obj)
        this.options_city.push(obj)
        
    
        
      }
    },
    delValue(){
      this.showData = this.tableData
    },  
    drawChart(){
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据  
        var option = {
            series: [
                {
                    type: 'pie',
                    data: this.tableData
                }
            ]
        }
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },


}
</script>
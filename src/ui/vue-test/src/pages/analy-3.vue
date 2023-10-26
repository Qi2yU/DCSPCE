<style>
</style>


<template>
  <div id="user">
    <h1>取样分析</h1>
    <choice_button @query-event = "Query_fun" @chose-event = "Chose_fun"></choice_button>

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
  <chart :td = tableData></chart>
  <el-button type="primary"    class = "download">导出</el-button>
  </div>
</template>

<script>
import choice_button from '../components/analy3_buttons.vue'
import chart from '../components/analy3_chart.vue'

export default {
  data(){
    return{
      showData:[

      ],
      tableData:[{
      name:"a市",
      value:"10",
      },{
      name:"b市",
      value:"20",
      },{
      name:"a市",
      value:"30",
      },{
      name:"a市",
      value:"10",
      }],
      queryData:[],
      value:"",
      radio:"1",
    
    }
  },
  components:{
    choice_button,
    chart
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
   
  },


}
</script>
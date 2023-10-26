<style>
</style>

<template>
<div id="main" style="width: 600px; height: 400px">
  <template>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="企业名城"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="调查期1变化"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="调查期2变化"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="调查期3变化"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="调查期4变化"
        width="180">
      </el-table-column>
    </el-table>
  </template>
</div>

</template>

<script>
export default {
  name: 'User',
  data(){
    return{
      tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
    }
  },
   props:{
    cD:Array,
    num:Number,
   },

  watch:{
    cD(newvalue, oldvalue){
      console.log("change")
    }
  },
  mounted() {
    this.drawChart();
  },
  methods:{

    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      var line_data = [] 
        for(let i = 0; i < this.num; i++){
          line_data.push(this.cD[i])
        }
        var option = {
             xAxis: {
               type: 'category',
               data: ['xxx', 'xxx', 'xxx', 'xxx']
          },
             yAxis: {
               type: 'value'
          },
            series: [{
              name:line_data[0].name,
              data:line_data[0].nums,
              type:'line'
            },{
              name:line_data[1].name,
              data:line_data[1].nums,
              type:'line'
            },{
              name:line_data[2].name,
              data:line_data[2].nums,
              type:'line'
            },]
};
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },

  }
}
</script>
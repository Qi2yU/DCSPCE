<style>
</style>

<template>


</template>

<script>
export default {
  name: 'User',
  data(){
    return{
    }
  },
  mounted() {
    this.drawChart();
  },
  props:["td"],
  methods:{
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      var pieData = [];
      for(var i = 0; i <this.td.length; i++){
            pieData.push(this.td[i]);
            console.log(td[i]);
      }
      
      console.log(pieData);
        var option = {
            series: [
                {
                    type: 'pie',
                    data: pieData
                }
            ]
        }
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },

  }
}
</script>
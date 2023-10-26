<style>
</style>
<template>
  <div id="user">
    <h1>趋势分析</h1>
    <ana_button></ana_button>
    <chart :cD = "chartData" :num = "chartData.length"></chart>
    
    <el-button type="primary"    class = "download">导出</el-button>
  </div>
</template>

<script>
import ana_button from '../components/analy2_buttons.vue'
import chart from '../components/analy2_chart.vue'
export default {
  name: 'User',
  data(){
    return{
      chartData:[{
        name:'企业1',
        nums:[1,2,3,5],

      },{
        name:'企业2',
        nums:[4,5,6,7],
      },{
        name:'企业3',
        nums:[5,5,5,6],
      },
      ],
    }
  },
  components:{
    ana_button,
    chart
  }
}
</script>
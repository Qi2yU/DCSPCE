<style>
</style>
<template>
  <div id="user">
    <h1>对比分析</h1>
<!-- 
    <el-date-picker
      v-model="value_date1"
      type="datetimerange"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期">
  </el-date-picker>
    
  <el-date-picker
      v-model="value_date2"
      type="datetimerange"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期">
  </el-date-picker> -->


  <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
  

  <el-select v-model="value_city"  placeholder="地区" >
      <el-option
        v-for="item in options_city"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
  


  <!-- <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="date"
      label="企业名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="调查期A岗位变化数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="调查期A岗位减少数">
    </el-table-column>
    <el-table-column
      prop="name"
      label="调查期B岗位变化数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="调查期B岗位减少数">
    </el-table-column>
    <el-table-column
      prop="name"
      label="调查期A岗位变化占比"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="调查期B岗位变化占比">
    </el-table-column>
  </el-table> -->


    
    <el-button type="primary"  class = "download">导出</el-button>
  </div>
</template>

<script>

export default {
  name: 'User',
  data(){
    return{
      chartData:[],
      options_char:[],
      options_city:[
        {value:'0' ,label:"yang"},
        {value:'1' ,label:"shi"},
      ],
      options_indu:[],
      tableData:[],
      value_date1:'',
      value_date2:'',
      options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
      value:'',

    }
  },

//   mounted() {
//       this.$http.get("http://localhost:8070/government-pro/analy_compare/city"
//       ).then((response)=>{
//       let result = response.data
//       this.options_city = []
//       result.forEach(element => {
//         this.options_city.push({label:element.name, value:element.num})
//       });
//       console.log(this.options_city)
//     }
//     )
    
 
// },
  methods:{
    get_data(start_time,end_time,city,chara,indu){
      this.$https.get("http://localhost:8070/government-pro/analy_compare/get_data",{
        params:{
          start_time:start_time,
          end_time:end_time,
          city:city,
          character:chara,
          industry:indu
        }
      }
      ).then(res=>{
        console.log(res)
      })
    },
  },
  components:{
    
  }
}
</script>
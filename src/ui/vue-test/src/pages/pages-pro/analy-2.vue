<style>
</style>
<template>
  <div id="user">
    <h1>对比分析</h1>

    <el-date-picker
      v-model="value_date1"
      type="datetime"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期时间">
    </el-date-picker>

    <el-date-picker
      v-model="value_date2"
      type="datetime"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期时间">
    </el-date-picker>
    
  <!-- <el-date-picker
      v-model="value_date2"
      type="datetimerange"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期">
  </el-date-picker> -->


  

  <el-select v-model="value_city" clearable placeholder="地区" >
      <el-option
        v-for="item in options_city"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select v-model="value_char"  clearable placeholder="企业性质" >
      <el-option
        v-for="item in options_char"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select v-model="value_indu" clearable placeholder="所属行业" >
      <el-option
        v-for="item in options_indu"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
  
    <el-button type="primary" @click="get_data"  class = "query" >查询</el-button>

  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="name"
      label="企业名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="a_change_num"
      label="调查期A岗位变化数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="a_less"
      label="调查期A岗位减少数">
    </el-table-column>
    <el-table-column
      prop="b_change_num"
      label="调查期B岗位变化数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="b_less"
      label="调查期B岗位减少数">
    </el-table-column>
    <el-table-column
      prop="a_change_precent"
      label="调查期A岗位变化占比"
      width="180">
    </el-table-column>
    <el-table-column
      prop="_change_precent"
      label="调查期B岗位变化占比">
    </el-table-column>
  </el-table>


    
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
      options_city:[],
      options_indu:[],
      tableData:[],
      value_date1:'',
      value_date2:'',
     
      value_city:'',
      value_char:'',
      value_indu:'',

    }
  },

  mounted() {
      this.$http.get("http://localhost:8070/government-pro/analy_compare/city"
      ).then((response)=>{
      let result = response.data
      this.options_city = []
      result.forEach(element => {
        this.options_city.push({label:element.name, value:element.name})
      });
  
    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_compare/char"
      ).then((response)=>{
      let result = response.data
      this.options_char = []
      result.forEach(element => {
        this.options_char.push({label:element.name, value:element.name})
      });

    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_compare/indu"
      ).then((response)=>{
      let result = response.data
      this.options_indu = []
      result.forEach(element => {
        this.options_indu.push({label:element.name, value:element.name})
      });

    }
    )

},
  methods:{
    get_data(){

      this.$http.get("http://localhost:8070/government-pro/analy_compare/get_data",{
        params:{
          start_time:this.value_date1,
          end_time:this.value_date1,
          city:this.value_city,
          character:this.value_char,
          industry:this.value_indu
        }
      }
      ).then(res=>{
        this.tableData = res.data
        console.log(this.res)
        console.log(this.tableData)
      })
    },
  },
  components:{
    
  }
}
</script>
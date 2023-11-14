<style>
</style>
<template>
  <div id="user">
    <h1>趋势分析</h1>

    <el-date-picker
      v-model="value_date"
      type="datetimerange"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      align="right"
      unlink-panels
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      >
    </el-date-picker>

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

    <el-button type="primary"  @click = "get_data"  class = "download">查询</el-button>
    <div id="main" style="width: 600px; height: 400px"></div>
    <el-button type="primary"    class = "download">导出</el-button>
  </div>
</template>

<script>

export default {
  name: 'User',
  data(){
    return{
      value_city:'',
      value_char:'',
      value_indu:'',

      option:{},

      x_axis:[],


      value_date:'',
      chartData:[],
      tableData:[],
      myChart:null,
      start_time:'',
      end_time:'',

      options_char:[],
      options_city:[],
      options_indu:[],
      
    }
  },
  methods:{
    get_data(){

      this.start_time = this.value_date[0]
      this.end_time = this.value_date[1]
      console.log(this.start_time)
      console.log(this.end_time)
      this.$http.get("http://localhost:8070/government-pro/analy_tend/get_data",{
        params:{
          start_time:this.start_time,
          end_time:this.end_time,
          city:this.value_city,
          character:this.value_char,
          industry:this.value_indu
        }
      }
      ).then((response)=>{
      // let result = response.data
      // this.options_city = []
      // result.forEach(element => {
      //   this.options_city.push({label:element.name, value:element.name})
      // });
      console.log(response)
    })
    },
  },

  mounted() {
      this.myChart = this.$echarts.init(document.getElementById("main"));
      this.$http.get("http://localhost:8070/government-pro/analy_tend/city"
      ).then((response)=>{
      let result = response.data
      this.options_city = []
      result.forEach(element => {
        this.options_city.push({label:element.name, value:element.name})
      });
  
    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_tend/char"
      ).then((response)=>{
      let result = response.data
      this.options_char = []
      result.forEach(element => {
        this.options_char.push({label:element.name, value:element.name})
      });

    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_tend/indu"
      ).then((response)=>{
      let result = response.data
      this.options_indu = []
      result.forEach(element => {
        this.options_indu.push({label:element.name, value:element.name})
      });

    }
    )
    // this.$http.get("http://localhost:8070/government-pro/analy_tend/get_x"
    //   ).then((response)=>{
    //   let result = response.data
    //   this.x_axis = []
    //   result.forEach(element => {
    //     this.x_axis.push(element)
    //   });

    // }
    // )
},
}
</script>
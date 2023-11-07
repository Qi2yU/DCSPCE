<!--
 * @Author: your name
 * @Date: 2020-04-13 14:27:13
 * @LastEditTime: 2020-04-13 20:17:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\page\user.vue
 -->


<template>
  <div>
   <SumButton @sum-event="Sum_fun"></SumButton>
  <el-table
    :data="tableData"
    height="250"
    border
    stripe
    style="width: 100%">
    <el-table-column
      prop="name"
      label="企业名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="type"
      label="企业性质"
      width="180">
    </el-table-column>
    <el-table-column
      prop="business"
      label="所属行业"
      width="180">
    </el-table-column>
    <el-table-column
      prop="local"
      label="地区">
    </el-table-column>
    <el-table-column
      prop="research"
      label="调查期">
    </el-table-column>
    <!-- <el-table-column
      prop="address"
      label="企业季度">
    </el-table-column>
    <el-table-column
      prop="address"
      label="企业月度">
    </el-table-column>
    <el-table-column
      prop="address"
      label="企业年度">
    </el-table-column> -->
    <el-table-column
      prop="num"
      label="就业人数">
    </el-table-column>
  </el-table>


  <el-table
    :data="tableData_Gru"
    class="SumTable"
    height="250"
    border
    stripe
    style="width: 100%, display: block;">
    <el-table-column
      prop="choice"
      :label = choice_name
      width="180">
    </el-table-column>
    <el-table-column
      prop="num"
      label="就业人数">
    </el-table-column>
  </el-table>

 
  <el-button type="primary"  @click = "DownloadHandler"  class = "download">导出</el-button>
  
 
</div>
</template>

<script>
import axios from "axios"
import SumButton from "../../components/components-pro/sum_components/sum_buttons.vue"

  export default {
    data() {
      return {
        tableData: [],
        tableData_Gru:[],
        choice_name:'汇总项',
        sum_id:'',
        start_time:'',
        end_time:'',
      }
    },
    components:{
      SumButton,
    },
    methods:{
      Sum_fun(v1, v2, v3, v4){
          this.tableData_Gru = []
          
          switch(v1){
            case('选项1'):this.choice_name = "企业性质" ,this.sum_id ="企业性质"
            case('选项2'):this.choice_name = "所属行业",this.sum_id = "所属行业"
            case('选项3'):this.choice_name = "调查期",this.sum_id = "调查期"
            case('选项4'):this.choice_name = "地区",this.sum_id = "地区"
            case('选项5'):this.choice_name = "企业季度",this.sum_id = "企业季度"
            case('选项6'):this.choice_name = "企业月度",this.sum_id = "企业月度"
            case('选项7'):this.choice_name = "企业年度",this.sum_id = "企业年度"
            case('选项8'):this.choice_name = "企业性质",this.sum_id = "企业性质"
          }
          if(v4!=null){
            this.start_time = v4[0]
            this.end_time = v4[1]
            console.log(this.start_time)
            console.log(this.end_time)
          }
          else{
            console.log("未选择时间")
          }
        
          axios.get("http://localhost:8070/government-pro/sum",{
            params:{
              sum_id: this.sum_id,
              start_time:this.start_time,
              end_time:this.end_time,
            }
          }).then(function(response){
            console.log(response)
          })
         
      },


      DownloadHandler(){

      }
    }
  }
</script>


<style>
.download{
 text-align: center
}
.SumTable{
 display: block;
}
</style>

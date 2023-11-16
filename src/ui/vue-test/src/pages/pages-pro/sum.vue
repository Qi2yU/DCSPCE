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
      prop="character"
      label="企业性质"
      width="180">
    </el-table-column>
    <el-table-column
      prop="industry"
      label="所属行业"
      width="180">
    </el-table-column>
    <el-table-column
      prop="city"
      label="地区">
    </el-table-column>
    <el-table-column
      prop="time"
      label="调查期">
    </el-table-column>
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
      prop="kind_name"
      :label = choice_name
      width="180">
    </el-table-column>
    <el-table-column
      prop="sum_num"
      label="就业人数">
    </el-table-column>
  </el-table>

 
  <el-button type="primary"  @click = "DownloadHandler"  class = "download">导出</el-button>
  
 
</div>
</template>

<script>

import SumButton from "../../components/components-pro/sum_components/sum_buttons.vue"
import FileSaver from "file-saver";
import XLSX from "xlsx"

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
    mounted(){
          this.$http.get("http://localhost:8070/government-pro/sum/mounted",{
 
          }).then((response)=>{ 
            this.tableData = response.data
            console.log(response)
          })
          .catch(function(error){
            console.log(error)
          })
    },
    components:{
      SumButton,
    },
    methods:{
      Sum_fun(v1, v4){
          switch(v1){
            case('选项1'):this.choice_name = "企业性质" ,this.sum_id ="企业性质"
            break
            case('选项2'):this.choice_name = "所属行业",this.sum_id = "所属行业"
            break
            case('选项3'):this.choice_name = "调查期",this.sum_id = "调查期"
            break
            case('选项4'):this.choice_name = "企业地区",this.sum_id = "企业地区"
            break
            case('选项5'):this.choice_name = "企业季度",this.sum_id = "企业季度"
            break
            case('选项6'):this.choice_name = "企业月度",this.sum_id = "企业月度"
            break
            case('选项7'):this.choice_name = "企业年度",this.sum_id = "企业年度"
            break
            case('选项8'):this.choice_name = "企业性质",this.sum_id = "企业性质"
            break
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

          console.log(this.sum_id)
        
          this.$http.get("http://localhost:8070/government-pro/sum",{
            params:{
              sum_id: this.sum_id,
              start_time:this.start_time,
              end_time:this.end_time,
            }
          }).then((response)=>{
            this.tableData_Gru = response.data
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

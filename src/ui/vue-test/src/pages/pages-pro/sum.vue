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
    <el-select v-model="value1" placeholder="汇总字段">
      <el-option
        v-for="item in options1"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
  
    <el-date-picker
      v-model="value4"
      v-if = show_time
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


    <el-select v-if = show_research v-model="value_start" clearable placeholder="起始调查期" >
      <el-option
        v-for="item in options_starttime"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select  v-if = show_research v-model="value_end" clearable placeholder="结束调查期" >
      <el-option
        v-for="item in options_endtime"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>

    <el-button type="success"  @click = "Sum_fun">汇总</el-button>
    


  <el-table
    class="Table"
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

import FileSaver from "file-saver";
import XLSX from "xlsx"

  export default {
    data() {
      return {
        options1:[{
            value: '选项1',
            label: '企业性质'
          },{
          value: '选项2',
          label: '所属行业',
        }, {
          value: '选项3',
          label: '调查期'
        }, {
            value: '选项4',
            label: '地区'
          },{
          value: '选项5',
          label: '企业季度',
        }, {
          value: '选项6',
          label: '企业月度'
        },  {
          value: '选项7',
          label: '企业年度'
        }],
        options_starttime:[],
        options_endtime:[],
        tableData: [],
        tableData_Gru:[],
        choice_name:'汇总项',
        sum_id:'',
        start_time:'',
        end_time:'',

        value4:'',
        value1:'',

        value_start:'',
        value_end:'',

        show_time:false,
        show_research:false,
        flag : true,
      }
    },
    watch:{
      value1(new_v, old_v){

        if(new_v == "选项3"){
          this.show_research = true
          this.show_time = false
          this.flag = true
        }
        else if(new_v =="选项5" || new_v =="选项6" ||new_v =="选项7" ){
          this.show_research = false
          this.show_time = true
          this.flag = false
        }
      }
    },
    mounted(){
      this.$http.get("http://localhost:8070/government-pro/analy_tend/start_time"
      ).then((response)=>{
      let result = response.data
  
      this.options_starttime = []
      result.forEach(element => {
        this.options_starttime.push({label:element.name, value:element.name})
      });

    }
    )
    
    this.$http.get("http://localhost:8070/government-pro/analy_tend/end_time"
      ).then((response)=>{
        let result = response.data

      this.options_endtime = []
      result.forEach(element => {
        this.options_endtime.push({label:element.name, value:element.name})
      });
    }
    )
    this.$http.get("http://localhost:8070/government-pro/sum/mounted",{
          }).then((response)=>{ 
            this.tableData = response.data
          })
          .catch(function(error){
            console.log(error)
          })
    },


    methods:{
      Sum_fun(){
          switch(this.value1){
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
          if(this.show_time){
            this.start_time = this.value4[0]
            this.end_time = this.value4[1]
          }else if(this.show_research){
            this.start_time = this.value_start
            this.end_time = this.value_end
          }
          console.log(this.flag)
        
          this.$http.get("http://localhost:8070/government-pro/sum",{
            params:{
              sum_id: this.sum_id,
              start_time:this.start_time,
              end_time:this.end_time,
              flag_front:this.flag,
            }
          }).then((response)=>{
            this.tableData_Gru = response.data
            console.log(response)
          })
         
      },


      DownloadHandler(){
        
      let time = new Date();
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();
      let name = year + "" + month + "" + day;
      console.log(name)
      /* generate workbook object from table */
      //  .table要导出的是哪一个表格
      var wb = XLSX.utils.table_to_book(document.querySelector(".Table"));
      /* get binary string as output */
      var wbout = XLSX.write(wb, {
        bookType: "xlsx",
        bookSST: true,
        type: "array"
      });
      try {
        //  name+'.xlsx'表示导出的excel表格名字
        FileSaver.saveAs(
          new Blob([wbout], { type: "application/octet-stream" }),
          name + ".xlsx"
        );
      } catch (e) {
        if (typeof console !== "undefined") console.log(e, wbout);
      }
      return wbout;
      },
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

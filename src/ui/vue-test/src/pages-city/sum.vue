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
  import SumButton from "../components-city/sum_components/sum_buttons.vue"


  export default {
    data() {
      return {
        tableData: [{
          name: 'x公司',
          type: 'a',
          business: '上海市普陀区金沙江路 1518 弄',
          local: '上海市普陀区金沙江路 1518 弄',
          research: '2016-05-02',
          num:'10'

        }, {
          name: 'y公司',
          type: 'b',
          business: '上海市普陀区金沙江路 1517 弄',
          local: '上海市普陀区金沙江路 1518 弄',
          research: '2016-05-02',
          num:'30'
        }, {
          name: 'a公司',
          type: 'b',
          business: '上海市普陀区金沙江路 1519 弄',
          local: '上海市普陀区金沙江路 1518 弄',
          research: '2016-05-04',
          num:'10'
        }, {
          name: 'b公司',
          type: 'b',
          business: '上海市普陀区金沙江路 1516 弄',
          local: '上海市普陀区金沙江路 1518 弄',
          research: '2016-05-02',
          num:'20'
        }],
        tableData_Gru:[
        ],
        choice_name:'汇总项',

        
      }
    },
    components:{
      SumButton,
    },
    methods:{
      Sum_fun(v1, v2, v3){
          this.tableData_Gru = []
          console.log(v1,v2,v3)
          switch(v1){
            case('选项1'):this.choice_name = "企业性质"
            case('选项2'):this.choice_name = "所属行业"
            case('选项3'):this.choice_name = "调查期"
            case('选项4'):this.choice_name = "地区"
            case('选项5'):this.choice_name = "企业季度"
            case('选项6'):this.choice_name = "企业月度"
            case('选项7'):this.choice_name = "企业年度"
            case('选项8'):this.choice_name = "企业性质"
          }
          let keyContainerTemp = {} // 以key进行分组的临时对象
          this.tableData.forEach((item) => {
            keyContainerTemp[item.type] = keyContainerTemp[item.type] || []
            keyContainerTemp[item.type].push(item)
          })
          console.log(keyContainerTemp)
          
          let keysTemp = Object.keys(keyContainerTemp)
          keysTemp.forEach((keysItem) => {
            let count = 0
            keyContainerTemp[keysItem].forEach((item) => {
              count += parseInt(item.num) // 遍历每种Key对应的数量汇总
            })
            this.tableData_Gru.push({ 
              choice: keysItem,
              num:count })
          })
          console.log(this.tableData_Gru)
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

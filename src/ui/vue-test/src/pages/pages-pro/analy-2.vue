<style>
</style>
<template>
  <div id="user">
    <h1>对比分析</h1>

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
  </el-date-picker>


  <el-select v-model="value_city" placeholder="地区">
      <el-option
        v-for="item in options_city"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
  
    <el-select v-model="value_industry" placeholder="所属行业">
      <el-option
        v-for="item in options_indu"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
  
    <el-select v-model="value_character" placeholder="企业性质">
      <el-option
        v-for="item in options_char"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>


  <el-table
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
  </el-table>

  


    
    <el-button type="primary"    class = "download">导出</el-button>
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
      value_date1,
      value_date2,
    }
  },
  mounted(){
    this.$http.get("http://localhost:8070/government-pro/analy_compare/city"
    ).then((response)=>{
      this.options_char = response
      console.log(response)
    }

    )
  },

  components:{

  }
}
</script>
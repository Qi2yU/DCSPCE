<!--
 * @Author: your name
 * @Date: 2020-04-13 14:27:13
 * @LastEditTime: 2020-04-13 20:17:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\page\user.vue
 -->
 <style>
</style>

<template>
  <div>
    <el-select v-model="value" placeholder="汇总字段">
    <el-option
      v-for="item in options1"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      :disabled="item.disabled">
    </el-option>
  </el-select>

  <el-select v-model="value" placeholder="汇总项">
    <el-option
      v-for="item in options2"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      :disabled="item.disabled">
    </el-option>
  </el-select>

  <el-select v-model="value" placeholder="汇总方式">
    <el-option
      v-for="item in options3"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      :disabled="item.disabled">
    </el-option>
  </el-select>
  <el-button type="success"  @click = "SumHandler">汇总</el-button>
  <el-table
    :data="tableData"
    height="250"
    border
    stripe
    style="width: 100%">
    <el-table-column
      prop="date"
      label="企业名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="date"
      label="企业性质"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="所属行业"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="调查期">
    </el-table-column>
    <el-table-column
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
    </el-table-column>
    <el-table-column
      prop="address"
      label="就业人数">
    </el-table-column>
  </el-table>



 
  <el-button type="primary"  @click = "DownloadHandler">导出</el-button>
  
 
</div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
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
        options2:[{
          value: '选项1',
          label: '就业人数'
        }],
        options3:[{
          value: '选项1',
          label: '求和'
        },{
          value: '选项2',
          label: '平均值',
        }, {
          value: '选项3',
          label: '最大值'
        }, {
          value: '选项4',
          label: '最小值'
        },]
        
      }
    }
  }
</script>


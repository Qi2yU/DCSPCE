
<template>
    <div :xl="6" :lg="7" class="bg-query">
      <el-row type="flex" class="row-bg row-two" justify="center" align="top">
        <el-col :span="6"></el-col>
        <el-col :span="10">
          <!--标题-->
          <h1 class="head">查询结果</h1>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <el-row>
        <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
            prop="starttime"
            label="调查期开始时间"
            width="180">
        </el-table-column>
        <el-table-column
            prop="endtime"
            label="调查期结束时间"
            width="180">
        </el-table-column>
        <el-table-column
            prop="doc_num"
            label="建档期人数">
        </el-table-column>
        <el-table-column
            prop="query_num"
            label="调查期数据">
        </el-table-column>
        <el-table-column
            prop="descend_type"
            label="减少类型">
        </el-table-column>
        <el-table-column
            prop="main_reason"
            label="主要原因">
        </el-table-column>
        <el-table-column
            prop="second_reason"
            label="次要原因">
        </el-table-column>
        <el-table-column
            prop="reason_detail"
            label="原因说明">
        </el-table-column>
        </el-table>
      </el-row>
    
    
    </div>
    
  </template>
  
 
  <script>
    export default {
      data() {
        return {
          tableData: [{
            starttime: '2013-10-1',
            endtime: '2013-10-31',
            doc_num: '100',
            query_num: '90',
            descend_type: '1',
            main_reason: '1',
            second_reason: '',
            reason_detail: '11111'
          },{
            starttime: '2013-9-1',
            endtime: '2013-9-30',
            doc_num: '105',
            query_num: '100',
            descend_type: '1',
            main_reason: '1',
            second_reason: '',
            reason_detail: '1111'
          }, {
            starttime: '2013-8-1',
            endtime: '2013-8-31',
            doc_num: '100',
            query_num: '105',
            descend_type: '',
            main_reason: '',
            second_reason: '',
            reason_detail: ''
          }, {
            starttime: '2013-7-1',
            endtime: '2013-7-31',
            doc_num: '103',
            query_num: '100',
            descend_type: '1',
            main_reason: '1',
            second_reason: '',
            reason_detail: '11111'
          }]
        }
      }
    }
  </script>
  
  <style lanng="less"> 
  .bg-query {
      height: 100%;
    }
  
  </style>
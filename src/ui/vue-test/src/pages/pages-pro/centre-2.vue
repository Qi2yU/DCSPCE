
<template>
    <div :xl="6" :lg="7" class="bg-loadup">
      <el-row type="flex" class="row-bg row-two" justify="center" align="top">
        <el-col :span="6"></el-col>
        <el-col :span="10">
          <!--标题-->
          <h1 class="head">数据上报</h1>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <el-row  type="flex" class="row-bg row-two" justify="center" align="top">
        <el-col :span="5"></el-col>
        <el-col :span="20">
          <el-form :model="comCurData" ref="comCurData" label-width="150px" class="demo-dynamic">
            <el-form-item
              label="建档期就业人数"
              :rules="[
                { required: true, message: '请输入邮箱地址', trigger: 'blur' }
              ]"
            >
              <el-col :span="15">
              <el-input v-model="comCurData.docEmploymentNumber"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item
              label="调查期就业人数"
              :rules="[
                { required: true, message: '请输入邮箱地址', trigger: 'blur' }
              ]"
            >
              <el-col :span="15">
              <el-input v-model="comCurData.curEmploymentNumber"></el-input>
              </el-col>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="1"></el-col>
      </el-row>
    </div>
  </template>
  
  <script>
    
  </script>
  
  
  <style lanng="less"> 
  .head {
    text-align: center;
    margin-top: 5%;
    margin-bottom: 5%;
    color: #000a0c;
    font-size: 40px;
  }
  
  .loadup-list{
    width:100%;
    margin-bottom: 5%;
  }
  
  .bg-loadup {
      height: 100%;
      /* background-image: url("../assets/backgroud.jpg"); */
      /* background-size: 200%; */
  
      .toolbar .el-form--inline .reasonClass div.el-input{
        width: 10px;
      }
    }
  
  </style>
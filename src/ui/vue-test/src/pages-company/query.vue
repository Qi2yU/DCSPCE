
<template>
  <div :xl="6" :lg="7" class="bg-query">
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="6"></el-col>
      <el-col :span="10">
        <!--标题-->
        <h1 class="head1">往期数据查询</h1>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="4"></el-col>
      <el-col :span="14">
        <!--标题-->
        <h2 class="head2">选择要查询的数据时间段</h2>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
    <el-row type="flex" class="row-bg row-two" height='160px' align="top">
      <el-col :span="9"></el-col>
      <el-col :span="10">
        <div class="block">
          <el-date-picker
            v-model="value1"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="6"> 
        <el-button @click="query_result()">查询</el-button>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="9"></el-col>
      <el-col :span="10" justify="center" align="top">
        
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
  
  </div>
  
</template>

<script>
var $this = {};
  export default {
    data() {
      return {
        value1: '',
        value2: ''
      };
    },
    beforeCreate() {
     $this = this;
   },
    methods:{
      query_result() {
        $this.$router.push("/company/query_result").catch(error => error);
        console.log("get query result");
      }
    }
  };
  
</script>



<style lanng="less"> 
.bg-query {
    height: 100%;
  }


.head1 {
  text-align: center;
  margin-top: 5%;
  margin-bottom: 5%;
  color: #000a0c;
  font-size: 40px;
}

.head2 {
  text-align: center;
  margin-top: 5%;
  margin-bottom: 5%;
  color: #000a0c;
  font-size: 25px;
}

</style>
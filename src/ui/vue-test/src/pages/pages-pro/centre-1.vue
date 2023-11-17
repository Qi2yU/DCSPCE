<template>
  <div :xl="6" :lg="7" class="bg-loadup">
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="6"></el-col>
      <el-col :span="10">
        <!--标题-->
        <h1 class="head">调查期管理</h1>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="5"></el-col>
      <el-col :span="20">
        <el-form :model="tableSearch" ref="tableSearch" label-width="150px" class="demo-dynamic">
          <el-form-item
            label="调查期开始时间"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="tableSearch.r_time_start"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="调查期结束时间"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="tableSearch.r_time_end"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="数据填报开始时间"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="tableSearch.f_time_start"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="数据填报结束时间"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="tableSearch.f_time_end"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button @click="addTableSearch">添加</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  var $this = {};
  export default {
    data() {
      return {
        tableSearch: {
          r_time_start: '',
          r_time_end: '',
          f_time_start: '',
          f_time_end: '',
        }
      };
    },
    methods: {
      addTableSearch() {
        console.log(this.tableSearch);
        this.$http({
          url: '/government-pro/setTable',
          method: 'post',
          data:JSON.stringify({
            r_time_start: this.tableSearch.r_time_start,
            r_time_end: this.tableSearch.r_time_end,
            f_time_start: this.tableSearch.f_time_start,
            f_time_end: this.tableSearch.f_time_end,
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          const return_value = response.data;
          console.log(return_value);
        });
      },
    }
  }
</script>
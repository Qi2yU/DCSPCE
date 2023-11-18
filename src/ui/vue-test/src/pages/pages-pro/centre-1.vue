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
    <el-table :data="searchtableList" style="width: 100%;">
      <el-table-column label="调查期编号" prop="id"></el-table-column>
      <el-table-column label="调查期开始时间" prop="r_time_start"></el-table-column>
      <el-table-column label="调查期结束时间" prop="r_time_end"></el-table-column>
      <el-table-column label="调查期是否结束">
        <template slot-scope="scope">
          {{ formatIsFinished(scope.row.is_finished) }}
        </template>
      </el-table-column>
      <el-table-column label="调查期类型">
        <template slot-scope="scope">
          {{ formatType(scope.row.type) }}
        </template>
      </el-table-column>
    </el-table>
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
        },
        searchtableList: [],
      };
    },
    mounted() {
      this.fetchSearchList();
    },
    methods: {
      formatIsFinished(value) {
        return value === 0 ? '进行中' : '已结束';
      },
      formatType(value) {
        return value === 1 ? '按月报' : (2 ? '按半月报' : '按日报');
      },
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
          this.fetchSearchList();
        });
      },
      fetchSearchList() {
        this.$http({
          url: '/government-pro/setSearchList',
          method: 'get',
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          this.searchtableList = response.data;
          console.log(this.searchtableList);
          this.$forceUpdate();
        });
      },
    }
  }
</script>
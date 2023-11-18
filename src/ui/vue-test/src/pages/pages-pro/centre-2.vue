<template>
  <div :xl="6" :lg="7" class="bg-loadup">
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="6"></el-col>
      <el-col :span="10">
        <!--标题-->
        <h1 class="head">用户管理</h1>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="5"></el-col>
      <el-col :span="20">
        <el-form :model="userAccount" ref="userAccount" label-width="150px" class="demo-dynamic">
          <el-form-item
            label="企业名称"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="userAccount.userName"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="企业账号"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="userAccount.userId"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="初始密码"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
              <el-input v-model="userAccount.password"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button @click="addNewUser">添加</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-table :data="userList" style="width: 100%;">
      <el-table-column label="用户名称" prop="user_name"></el-table-column>
      <el-table-column label="用户ID" prop="user_id"></el-table-column>
      <el-table-column label="用户密码" prop="password"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  var $this = {};
  export default {
    data() {
      return {
        userAccount: {
          userName: '',
          userId: '',
          password: ''
        },
        userList: [],
      };
    },
    mounted() {
      this.fetchUserList();
    },
    methods: {
      addNewUser() {
        console.log(this.userAccount);
        this.$http({
          url: '/government-pro/setUser',
          method: 'post',
          data:JSON.stringify({
            userName: this.userAccount.userName,
            userId: this.userAccount.userId,
            password: this.userAccount.password,
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          const return_value = response.data;
          console.log(return_value);
          this.fetchUserList();
          // this.$router.push(return_value).catch(error=>error);
        });
        // this.go_back_centre2();
      },
      fetchUserList() {
        this.$http({
          url: '/government-pro/setUserList',
          method: 'get',
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          this.userList=response.data;
          console.log(this.userList);
          this.$forceUpdate();
        });
      },
      // async fetchUserList() {
      //   try {
      //     const response = await this.$axios.get('/government-pro/setUserList');
      //     this.userList = response.data;
      //     console.log(this.userList);
      //   } catch(error) {
      //     console.log("error in fetchUserList", error);
      //   }
      // },
      // go_back_centre2() {
      //   console.log("$router",this.$router);
      //   $this.$router.push("/government-pro").catch(error => error);
      // }
    }
  }
</script>
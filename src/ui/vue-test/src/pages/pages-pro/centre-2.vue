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
        }
      };
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
          this.$router.push(return_value).catch(error=>error);
        });
        // this.go_back_centre2();
      },
      // go_back_centre2() {
      //   console.log("$router",this.$router);
      //   $this.$router.push("/government-pro").catch(error => error);
      // }
    }
  }
</script>
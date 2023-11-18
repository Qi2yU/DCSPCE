<template>
    <div :xl="6" :lg="7" class="bg-login">
      <!--logo-->
      <!-- <el-image :src="require('@/assets/logo.png')" class="logo"/> -->
      <!--标题-->
      <el-row type="flex" class="row-bg row-two" justify="center" align="top">
        <el-col :span="6"></el-col>
        <el-col :span="10">
          <!--标题-->
          <h1 class="title">省就业失业数据收集上报系统</h1>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <!--form表单-->
      <el-row type="flex" class="row-bg card" justify="center" align="bottom">
        <el-col :span="7" class="login-card">
          <!--loginForm-->
          <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="21%" class="loginForm">
            <el-form-item label="账户" prop="username" style="width: 380px">
              <el-input v-model="loginForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" style="width: 380px">
              <el-input type="password" v-model="loginForm.password"></el-input>
            </el-form-item>
            <!-- <el-form-item label="验证码" prop="code" style="width: 380px"> -->
              <!-- <el-input v-model="loginForm.code" class="code-input" style="width: 70%;float: left"></el-input> -->
              <!-- 验证码图片 -->
              <!-- <el-image :src="codeImg" class="codeImg"></el-image> -->
            <!-- </el-form-item> -->
            <!-- <el-form-item label="记住密码" prop="remember"> -->
              <!-- <el-switch v-model="loginForm.remember"></el-switch> -->
            <!-- </el-form-item> -->
            <el-form-item class="btn-ground">
              <el-button type="primary" @click="submitForm('loginForm')">立即登陆</el-button>
              <el-button @click="resetForm('loginForm')">重置</el-button>
            </el-form-item>
            
          </el-form>
        </el-col>
      </el-row>
      <!-- 目前进入不同系统的按钮 -->
      <!-- <el-row type="flex" class="row-bg card" justify="center">
        <el-col class="login-system">
            <el-form label-width="10%" >
                <el-form-item class="btn-ground">
                <el-button type="primary" @click="goto_company('company')">企业端</el-button>
                <el-button type="primary" @click="goto_city('layout')">市端</el-button>
                <el-button type="primary" @click="goto_province('layout')">省端</el-button>
                </el-form-item>
            </el-form>
        </el-col>
      </el-row> -->
    </div>
  </template>
  
  <script>
  export default {
    name: "Login",
    data() {
      return {
        // 表单信息
        loginForm: {
          // 账户数据
          username: '',
          // 密码数据
          password: ''
          // // 验证码数据
          // code: '',
          // // 记住密码
          // remember: false,
          // // 验证码的key，因为前后端分离，这里验证码不能由后台存入session，所以交给vue状态管理
          // codeToken: ''
        },
        // 表单验证
        rules: {
          // 设置账户效验规则
          username: [
            {required: true, message: '请输入账户', trigger: 'blur'},
            {min: 11, max: 11, message: '长度为11个字符的账户', trigger: 'blur'}
          ],
          // 设置密码效验规则
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 15, message: '长度在 6 到 15 个字符的密码', trigger: 'blur'}
          ]
          // 设置验证码效验规则
          // code: [
          //   {required: true, message: '请输入验证码', trigger: 'blur'},
          //   {min: 5, max: 5, message: '长度为 5 个字符', trigger: 'blur'}
          // ]
        },
        // 绑定验证码图片
        // codeImg: null
      };
    },
    methods: {
      // 提交表单
      submitForm() {
        console.log(this.loginForm);

        this.$http({
          url: '/login',
          method: 'post',
          data: JSON.stringify({
            userId: this.loginForm.username,
            password: this.loginForm.password
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          const return_value= response.data;

          console.log(return_value)

          // 登陆成功后，把对应的userid赋给 axios
          this.$http.userid = return_value.userId
          console.log(this.$http.userid)

          // 全局变量userId的赋值！！！
          const return_router = return_value.nextRouter;

          this.$router.push({path:return_router,
            query:{userId:this.loginForm.username}});
        });
      },
      // submitForm(formName) {
      // this.$router.push("/layout").catch(error => error);
      // console.log("go to layout");
      
      // // this.$refs[formName].validate((valid) => {
      // //   if (valid) {
      // //     // 表单验证成功
      // //     alert('submit')
      // //   } else {
      // //     console.log('error submit!!');
      // //     return false;
      // //   }
      // // });
      // },
      // 重置表单
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      // goto_city(){
      //   this.$router.push("/layout").catch(error => error);
      //   console.log("go to layout");
      // },
      // goto_province(){
      //   this.$router.push("/layout").catch(error => error);
      //   console.log("go to layout");
      // },
      // goto_company(){
      //   this.$router.push("/company").catch(error => error);
      //   console.log("go to company");
      // },
    },
  }
  </script>
  
  <style scoped>
  .codeImg {
    /*让验证码图片飘在右边*/
    float: right;
    /*设置一些圆角边框*/
    border-radius: 3px;
    /*设置宽度*/
    width: 26%;
  }
  
  .bg-login {
    height: 100%;
    /* background-image: url("../assets/backgroud.jpg"); */
    background-size: 200%;
  
  }
  
  .btn-ground {
    text-align: center;
  }
  
  .logo {
    margin: 30px;
    height: 70px;
    width: 70px;
    position: fixed;
  }
  
  .title {
    text-shadow: -3px 3px 1px #5f565e;
    text-align: center;
    margin-top: 20%;
    color: #41b9a6;
    font-size: 40px;
  }
  
  .login-card {
    background-color: #ffffff;
    opacity: 0.9;
    box-shadow: 0 0 20px #ffffff;
    border-radius: 10px;
    padding: 40px 40px 30px 15px;
    width: auto;
  }

  .login-system{
    background-color: #ffffff;
    opacity: 0.9;
    box-shadow: 0 0 20px #ffffff;
    border-radius: 10px;
    padding: 40px 40px 30px 15px;
    width: 500px;
  }
  </style>
  

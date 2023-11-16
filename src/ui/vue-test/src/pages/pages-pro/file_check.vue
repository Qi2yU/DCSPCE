<!--
 * @Author: your name
 * @Date: 2020-04-13 14:27:08
 * @LastEditTime: 2020-04-13 18:48:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\page\main.vue
 -->
 <style>
</style>
<template>
    <div class="line">
    <h1>备案审核</h1>
    <br>
    
   <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" @click="goPage('file_be_checked')">待审核</el-menu-item>
        <el-menu-item index="2" @click="goPage('file_passed')">已通过</el-menu-item>
        <el-menu-item index="3" @click="goPage('file_backed')">已驳回</el-menu-item>
        <el-menu-item index="4" @click="goPage('file_no_uploaded')">未上报</el-menu-item>
    </el-menu>
    <router-view></router-view>
</div>
</template>
  
  
<script>
  var $this = {};
  export default {
    data() {
      // return {
      //   activeIndex: '',
      // };
    },
    beforeCreate() {
       $this = this;
     },
    methods: {

      function()
      {
          this.activeIndex=this.$route.name;
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      goPage(link) {
        if (link == "file_passed") {
           $this.$router.push("/government-pro/file_check/file_passed").catch(error => error);
           console.log("go to passed");
         } else if (link == "file_be_checked") {
           $this.$router.push("/government-pro/file_check/file_be_checked").catch(error => error);
           console.log("go to be checked");
         } else if ((link == "file_backed")) {
           $this.$router.push("/government-pro/file_check/file_backed").catch(error => error);
           console.log("go to backed");
         } else if ((link == "file_no_uploaded")) {
           $this.$router.push("/government-pro/file_check/file_no_uploaded").catch(error => error);
           console.log("go to no_uploaded");
         }
       }
    }
  }
</script>  
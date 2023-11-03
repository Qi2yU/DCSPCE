<!--
 * @Author: your name
 * @Date: 2020-04-13 15:59:01
 * @LastEditTime: 2020-04-13 19:09:52
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\components\nav-top.vue
 -->
 <style scoped>
 .el-row{
   height: 100%;
 }
 
 .el-menu{
   border-right:none;
 }
 .el-aside{
   border-right: 1px solid #f5f1f1;
 }
 </style>
 
 <template>
   <el-aside width="200px">
     <el-row class="tac">
       <el-col>
         <el-menu
           default-active="1"
           class="el-menu-vertical-demo"
           @open="handleOpen"
           @close="handleClose"
         >
           <el-menu-item index="1" @click="goPage('record')">
             <i class="el-icon-menu"></i>
             <span slot="title">企业备案</span>
           </el-menu-item>
 
           <el-menu-item index="2" @click="goPage('data_collection')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据上报</span>
           </el-menu-item>
 
           <el-menu-item index="3" @click="goPage('query')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据查询</span>
           </el-menu-item>
           <el-menu-item index="4" @click="goPage('notice')">
             <i class="el-icon-s-order"></i>
             <span slot="title">查看通知</span>
           </el-menu-item>
         </el-menu>
       </el-col>
     </el-row>
   </el-aside>
 </template>
 
 <script>
 var $this = {};
 export default {
   data() {
     return {};
   },
   beforeCreate() {
     $this = this;
   },
   methods: {
     handleOpen(key, keyPath) {
       console.log(key, keyPath);
     },
     handleClose(key, keyPath) {
       console.log(key, keyPath);
     },
 
     //跳转到某个导航页
     goPage(link) {
       if (link == "record") {
         $this.$router.push("/company/record").catch(error => error);
       } else if ((link == "data_collection")) {
         $this.$router.push("/company/data_collection").catch(error => error);
         console.log("go to data_collection");
       } else if ((link == "query")) {
         $this.$router.push("/company/query").catch(error => error);
         console.log("go to query");
       }else if ((link == "notice")) {
         $this.$router.push("/company/notice").catch(error => error);
         console.log("go to notice");
       }
     }
   }
 };
 </script>
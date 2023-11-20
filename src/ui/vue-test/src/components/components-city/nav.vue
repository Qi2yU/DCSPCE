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
       
           class="el-menu-vertical-demo"
           @open="handleOpen"
           @close="handleClose"
         >

           <el-menu-item index="0" @click="goPage('file_check')">
             <i class="el-icon-menu"></i>
             <span slot="title">备案审核</span>
           </el-menu-item>

           
           
           <el-menu-item index="5" @click="goPage('dataexam')">
             <i class="el-icon-menu"></i>
             <span slot="title">数据审核</span>
           </el-menu-item>

           <el-menu-item index="2" @click="goPage('sum')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据汇总</span>
           </el-menu-item>
 
           <el-menu-item index="3" @click="goPage('query')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据查询</span>
           </el-menu-item>
 
           <el-submenu index="4">
             <template slot="title">
               <i class="el-icon-location"></i>
               <span>数据分析</span>
             </template>
             <el-menu-item-group>
               <el-menu-item index="4-1"  @click="goPage('analy-1')">趋势分析</el-menu-item>
             </el-menu-item-group>
             <el-menu-item-group>
               <el-menu-item index="4-2"  @click="goPage('analy-2')">对比分析</el-menu-item>
             </el-menu-item-group>
           </el-submenu>
           
           <el-menu-item index="6" @click="goPage('notice')">
             <i class="el-icon-s-order"></i>
             <span slot="title">通知界面</span>
           </el-menu-item>


           <el-submenu index="1">
             <template slot="title">
               <i class="el-icon-location"></i>
               <span>系统管理</span>
             </template>
             <el-menu-item-group>
               <el-menu-item index="1-1"  @click="goPage('centre-1')">调查期管理</el-menu-item>
             </el-menu-item-group>
             <el-menu-item-group>
               <el-menu-item index="1-2"  @click="goPage('centre-2')">用户管理</el-menu-item>
             </el-menu-item-group>
             <el-menu-item-group>
               <el-menu-item index="1-3"  @click="goPage('centre-3')">角色管理</el-menu-item>
             </el-menu-item-group>
             <el-menu-item-group>
              <el-menu-item index="1-4"   @click="goPage('centre-4')">系统状态监测</el-menu-item>
             </el-menu-item-group>
           </el-submenu>
         </el-menu>
       </el-col>
     </el-row>
   </el-aside>
 </template>
 
 <script>
 var $this = {};
 export default {
  props:['userId'],
   data() {
     return { };
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
      if (link == "file_check") {
         $this.$router.push("/government-city/file_check").catch(error => error);
         console.log("666");
       } else if ((link == "centre-1")) {
        console.log("/government-city/centre-1")
         $this.$router.push("/government-city/centre-1").catch(error => error);
       } else if((link == "centre-2")) {
        console.log("/government-city/centre-2")
         $this.$router.push("/government-city/centre-2").catch(error => error);
       } else if((link == "centre-3")) {
        console.log("/government-city/centre-3")
         $this.$router.push("/government-city/centre-3").catch(error => error);
       } else if((link == "centre-4")) {
        console.log("/government-city/centre-4")
         $this.$router.push("/government-city/centre-4").catch(error => error);
       } else if ((link == "dataexam")) {
         $this.$router.push({path:"/government-city/dataexam",query:{userId:this.userId}}).catch(error => error);
         console.log("go to dataexam");
       }else if ((link == "sum")) {
         $this.$router.push("/government-city/sum").catch(error => error);
         console.log("go to sum");
       } else if ((link == "query")) {
         $this.$router.push({path:"/government-city/query",query:{userId:this.userId}}).catch(error => error);
         console.log("go to query");
       }else if ((link == "analy-1")) {
         $this.$router.push("/government-city/analy1").catch(error => error);
         console.log("go to analy-1");
       }else if ((link == "analy-2")) {
         $this.$router.push("/government-city/analy2").catch(error => error);
         console.log("go to analy-2");
       }else if ((link == "analy-3")) {
         $this.$router.push("/government-city/analy3").catch(error => error);
         console.log("go to analy-3");
       }else if ((link == "notice")) {
         $this.$router.push("/government-city/notice").catch(error => error);
         console.log("go to notice");
       }
     }
   }
 };
 </script>
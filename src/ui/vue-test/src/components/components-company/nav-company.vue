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
         
           <el-menu-item index="1" @click="goPage('record')">
             <i class="el-icon-menu"></i>
             <span slot="title">企业备案</span>
           </el-menu-item>
           <template v-if="is_recorded()">
           <el-menu-item index="2" @click="goPage('data_collection')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据上报</span>
           </el-menu-item>
          </template>
          <!-- 未备案时显示不能用的 -->
          <template v-if="!is_recorded()">
           <el-menu-item disabled index="2" @click="goPage('data_collection')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据上报</span>
           </el-menu-item>
          </template>
          
          <template v-if="is_recorded()">
           <el-menu-item index="3" @click="goPage('query')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据查询</span>
           </el-menu-item>
          </template>
          <!-- 未备案时显示不能用的 -->
          <template v-if="!is_recorded()">
           <el-menu-item disabled index="3" @click="goPage('query')">
             <i class="el-icon-s-order"></i>
             <span slot="title">数据查询</span>
           </el-menu-item>
          </template>
          <template v-if="is_recorded()">
           <el-menu-item index="4" @click="goPage('notice')">
             <i class="el-icon-s-order"></i>
             <span slot="title">查看通知</span>
           </el-menu-item>
          </template>
          <!-- 未备案时显示不能用的 -->
          <template v-if="!is_recorded()">
           <el-menu-item disabled index="4" @click="goPage('notice')">
             <i class="el-icon-s-order"></i>
             <span slot="title">查看通知</span>
           </el-menu-item>
          </template>
         </el-menu>
       </el-col>
     </el-row>
   </el-aside>
 </template>
 
 <script>
 var $this = {};
 export default {
   data() {
     return {
      isRecorded: ''
     };
   },
   beforeCreate() {
     $this = this;
   },
   created:function(){
      this.userid = this.$http.userid
      this.$http.get("/get_recorded_info",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        // console.log(response)
        // console.log(response.data)
        this.isRecorded = response.data.is_recorded;
        // console.log(this.isRecorded)
      });
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      is_recorded(){
        return this.isRecorded == 1
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
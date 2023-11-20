<!--
 * @Author: your name
 * @Date: 2020-04-13 10:56:45
 * @LastEditTime: 2020-04-13 18:57:16
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\page\layout.vue
 -->
<style>
.el-main {
  background-color: #f5f7f9;
}

.el-header,
.el-footer {
  background-color: white;
  box-sizing: border-box;
  border-bottom: 1px solid #f5f1f1;
}

.el-container {
  height: 100%;
}
.p{
  
}
</style>

 <template>
  <el-container>
    <!-- header部分 -->
    <el-header>
      <navtop></navtop>
    </el-header>
    <el-container>
       <!-- aside部分 -->
      <leftNav></leftNav>
      <el-main>
        
        <p v-if="show">欢迎使用省端企业就业管理系统</p>
        <el-divider></el-divider>
        <check v-if="show"></check>
         <!-- main部分 -->
       <router-view />
       
      </el-main>
    </el-container>
  </el-container>
</template>


 <script>
import navtop from "@/components/components-pro/nav-top.vue";
import leftNav from "@/components/components-pro/nav.vue";
import check from "@/pages/pages-pro/data_exam.vue"
export default {
  components: {
    navtop,
    leftNav,
    check,
  },
  data() {
    return {
      show:true,
    };
  },
  methods: {},
  updated(){
    console.log("update")
    this.show = false 
  }

};
</script>




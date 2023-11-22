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
  /* overflow:hidden */
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
</style>

 <template>
  <el-container>
    <!-- header部分 -->
    <el-header>
      <navtop></navtop>
    </el-header>
    <el-container>
       <!-- aside部分 -->
      <leftNav :userId="userId"></leftNav>
      <el-main>
        <div v-if="show">
        <h1 style="font-size: xx-large;">欢迎使用市端省就业失业数据收集系统</h1>
        <el-divider></el-divider>
        <check ></check>
        </div>
         <!-- main部分 -->
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>


 <script>
import navtop from "@/components/components-city/nav-top.vue";
import leftNav from "@/components/components-city/nav.vue";
import check from "@/pages/pages-city/data_exam copy.vue"
export default {
  components: {
    navtop,
    leftNav,
    check,
  },
  data() {
    return { 
      userId:this.$route.query.userId,
      show: true,
    };
  },
  methods: {},
  updated(){
    console.log("update")
    this.show = false 
  }
};
</script>




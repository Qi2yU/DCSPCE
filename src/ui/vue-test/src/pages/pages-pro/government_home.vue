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

  position: absolute;

  left: 200px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;


}

.el-header{
  position: relative;
  width: 100%;
  height: 60px; 
}
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
        <div v-if="show">
          <h1 style="font-size: xx-large;">欢迎使用省端省就业失业数据收集系统</h1>
          <el-divider ></el-divider>
          <check></check>
        </div>
        <router-view /> 
      </el-main>
    </el-container>
  </el-container>
</template>


 <script>
import navtop from "@/components/components-pro/nav-top.vue";
import leftNav from "@/components/components-pro/nav.vue";
import check from "@/pages/pages-pro/data_exam copy.vue"
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
    if(this.$route.path != "/government-pro"){
      this.show = false
    }
  },
  

};
</script>




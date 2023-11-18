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
         <!-- main部分 -->
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>


 <script>
import navtop from "@/components/components-company/nav-top.vue";
import leftNav from "@/components/components-company/nav-company.vue";
export default {
  components: {
    navtop,
    leftNav
  },
  data() {
    return {};
  },created:function(){
      this.userid = this.$http.userid
      this.$http.get("/get_refused_info",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        console.log(response)
        console.log(response.data)
        if(! response.data.is_refused) return ;
        if(response.data.comInfoRefused != null) this.$message.error("您的审核备案已被驳回，请及时查看。(附驳回理由：" + response.data.comInfoRefused +")");
        else this.$message.error("您当期上报的数据已被驳回，请及时查看。(附驳回理由：" + response.data.emplInfoRefused +")");
        
      });

    },
  methods: {}
};
</script>




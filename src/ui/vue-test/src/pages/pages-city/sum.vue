<!--
 * @Author: your name
 * @Date: 2020-04-13 14:27:13
 * @LastEditTime: 2020-04-13 20:17:55
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \vueTest\houtai\src\page\user.vue
 -->

 <template>
  <div>
   <SumButton @sum-event="Sum_fun"></SumButton>
  <el-table
    class="Table"
    :data="tableData"
    height="250"
    border
    stripe
    style="width: 100%">
    <el-table-column
      prop="name"
      label="企业名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="character"
      label="企业性质"
      width="180">
    </el-table-column>
    <el-table-column
      prop="industry"
      label="所属行业"
      width="180">
    </el-table-column>
    <el-table-column
      prop="city"
      label="地区">
    </el-table-column>
    <el-table-column
      prop="time"
      label="调查期">
    </el-table-column>
    <el-table-column
      prop="num"
      label="就业人数">
    </el-table-column>
  </el-table>


  <el-table
    :data="tableData_Gru"
    class="SumTable"
    height="250"
    border
    stripe
    style="width: 100%, display: block;">
    <el-table-column
      prop="kind_name"
      :label = choice_name
      width="180">
    </el-table-column>
    <el-table-column
      prop="sum_num"
      label="就业人数">
    </el-table-column>
  </el-table>

 
  <el-button type="primary"  @click = "DownloadHandler"  class = "download">导出</el-button>
  
 
</div>
</template>

<script>

import SumButton from "../../components/components-city/sum_components/sum_buttons.vue"
import FileSaver from "file-saver";
import XLSX from "xlsx"

  export default {
    data() {
      return {
        
        city:this.$http.userId,
        tableData: [],
        tableData_Gru:[],
        choice_name:'汇总项',
        sum_id:'',
        start_time:'',
        end_time:'',
        userId:this.$route.query.userId,
        cityMapping: {
                '5301': '唐山',
                '5303':'曲靖市',
                '5304':'玉溪市',
                '5305':'保山市',
                '5306':'昭通市',
                '5307':'丽江市',
                '5308':'普洱市',
                '5309':'临沧市',
                '5323':'楚雄彝族自治州',
                '5325':'红河哈尼族彝族自治州',
                '5326':'文山壮族苗族自治州',
                '5328':'西双版纳傣族自治州',
                '5329':'大理白族自治州',
                '5331':'德宏傣族景颇族自治州',
                '5333':'怒江傈僳族自治州',
                '5334':'迪庆藏族自治州'
                
        },

      }
    },
   async mounted(){
          await this.getCity()
          console.log(this.city)
          this.$http.get("http://localhost:8070/government-city/sum/mounted",{
            params:{
              city:this.city
            }
          }).then((response)=>{ 
            this.tableData = response.data
            console.log(response)
          })
          .catch(function(error){
            console.log(error)
          })
    },
    components:{
      SumButton,
    },
    methods:{
      getCity() {
      // 截取前4位
      const prefix = this.userId.substring(0, 4);

      // 查找对照表中的映射
      this.city = this.cityMapping[prefix] || '未知城市';
      },
      Sum_fun(v1, v4){
          switch(v1){
            case('选项1'):this.choice_name = "企业性质" ,this.sum_id ="企业性质"
            break
            case('选项2'):this.choice_name = "所属行业",this.sum_id = "所属行业"
            break
            case('选项3'):this.choice_name = "调查期",this.sum_id = "调查期"
            break
            case('选项5'):this.choice_name = "企业季度",this.sum_id = "企业季度"
            break
            case('选项6'):this.choice_name = "企业月度",this.sum_id = "企业月度"
            break
            case('选项7'):this.choice_name = "企业年度",this.sum_id = "企业年度"
            break
            case('选项8'):this.choice_name = "企业性质",this.sum_id = "企业性质"
            break
          }
          if(v4!=null){
            this.start_time = v4[0]
            this.end_time = v4[1]
            console.log(this.start_time)
            console.log(this.end_time)
          }
          else{
            console.log("未选择时间")
          }

          console.log(this.sum_id)
        
          this.$http.get("http://localhost:8070/government-city/sum",{
            params:{
              sum_id: this.sum_id,
              start_time:this.start_time,
              end_time:this.end_time,
              city:this.city,
            }
          }).then((response)=>{
            this.tableData_Gru = response.data
            console.log(response)
          })
         
      },


      DownloadHandler(){
        
      let time = new Date();
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();
      let name = year + "" + month + "" + day;
      console.log(name)
      /* generate workbook object from table */
      //  .table要导出的是哪一个表格
      var wb = XLSX.utils.table_to_book(document.querySelector(".Table"));
      /* get binary string as output */
      var wbout = XLSX.write(wb, {
        bookType: "xlsx",
        bookSST: true,
        type: "array"
      });
      try {
        //  name+'.xlsx'表示导出的excel表格名字
        FileSaver.saveAs(
          new Blob([wbout], { type: "application/octet-stream" }),
          name + ".xlsx"
        );
      } catch (e) {
        if (typeof console !== "undefined") console.log(e, wbout);
      }
      return wbout;
      },
    }
  }
</script>


<style>
.download{
 text-align: center
}
.SumTable{
 display: block;
}
</style>

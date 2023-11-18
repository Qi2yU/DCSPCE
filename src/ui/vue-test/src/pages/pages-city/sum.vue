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
    <el-select v-model="value1" placeholder="汇总字段">
      <el-option
        v-for="item in options1"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
  



    <el-select v-if = show_research v-model="value_start" clearable placeholder="起始调查期" >
      <el-option
        v-for="item in options_starttime"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select  v-if = show_research v-model="value_end" clearable placeholder="结束调查期" >
      <el-option
        v-for="item in options_endtime"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>


    <el-select v-if = show_month v-model="value_startmonth" clearable placeholder="起始月份" >
      <el-option
        v-for="item in options_startmonth"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select  v-if = show_month v-model="value_endmonth" clearable placeholder="结束月份" >
      <el-option
        v-for="item in options_endmonth"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>

    <el-select v-if = show_year v-model="value_startyear" clearable placeholder="起始年份" >
      <el-option
        v-for="item in options_startyear"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select  v-if = show_year v-model="value_endyear" clearable placeholder="结束年份" >
      <el-option
        v-for="item in options_endyear"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>


    <el-button type="success" :disabled="show" @click = "Sum_fun">汇总</el-button>
    


  <!-- <el-table
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
  </el-table> -->


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

import FileSaver from "file-saver";
import XLSX from "xlsx"

  export default {
    data() {
      return {
        options1:[{
            value: '选项1',
            label: '企业性质'
          },{
          value: '选项2',
          label: '所属行业',
        }, {
          value: '选项3',
          label: '调查期'
        },{
          value: '选项5',
          label: '企业季度',
        }, {
          value: '选项6',
          label: '企业月度'
        },  {
          value: '选项7',
          label: '企业年度'
        }],
        options_starttime:[],
        options_endtime:[],
        options_startmonth:[],
        options_endmonth:[],
        options_startyear:[],
        options_endyear:[],


        userId:this.$http.userid,
        city:'',

        tableData: [],
        tableData_Gru:[],
        choice_name:'汇总项',
        sum_id:'',
        start_time:'',
        end_time:'',

        value4:'',
        value1:'',

        value_start:'',
        value_end:'',
        value_endmonth:'',
        value_startmonth:'',
        value_startyear:'',
        value_endyear:'',

        show_month:false,
        show_research:false,
        show_quter:false,
        show_year:false,

        flag:0,
        show:true,

        cityMapping: {
                '5301': '昆明市',
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
    watch:{
      value1(new_v, old_v){

        if(new_v == "选项3" || new_v =="选项1" ||new_v =="选项4" || new_v =="选项2"|| new_v =="选项8"){
          this.show_research = true
          this.show_month = false
          this.show_year = false
          this.flag = 0
        }
        else if(new_v =="选项6" || new_v =="选项5"){
          this.show_research = false
          this.show_month = true
          this.show_year = false
          this.flag = 1
        }
        else if(new_v =="选项7"  ){
          this.show_research = false
          this.show_month = false
          this.show_year = true
          this.flag = 3
        }

        
      },
      value_end(){
      
        let cs
      if(this.value_start.length == 13){
         cs = this.value_start.replace("年","0")
      }
      else{
         cs = this.value_start.replace("年","")
      }
      
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")
      cs = parseInt(cs)

      let es
      if(this.value_end.length == 13){
         es = this.value_end.replace("年","0")
      }
      else{
         es = this.value_end.replace("年","")
      }
      es = es.replace("月第", "")
      es = es.replace("号调查期","") //2024011 2023111
      
      es = parseInt(es)
  
      if(cs > es ){
        this.show = true
      }
      else{
        this.show = false
      }
      },

      value_start(){
        let cs
      if(this.value_start.length == 13){
         cs = this.value_start.replace("年","0")
      }
      else{
         cs = this.value_start.replace("年","")
      }
      
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")
      cs = parseInt(cs)

      let es
      if(this.value_end.length == 13){
         es = this.value_end.replace("年","0")
      }
      else{
         es = this.value_end.replace("年","")
      }
      es = es.replace("月第", "")
      es = es.replace("号调查期","") //2024011 2023111
      
      es = parseInt(es)
  
      if(cs > es ){
        this.show = true
      }
      else{
        this.show = false
      }
      },




      value_startyear(){
    let cs = this.value_startyear

      let es = this.value_endyear

      cs = parseInt(cs)
      es = parseInt(es)
      if(cs > es ){
        this.show = true
      }
      else{
        this.show = false
      }
      },
      value_endyear(){
    let cs = this.value_startyear

      let es = this.value_endyear

      cs = parseInt(cs)
      es = parseInt(es)
      if(cs > es ){
        this.show = true
      }
      else{
        this.show = false
      }
      },
      value_startmonth(){//2023_08
    let cs = this.value_startmonth.replace("_","")

      let es = this.value_endmonth.replace("_","")
      cs = parseInt(cs)
      es = parseInt(es)
      if(cs > es ){
        this.show = true
      }
      else{
        this.show = false
      }
      },
      value_endmonth(){//2023_08
    let cs = this.value_startmonth.replace("_","")

      let es = this.value_endmonth.replace("_","")

      cs = parseInt(cs)
      es = parseInt(es)
      if(cs > es ){
        this.show = true
      }
      else{
        this.show = false
      }
      },

    },
  async  mounted(){
 

    
   



      this.$http.get("http://localhost:8070/government-pro/analy_tend/start_time"
      ).then((response)=>{
      let result = response.data
  
      this.options_starttime = []
      result.forEach(element => {
        this.options_starttime.push({label:element.name, value:element.name})
      });

    }
    )
    
    this.$http.get("http://localhost:8070/government-pro/analy_tend/end_time"
      ).then((response)=>{
        let result = response.data

      this.options_endtime = []
      result.forEach(element => {
        this.options_endtime.push({label:element.name, value:element.name})
      });
    }
    )

    this.$http.get("http://localhost:8070/government-pro/sum/start_month"
      ).then((response)=>{
      let result = response.data
  
      this.options_startmonth = []
      result.forEach(element => {
        this.options_startmonth.push({label:element.name, value:element.name})
      });
    }
    )
    this.$http.get("http://localhost:8070/government-pro/sum/end_month"
      ).then((response)=>{
      let result = response.data
  
      this.options_endmonth = []
      result.forEach(element => {
        this.options_endmonth.push({label:element.name, value:element.name})
      });

    }
    )
    

    this.$http.get("http://localhost:8070/government-pro/sum/start_year"
      ).then((response)=>{
      let result = response.data
  
      this.options_startyear = []
      result.forEach(element => {
        this.options_startyear.push({label:element.name, value:element.name})
      });
    }
    )
    this.$http.get("http://localhost:8070/government-pro/sum/end_year"
      ).then((response)=>{
      let result = response.data
  
      this.options_endyear = []
      result.forEach(element => {
        this.options_endyear.push({label:element.name, value:element.name})
      });

    }
    )



    
    
  },


    methods:{
      getCity() {
      // 截取前4位
      const prefix = this.userId.substring(0, 4);

      // 查找对照表中的映射
      this.city = this.cityMapping[prefix] || '未知城市';
      },
      Sum_fun(){
        
          this.getCity()
          switch(this.value1){
            case('选项1'):this.choice_name = "企业性质" ,this.sum_id ="企业性质"
            break
            case('选项2'):this.choice_name = "所属行业",this.sum_id = "所属行业"
            break
            case('选项3'):this.choice_name = "调查期",this.sum_id = "调查期"
            break
            case('选项4'):this.choice_name = "企业地区",this.sum_id = "企业地区"
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
          if(this.show_month){
            this.start_time = this.value_startmonth
            this.end_time = this.value_endmonth

          }else if(this.show_research){
            this.start_time = this.value_start
            this.end_time = this.value_end
            
          }
          else if(this.show_year){
            let month = this.options_endmonth[this.options_endmonth.length - 1].label.substring(5,7)
            let month1 = this.options_endmonth[0].label.substring(5,7)
            if(this.value_startyear == this.options_endmonth[0].label.substring(0,4)){
              this.start_time = this.value_startyear + month1
            }else{
              this.start_time = this.value_startyear + "01"
            }

            if(this.value_endyear == this.options_endmonth[this.options_endmonth.length - 1].label.substring(0,4)){
              this.end_time = this.value_endyear + month
            }else{
              this.end_time = this.value_endyear + "12"
            }

            
            
            console.log(this.end_time)
            console.log(this.start_time)
          }
        
        
          this.$http.get("http://localhost:8070/government-city/sum",{
            params:{
              sum_id: this.sum_id,
              start_time:this.start_time,
              end_time:this.end_time,
              flag_front:this.flag,
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

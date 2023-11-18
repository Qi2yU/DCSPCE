<style>
</style>
<template>
  <div id="user">
    <h1>对比分析</h1>
    
    <el-select v-model="start_time" filterable clearable placeholder="起始调查期" >
      <el-option
        v-for="item in options_starttime"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select v-model="end_time" filterable clearable placeholder="结束调查期" >
      <el-option
        v-for="item in options_endtime"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
  
  


    <el-select v-model="value_char"  clearable placeholder="企业性质" >
      <el-option
        v-for="item in options_char"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
    <el-select v-model="value_indu" clearable placeholder="所属行业" >
      <el-option
        v-for="item in options_indu"
        :key="item.value"
        :label="item.label"
        :value="item.value"
       >
      </el-option>
    </el-select>
  
    <el-button type="primary" @click="get_data" :disabled="disable" class = "query" >查询</el-button>
    <div id="main" style="width: 1500px; height: 400px"></div>

  <el-table
    :data="tableData"
    class="Table"
    border
    height="250"
    style="width: 100%">
    <el-table-column
      prop="name"
      label="企业名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="a_change_num"
      label="调查期A岗位变化数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="a_less"
      label="调查期A岗位减少数">
    </el-table-column>
    <el-table-column
      prop="b_change_num"
      label="调查期B岗位变化数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="b_less"
      label="调查期B岗位减少数">
    </el-table-column>
    <el-table-column
      prop="a_change_precent"
      label="调查期A岗位变化占比"
      width="180">
    </el-table-column>
    <el-table-column
      prop="b_change_precent"
      label="调查期B岗位变化占比">
    </el-table-column>
  </el-table>

  

    
    <el-button type="primary" @click = download class = "download">导出折线图</el-button>
    <el-button type="primary" @click = DownloadHandler class = "download">导出表格</el-button>
  </div>
</template>

<script>
import FileSaver from "file-saver"
import XLSX from "xlsx"
import html2canvas from 'html2canvas'
export default {
  name: 'User',
  data(){
    return{
      chartData:[],
      tableData:[],
      userId:this.$http.userid,
      options_char:[],
   
      options_indu:[],
      options_starttime:[],
      options_endtime:[],
      start_time:'',
      end_time:'',
      city:'',

     
     
      value_char:'',
      value_indu:'',
      option:{},
      myChart:null,
      disable:false,
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

  mounted() {
       this.myChart = this.$echarts.init(document.getElementById("main"));


       this.$http.get("http://localhost:8070/government-pro/analy_tend/start_time"
      ).then((response)=>{
      let result = response.data
      console.log(result)
      this.options_starttime = []
      result.forEach(element => {
        this.options_starttime.push({label:element.name, value:element.name})
      });

    }
    )
    
    this.$http.get("http://localhost:8070/government-pro/analy_tend/end_time"
      ).then((response)=>{
        let result = response.data
        console.log(result)
      this.options_endtime = []
      result.forEach(element => {
        this.options_endtime.push({label:element.name, value:element.name})
      });
    }
    )


      this.$http.get("http://localhost:8070/government-pro/analy_compare/city"
      ).then((response)=>{
      let result = response.data
      this.options_city = []
      result.forEach(element => {
        this.options_city.push({label:element.name, value:element.name})
      });
  
    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_compare/char"
      ).then((response)=>{
      let result = response.data
      this.options_char = []
      result.forEach(element => {
        this.options_char.push({label:element.name, value:element.name})
      });

    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_compare/indu"
      ).then((response)=>{
      let result = response.data
      this.options_indu = []
      result.forEach(element => {
        this.options_indu.push({label:element.name, value:element.name})
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
   async get_data(){   
    await this.getCity()   
     await this.$http.get("http://localhost:8070/government-pro/analy_compare/get_data",{
        params:{
          start_time:this.start_time,
          end_time:this.end_time,
          city:this.city,
          character:this.value_char,
          industry:this.value_indu
        }
      }
      ).then(res=>{
        this.tableData = res.data      
      })
      await  this.$http.get("http://localhost:8070/government-pro/analy_compare/get_line"
      ).then(res=>{
        this.option = {
            xAxis: {
               type: 'category',
               data: ['建档期A', '调查期A', '建档期B', '调查期B']
            },
             yAxis: {
               type: 'value'
            },
            series: [{
              type:'line',
              data:res.data,
            }]
        }
        document.getElementById("main").setAttribute('_echarts_instance_', '')
        this.myChart.clear()
        this.myChart.setOption(this.option, true)
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
    download() {
      // 图表转换成canvas
      html2canvas(document.getElementById("main")).then(function (canvas) {
        var img = canvas
          .toDataURL("image/png")
          .replace("image/png", "image/octet-stream");
          // 创建a标签，实现下载
        var creatIMg = document.createElement("a");
        creatIMg.download = "图表.png"; // 设置下载的文件名，
        creatIMg.href = img; // 下载url
        document.body.appendChild(creatIMg);
        creatIMg.click();
        creatIMg.remove(); // 下载之后把创建的元素删除
      });
    },
   
},
watch:{
  end_time(){
      let cs = this.start_time.replace("年","")
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")
      cs = parseInt(cs)

      let es = this.end_time.replace("年","")
      es = es.replace("月第", "")
      es = es.replace("号调查期","") //202381
      es = parseInt(es)

      if(cs >= es ){
        this.show = true
      }
      else{
        this.show = false
      }
   

      
  },
  start_time(){
    let cs = this.start_time.replace("年","")
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")
      
      let es = this.end_time.replace("年","")
      es = es.replace("月第", "")
      es = es.replace("号调查期","")

      if(cs >= es ){
        this.show = true
      }
      else{
        this.show = false
      }
   

  }
    },

}
</script>
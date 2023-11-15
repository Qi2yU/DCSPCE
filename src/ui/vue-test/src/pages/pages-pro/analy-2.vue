<style>
</style>
<template>
  <div id="user">
    <h1>对比分析</h1>

    <el-date-picker
      v-model="value_date1"
      type="datetime"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期时间">
    </el-date-picker>

    <el-date-picker
      v-model="value_date2"
      type="datetime"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      placeholder="选择日期时间">
    </el-date-picker>
  
  

  <el-select v-model="value_city" clearable placeholder="地区" >
      <el-option
        v-for="item in options_city"
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
  
    <el-button type="primary" @click="get_data"  class = "query" >查询</el-button>
 

  <el-table
    :data="tableData"
    class="Table"
    border
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

  <div id="main" style="width: 600px; height: 400px"></div>

    
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

      options_char:[],
      options_city:[],
      options_indu:[],
      

      value_date1:'',
      value_date2:'',
     
      value_city:'',
      value_char:'',
      value_indu:'',
      option:{},
      myChart:null,

    }
  },

  mounted() {
       this.myChart = this.$echarts.init(document.getElementById("main"));
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

   async get_data(){      
     await this.$http.get("http://localhost:8070/government-pro/analy_compare/get_data",{
        params:{
          start_time:this.value_date1,
          end_time:this.value_date2,
          city:this.value_city,
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

}
</script>
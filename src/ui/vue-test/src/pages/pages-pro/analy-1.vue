<style>
</style>
<template>
  <div id="user">
    <h1>趋势分析</h1>


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
    <el-button type="primary"  @click = "get_data" :disabled="show" class = "download">查询</el-button>
    <div id="main" style="width: 1000px; height: 600px"></div>
    
    
    <el-table
            :data="tableData"
            :header-cell-style="{ 'font-size': '16px', color: '#1192ac' }"
            :cell-style="{ height: '44px', padding: '0px' }"
            style="width: 100%"
            class="Table"
            max-height="550"
            border
            small
          >
          <el-table-column
            prop="company_name"
            label="企业名"
            width="180"
            fixed = "left">
          </el-table-column>
            <div v-for="(item) in xAxislist" >
              <el-table-column :label="item" :prop="item" width="180">
              </el-table-column>
            </div>
    </el-table>




    
   
    <el-button type="primary" @click = exportExcel class = "download">导出折线图</el-button>
    <el-button type="primary" @click = DownloadHandler class = "download">导出表格</el-button>
  </div>

</template>

<script>

import FileSaver from "file-saver"
import XLSX from "xlsx"
import html2canvas from 'html2canvas'
import ExcelJS from 'exceljs'; 

export default {
  name: 'User',
  data(){
    return{
      value_city:'',
      value_char:'',
      value_indu:'',

      option:{},

      myChart:null,
      xAxislist:[],
      legend:[],

      series:[],
      value_date:'',
      tableData:[],
      myChart:null,


      options_char:[],
      options_city:[],
      options_indu:[],
      options_starttime:[],
      options_endtime:[],
      start_time:'',
      end_time:'',
      show:false,
      


      
    }
  },
  methods:{

    async  get_data(){
 
      await this.$http.get("http://localhost:8070/government-pro/analy_tend/get_time",{
        params:{
          start_time:this.start_time,
          end_time:this.end_time,
        }
      }).then(response=>{
        this.xAxislist = response.data
      })
      //2024年2月第2号调查期

      await this.$http.get("http://localhost:8070/government-pro/analy_tend/get_data",{
        params:{
          start_time:this.start_time,
          end_time:this.end_time,
          city:this.value_city,
          character:this.value_char,
          industry:this.value_indu
        }
      }
      ).then((response)=>{
        this.myChart.clear()
        let result = response.data
        let ob = {
          name:"",
          data:[],
          type:'line',
        }
        this.series = []
        result.forEach(element => {
   

          this.series.push({name:element.company_name, data:element.now_num_list, type:'line'})
          this.legend.push(element.company_name)
        });

        let option = {
          tooltip: {
          trigger: 'axis',
          formatter:function(params){
            let res = params[0].axisValueLabel;

            function getHtml(param){
                let str = '<div style="float: left"><span style="background: '+param.color+'; width: 11px; height: 11px; border-radius: 11px;float: left; margin: 5px 3px;"></span>'+
                param.seriesName+':'+param.data+'&emsp;&emsp;</div>';
                return str;
            }

            let flag=false;
            res += '<div style="clear: both">';
            for (let i = 0; i < params.length; i++) {
                res += getHtml(params[i]);
                if (params.length>11 && i%2==1){
                    res += '</div><div style="clear: both">';
                }
                if (params.length <=11){
                    res += '</div><div style="clear: both">';
                }
            }
            res += "</div>";
            return res;
        }
          },

          legend: {//顶部每条折线的标识的配置项
           icon: "circle",   //    改变它的icon circle，rect ，roundRect，triangle
           type: 'scroll',
           itemWidth:8,  // 设置它的宽度
           itemHeight:8, // 设置它的高度
           itemGap:20, // 设置它的间距
          // orient: 'vertical',  //vertical是竖着显示 ，默认是横着
          // left: '70%',   //距离左边70%，也可用left,center,right
           y: '1%',   //距离顶部的距离，也可以用center
          // textStyle:{    //icon后面的文字设置
          //   fontSize: 18,//字体大小
          //   color: '#ffffff'//字体颜色
          // }
        },
          xAxis:{
            name: "调查期",
            type:'category',
            data:this.xAxislist,
            axisLabel:{
              show: true,
              interval: 0,//使x轴文字显示全
              textStyle: {},
              formatter: function(params) {
              var newParamsName = "";
              var paramsNameNumber = params.length;
              var provideNumber = 3; //一行显示几个字
              var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
              if (paramsNameNumber > provideNumber) {
                for (var p = 0; p < rowNumber; p++) {
                  var tempStr = "";
                  var start = p * provideNumber;
                  var end = start + provideNumber;
                  if (p == rowNumber - 1) {
                    tempStr = params.substring(start, paramsNameNumber);
                  } else {
                    tempStr = params.substring(start, end) + "\n";
                  }
                  newParamsName += tempStr;
                }
              } else {
                newParamsName = params;
              }
              return newParamsName;
            }
            }
          },
          yAxis:{
            type:'value',
            scale:true,
          },
          series:this.series,
        }
        this.myChart.setOption(option)
    })

    
    await this.$http.get("http://localhost:8070/government-pro/analy_tend/get_table",{

      }).then(response=>{
        console.log(response)
        let result = response.data
        this.tableData = []
        let len = this.xAxislist.length
        result.forEach(element => {
          let obj = {
            company_name:'',
          }
          obj.company_name = element.company_name
          for(let i = 0; i < len; i++){
            let time = element.time_list[i]
            let data = element.table_percent_list[i]
            obj[time] = data
          }
          console.log(obj)
        this.tableData.push(obj)
      });
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
          //  name+'.xlsx'表示导出的excel表格名字
        FileSaver.saveAs(
            new Blob([wbout],  { type: "application/octet-stream" }),
            name + ".xlsx"
        );
    
        return wbout;
        },  
    exportExcel() {
      const workbook = new ExcelJS.Workbook(); // 创建工作簿
      const worksheet = workbook.addWorksheet('Sheet1'); // 添加工作表

      const chart = echarts.getInstanceByDom(this.$refs.myChart) // 获取图表实例
      const base64Image = chart.getDataURL({
        pixelRatio: 2, // 导出图片的分辨率比例，默认为1，即图片的分辨率为屏幕分辨率的一倍
        backgroundColor: '#fff' // 导出图片的背景色
      })
      let image= workbook.addImage({ // 添加图片
          base64: base64Image, // 图片的base64编码
          extension: 'png'  // 图片的扩展名
        });
      worksheet.addImage(image, 'A1:J20'); // 将图片添加到工作表中
      workbook.xlsx.writeBuffer().then(function (buffer) { // 生成excel文件的二进制数据
      saveAs.saveAs(new Blob([buffer], {  // 生成Blob对象
          type: 'application/octet-stream'  // 指定文件的MIME类型
        }), 
        'xchart.xlsx');  // 指定文件名
      });
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

  mounted() {
      this.myChart = this.$echarts.init(document.getElementById("main"));
      this.$http.get("http://localhost:8070/government-pro/analy_tend/city"
      ).then((response)=>{
      let result = response.data
      this.options_city = []
      result.forEach(element => {
        this.options_city.push({label:element.name, value:element.name})
      });
  
    }
    )
    this.$http.get("http://localhost:8070/government-pro/analy_tend/char"
      ).then((response)=>{
      let result = response.data
      this.options_char = []
      result.forEach(element => {
        this.options_char.push({label:element.name, value:element.name})
      });

    }
    )

    this.$http.get("http://localhost:8070/government-pro/analy_tend/indu"
      ).then((response)=>{
      let result = response.data
      this.options_indu = []
      result.forEach(element => {
        this.options_indu.push({label:element.name, value:element.name})
      });

    }
    )

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

},
watch:{
  end_time(){
      let cs = this.start_time.replace("年","")
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")

      let es = this.end_time.replace("年","")
      es = es.replace("月第", "")
      es = es.replace("号调查期","")

      if(cs >= es || cs.length > es.length){
        this.show = true
      }
      else{
        this.show = false
      }
           
      if(cs.length < es.length){
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

      if(cs >= es || cs.length > es.length){
        this.show = true
      }
      else{
        this.show = false
      }

      if(cs.length < es.length){
        this.show = false
      }
  }
},
}
</script>
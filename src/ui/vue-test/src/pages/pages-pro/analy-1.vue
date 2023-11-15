<style>
</style>
<template>
  <div id="user">
    <h1>趋势分析</h1>

    <el-date-picker
      v-model="value_date"
      type="datetimerange"
      format="yyyy-MM-dd "
      value-format="yyyy_MM_dd"
      align="right"
      unlink-panels
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      >
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
    <el-button type="primary"  @click = "get_data"  class = "download">查询</el-button>
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
      value_city:'',
      value_char:'',
      value_indu:'',

      option:{},

      myChart:null,
      xAxislist:[],
      legend:[],
      // series:{
      //   type:Array,
      //   default(){
      //     return [];
      //   },
      // },
      series:[],
      value_date:'',
      tableData:[],
      myChart:null,
      start_time:'',
      end_time:'',

      options_char:[],
      options_city:[],
      options_indu:[],


      
    }
  },
  methods:{
    async  get_data(){
      this.start_time = this.value_date[0]
      this.end_time = this.value_date[1]
      await this.$http.get("http://localhost:8070/government-pro/analy_tend/get_time",{
        params:{
          start_time:this.start_time,
          end_time:this.end_time,
        }
      }).then(response=>{
        this.xAxislist = response.data
  

      })

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
          legend: {//顶部每条折线的标识的配置项
           icon: "circle",   //    改变它的icon circle，rect ，roundRect，triangle
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
            type:'category',
            data:this.xAxislist,
            axisLabel:{
              show: true,
              interval: 0,//使x轴文字显示全
              textStyle: {
               
              },
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
    // this.$http.get("http://localhost:8070/government-pro/analy_tend/get_x"
    //   ).then((response)=>{
    //   let result = response.data
    //   this.x_axis = []
    //   result.forEach(element => {
    //     this.x_axis.push(element)
    //   });

    // }
    // )
},
}
</script>
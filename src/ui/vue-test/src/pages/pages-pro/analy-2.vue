<style scoped> 
.title_main{
  color: #409EFF;
  font-size: 20px Extra large;
  text-align: center;
}
.choice{
  text-align: center;
  border-style:hidden;
}
.button-container{
  text-align: center;
}
.button-container_down{
  text-align: center;
}
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }


</style>
<template>
  <div id="pro">
    <h1 class="title_main" style="color: black; text-align: center; font-size: xx-large;">对比分析界面</h1>
    <el-divider></el-divider>
  <div class="choice">
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
  </div>
  
  
  <div class="button-container">
    <el-button type="primary" @click="get_data" :disabled="show" class = "query" >查询</el-button>
  </div>

  <el-row>
  <el-col :span="24"><div class="grid-content bg-purple-dark"></div></el-col>
  </el-row>

    <div id="main" style="width: 1500px; height: 400px; text-align: center;"></div>
    <el-row>
  <el-col :span="24"><div class="grid-content bg-purple-dark"></div></el-col>
  </el-row>

  <el-table
  :data="tableData"
            :header-cell-style="{ 'font-size': '16px', color: '#1192ac' }"
            :cell-style="{ height: '44px', padding: '0px' }"
            style="width: 83%; left: 10%;"
            class="Table"
            max-height="550"
            border
            small>
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
      prop="a_change_precent"
      label="调查期A岗位变化占比"
      width="180">
    </el-table-column>
    
    <el-table-column
      prop="b_change_num"
      label="调查期B岗位变化数"
      width="180">
    </el-table-column>

    <el-table-column
      prop="b_change_precent"
      label="调查期B岗位变化占比">
    </el-table-column>

    <el-table-column
      prop="ab_change"
      label="调查期岗位同比变化">
    </el-table-column>

    <el-table-column
      prop="ab_percent"
      label="调查期岗位同比变化率">
    </el-table-column>


  </el-table>

  

  <div class="button-container_down">
    <el-button type="primary" @click = downloadall class = "download">导出图表</el-button>
  </div>
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
      options_starttime:[],
      options_endtime:[],
      start_time:'',
      end_time:'',
      

     
      value_city:'',
      value_char:'',
      value_indu:'',
      option:{},
      myChart:null,
      show:true,

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

   async get_data(){      
     await this.$http.get("http://localhost:8070/government-pro/analy_compare/get_data",{
        params:{
          start_time:this.start_time,
          end_time:this.end_time,
          city:this.value_city,
          character:this.value_char,
          industry:this.value_indu
        }
      }
      ).then(res=>{
        this.tableData = res.data  
        console.log(this.tableData)    
      })
      await  this.$http.get("http://localhost:8070/government-pro/analy_compare/get_line"
      ).then(res=>{
        this.option = {
          title:{
        show:true,//false
        text:"就业人数",//主标题文本


        textAlign:'auto',//整体（包括 text 和 subtext）的水平对齐
        textVerticalAlign:'auto',//整体（包括 text 和 subtext）的垂直对齐
        padding:0,//[5,10] | [ 5,6, 7, 8] ,标题内边距
        left:100,//'5' | '5%'，title 组件离容器左侧的距离
        right:'auto',//'title 组件离容器右侧的距离
        top:10,//title 组件离容器上侧的距离
        bottom:'auto',//title 组件离容器下侧的距离

        },
          tooltip: {
          trigger: 'axis',
          formatter:function(params){
            let res = params[0].axisValueLabel;

            function getHtml(param){
                let str = '<div style="float: left"><span style="background: '+param.color+'; width: 11px; height: 11px; border-radius: 11px;float: left; margin: 5px 3px;"></span>'+param.data+'&emsp;&emsp;</div>';
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
    downloadall(){
      this.download()
      this.DownloadHandler()
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
      let cs
      if(this.start_time.length == 13){
         cs = this.start_time.replace("年","0")
      }
      else{
         cs = this.start_time.replace("年","")
      }
      
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")
      cs = parseInt(cs)

      let es
      if(this.end_time.length == 13){
         es = this.end_time.replace("年","0")
      }
      else{
         es = this.end_time.replace("年","")
      }
      es = es.replace("月第", "")
      es = es.replace("号调查期","") //2024011 2023111
      
      es = parseInt(es)
  
      if(cs >= es ){
        this.show = true
      }
      else{
        this.show = false
      }
      if(!es || !cs){
          this.show = true
      }
   

      
  },
  start_time(){
      let cs 
      if(this.start_time.length == 13){
         cs = this.start_time.replace("年","0")
      }
      else{
         cs = this.start_time.replace("年","")
      }
      
      cs = cs.replace("月第", "")
      cs = cs.replace("号调查期","")
      cs = parseInt(cs)


      let es 
      if(this.end_time.length == 13){
         es = this.end_time.replace("年","0")
      }
      else{
         es = this.end_time.replace("年","")
      }
      es = es.replace("月第", "")
      es = es.replace("号调查期","") //2024011 2023111
      es = parseInt(es)

      if(cs >= es ){
        this.show = true
      }
      else{
        this.show = false
      }
      if(!es || !cs){
          this.show = true
      }
   

  }
    },

}
</script>
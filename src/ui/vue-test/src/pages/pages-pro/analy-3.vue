<style>
</style>


<template>
  <div id="user">
    <h1>取样分析</h1>

    <el-select v-model="value" clearable @clear="delValue()" placeholder="地区选择">
      <el-option
        v-for="item in options_city"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
    <el-button type="success"  @click = "Query_fun()">查询</el-button>

    <el-table
    ref="multipleTable"
    :data="showData"
    class="Table"
    border
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="name"
      label = "地区"
      width="180">
    </el-table-column>
    <el-table-column
      prop="value"
      label="企业数量">
    </el-table-column>

  </el-table>



  <div id="main" style="width: 600px; height: 400px"></div>
  <el-button type="primary"  @click = "download"  class = "download">导出饼图</el-button>
  <el-button type="primary"  @click = "DownloadHandler"  class = "download">导出表格</el-button>
  </div>
  
</template>

<script>
import choice_button from '../../components/components-pro/analy3_components/analy3_buttons.vue'
import FileSaver from "file-saver"
import XLSX from "xlsx"
import html2canvas from 'html2canvas'
export default {
  data(){
    return{
      showData:[],
      tableData:[],
      chartData:[],
      options_city:[],
      value:"",
      multipleSelection:[],
      myChart:null,
      option:{},

      chancle_arry:[],

      flag:true,
      change_when:false,
      query_flag : false,
      query_change : true,
      flagfordata: true,
    }
  },
  components:{
    choice_button,
  },
 mounted(){
   this.$http.get("http://localhost:8070/government-pro/sample/mounted").
      then((response)=>{ 
          this.tableData = response.data
          this.showData = this.tableData
          this.chartData = this.tableData
          this.multipleSelection = this.tableData
          this.drawChart();
          this.setOptions_city();
          this.defaultSelection();
          
      })
      
    
  
  },

  methods: {



    defaultSelection(){
      
      this.$nextTick(()=>{
        this.$refs.multipleTable.toggleAllSelection()
      })
    },

    handleSelectionChange(val) {

        let old_size = this.multipleSelection.length
        let new_size = val.length
        const old_arry = this.multipleSelection.map(item=>item.name)
        const new_arry = val.map(item=>item.name)

        this.multipleSelection = val;
        if(this.flag){
          console.log(old_size)
          console.log(new_size)
          if(old_size > new_size && this.flagfordata){
            const result = old_arry.filter(item => !new_arry.includes(item))
            this.chancle_arry.push(result)
            console.log(this.chancle_arry)
            
          }else{
            let result = new_arry.filter(item => !old_arry.includes(item))
            result = result.toString()
            this.flagfordata = true
            for(let i = 0; i < this.chancle_arry.length;i++){
              if(this.chancle_arry[i] == result){
                this.chancle_arry.splice(i,1)
              }
            }        
            
          }
          this.chartData = val  
          
          this.drawChart();
        }
    },
    Query_fun(){
      if(this.value == ""){
        return
      }
      else{
        let name = Object.values(this.options_city.at(this.value - 1))[0]
        let obj = this.tableData.filter((data_single)=>Object.values(data_single)[0]  == name)
        this.showData = obj
      }

      let flag = true
      let name = Object.values(this.showData.at(0))[0]      
      for(let i = 0; i < this.chancle_arry.length;i++){
          if(name == this.chancle_arry[i]){
            flag = false
          }
      } 
      if(flag){
        this.query_change = true
        this.defaultSelection()
       }
      else{
        this.query_change = false
      }
      
      this.flag = false
      
   
     
    },
    setOptions_city(){

      for(let i = 0;i < this.tableData.length ; i++){
       
        var name = this.tableData[i]["name"]
        let obj = {"label": name, "value" : i+1}
     
        this.options_city.push(obj)
        
    
        
      }
    },
    delValue(){
      this.showData = this.tableData
      
      this.chartData = this.tableData


      this.defaultSelection()
      this.chancle_arry = []
      this.flag = true
      this.flagfordata = false
      this.query_flag = false
      
      this.value = "";
  
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
    drawChart(){
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
       this.myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据  
      this.option = {
            series: [
                {
                    type: 'pie',
                    data: this.chartData
                }
            ]
        }
      // 使用刚指定的配置项和数据显示图表。
      document.getElementById("main").setAttribute('_echarts_instance_', '')
      this.myChart.clear()
      this.myChart.setOption(this.option, true)
    },
  },
  watch:{
    multipleSelection(new_arry,old_arry){
      if(new_arry.length == 0 && old_arry.length == 1 && this.flag == false && this.query_change == true ){
        console.log("取消一个")
        let flag = true
        let name = Object.values(this.showData.at(0))[0]
        
        for(let i = 0; i < this.chancle_arry.length;i++){
          if(name == this.chancle_arry[i]){
            flag = false
          }
        }
        if(this.change_when){
          flag = true
        }
        if(flag){
          let index = this.chartData.indexOf(this.showData[0])
          this.chartData.splice(index,1)
          this.query_change = false
          this.drawChart()
        }

      }

      if(new_arry.length == 1 && old_arry.length == 0 && this.query_change == false){
        console.log("增加一个")
        this.query_change = true
        this.change_when = true
        this.chartData.push(this.showData[0])
        this.drawChart()
      }
    },
    chancle_arry(new_arry,old_arry){

  

    },
  }
}
</script>
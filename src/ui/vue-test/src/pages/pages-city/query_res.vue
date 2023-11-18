<template>
  <div>
      <h2>查询结果</h2>
      <el-button size="medium" type="danger" @click="back" >清空</el-button>
      <el-button size="medium" type="primary" @click="exportToExcel" style="float: right;">导出</el-button>

      <el-table
     
    :data="tableData"
    
    style="width: 100%">
    <el-table-column type="expand" >
      <template slot-scope="props" >
        <el-form label-position="left" inline class="demo-table-expand" >
          
          <el-form-item label="企业性质">
            <span>{{ props.row.character }}</span>
          </el-form-item>
          <el-form-item label="所属行业">
            <span>{{ props.row.industry }}</span>
          </el-form-item>
          <!-- <el-form-item label="调查期">
            <span>{{ props.row.time }}</span>
          </el-form-item> -->
          <el-form-item label="建档期就业人数">
            <span>{{ props.row.lastNum }}</span>
          </el-form-item>
          <el-form-item label="调查期就业人数">
            <span>{{ props.row.nowNum }}</span>
          </el-form-item>
          <el-form-item label="就业人数减少类型">
            <span>{{ props.row.decReason }}</span>
          </el-form-item>
          <el-form-item label="主要原因">
            <span>{{ props.row.mainReason }}</span>
          </el-form-item>
          
          <el-form-item label="次要原因">
            <span>{{ props.row.secReason }}</span>
          </el-form-item>
          <el-form-item label="次要原因说明">
            <span>{{ props.row.explain }}</span>
          </el-form-item>
          
          
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="企业编号"
      prop="userId">
    </el-table-column>
    <el-table-column
      label="企业名称"
      prop="companyName">
    </el-table-column>
    <el-table-column
      label="市"
      prop="city">
    </el-table-column>
    <el-table-column
      label="区县"
      prop="district">
    </el-table-column>
    <el-table-column
      label="调查期起始时间"
      prop="start_time">
    </el-table-column>
    <el-table-column
      label="调查期结束时间"
      prop="end_time">
    </el-table-column>
  </el-table>
      <!-- <el-table
      :data="tableData"
      border
      style="width: 100%">
          <el-table-column
              prop="userId"
              label="企业编号"
              width="200">
          </el-table-column>
          <el-table-column
              prop="companyName"
              label="企业名称"
              width="288">
          </el-table-column>
          <el-table-column
              prop="start_time"
              label="调查期开始时间"
              width="180">
          </el-table-column>
          <el-table-column
              prop="end_time"
              label="调查期结束时间"
              width="180">
          </el-table-column>
          <el-table-column
              prop="tableName"
              label="调查期编号"
              width="180">
          </el-table-column>
          <el-table-column
               label="操作"
               width="100">
              <template slot-scope="scope">
                  <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              </template>
          </el-table-column>
      </el-table> -->
  </div>
  
</template>

<script>
import * as XLSX from 'xlsx';
  export default {
    created(){
      console.log("来了")
      console.log(this.date1);
      this.$http.post("/dataquery",{
          
              companyName:this.companyName,
              userId:this.userId,
              character:this.character,
              industry:this.industry,
              city:this.city,
              district:this.district,
              start_time:this.date1,
              end_time:this.date2
          
      }).then((response)=>{
          console.log(response);
          this.tableData=response.data;
  });
    },
    data() {
      return {
        companyName:this.$route.query.companyName,
        userId:this.$route.query.userId,
        character:this.$route.query.character,
        industry:this.$route.query.industry,
        city:this.$route.query.city,
        district:this.$route.query.district,
        date1:this.$route.query.date1,
        date2:this.$route.query.date2,
        tableData:[],
        // tableData: [{
        //   num: '1',
        //   name: 'c1',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // }, {
        //   num: '2',
        //   name: 'c2',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // }, {
        //   num: '3',
        //   name: 'c3',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // },{
        //   num: '4',
        //   name: 'c4',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // },{
        //   num: '5',
        //   name: 'c5',
        //   stime: '2023-9-9',
        //   etime: '2023-10-9'
        // }]
      }
    },
    methods: {
      exportToExcel() {
// 获取要导出的数据
const data = this.tableData.map(item => [item.userId, item.companyName, item.city,item.district,item.character,item.industry,item.start_time,item.end_time,item.lastNum,item.nowNum,item.decReason,item.mainReason,item.secReason,item.explain]);

const header =['企业编号','企业名称','市','区县','企业性质','所属行业','调查期起始时间','调查期结束时间','建档期就业人数','调查期就业人数','就业人数减少类型','主要原因','次要原因','次要原因说明'];
const exportData = [header, ...data];
// 创建一个Workbook对象
const ws = XLSX.utils.aoa_to_sheet(exportData);

// 创建一个Workbook
const wb = XLSX.utils.book_new();
XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

// 将Workbook对象写入到文件
XLSX.writeFile(wb, 'exported_data.xlsx');
},
    handleClick(row) {
      console.log(row);
      this.$router.push(
        {path:'/government-pro/queryres2',
      query:{
          tableName:row.tableName,
          userId:row.userId,
          
      }}
      );
    },
    back(){
      this.$router.back();
    }
    
  },
  beforeMount(){
    console.log("准备重新渲染了");
    
  },
  

  }
</script>
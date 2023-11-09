<template>
  <div>
      <h2>未上报</h2>
      <el-table
  v-if="tag2"
  :data="tableData2"
    style="width: 100%">
    <el-table-column
      width="50">
    </el-table-column>
      <el-table-column
      label="企业编号"
      prop="companyId">
    </el-table-column>
    <el-table-column
      label="企业名称"
      prop="companyName">
    </el-table-column>
    <el-table-column
      label="状态"
      prop="state">
    </el-table-column>
    <el-table-column
      label="联系电话"
      prop="phone">
    </el-table-column>
  </el-table>

      <el-table
     v-if="tag1" 
    :data="tableData"
    :show-header="false"
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="企业名称">
            <span>{{ props.row.companyName }}</span>
          </el-form-item>
          <el-form-item label="企业编号">
            <span>{{ props.row.companyId }}</span>
          </el-form-item>
          <el-form-item label="调查期">
            <span>{{ props.row.time }}</span>
          </el-form-item>
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
          <el-form-item label="主要原因说明">
            <span>{{ props.row.mdesc }}</span>
          </el-form-item>
          <el-form-item label="次要原因">
            <span>{{ props.row.secReason }}</span>
          </el-form-item>
          <el-form-item label="次要原因说明">
            <span>{{ props.row.explain }}</span>
          </el-form-item>
          <el-form-item label="第三原因">
            <span>{{ props.row.treason }}</span>
          </el-form-item>
          <el-form-item label="第三原因说明">
            <span>{{ props.row.tdesc }}</span>
          </el-form-item>
          <el-form-item label="退回修改建议">
            <span>{{ props.row.adv }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="企业编号"
      prop="companyId">
    </el-table-column>
    <el-table-column
      label="企业名称"
      prop="companyName">
    </el-table-column>
    <el-table-column
      label="状态"
      prop="state">
    </el-table-column>
    <el-table-column
      label="联系电话"
      prop="phone">
    </el-table-column>
  </el-table>

  
  </div>
  
</template>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 100px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>

<script>
  export default {
    created(){
      
  
  this.$http.get("/retreat",{
          params:{
              city: this.city,
          }
      }).then((response)=>{
          this.tableData=response.data;
          if(this.tableData.length==0) this.tag1=false;
          console.log(response);
  });
  this.$http.get("/uncommittedoncity",{
          params:{
              city: this.city,
          }
      }).then((response)=>{
          this.tableData2=response.data;
          if(this.tableData2.length==0) this.tag2=false;
          console.log(response);
  });
  
    },
    data() {
      return {
        city:this.$route.query.city,
        tableData:[],
        tableData2:[],
        tag1:true,
        tag2:true
        // tableData: [{
        //   name:"c1",
        //   id:"1",
        //   time:"调查期1",
        //   oldnum:"300",
        //   newnum:"240",
        //   category:"",
        //   mreason:"自然减员",
        //   mdesc:"无",
        //   sreason:"产业结构调整",
        //   sdesc:"无",
        //   treason:"退休",
        //   tdesc:"无",
        //   state:"退回修改",
        //   tele:"18811297897",
        //   adv:"原因有误",
        // }, {
        //   name:"c2",
        //   id:"2",
        //   time:"调查期1",
        //   oldnum:"300",
        //   newnum:"240",
        //   category:"",
        //   mreason:"自然减员",
        //   mdesc:"无",
        //   sreason:"产业结构调整",
        //   sdesc:"无",
        //   treason:"退休",
        //   tdesc:"无",
        //   state:"退回修改",
        //   tele:"18811297897",
        //   adv:"原因有误",
        // }, {
        //   name:"c3",
        //   id:"3",
        //   time:"调查期1",
        //   oldnum:"300",
        //   newnum:"240",
        //   category:"",
        //   mreason:"自然减员",
        //   mdesc:"无",
        //   sreason:"产业结构调整",
        //   sdesc:"无",
        //   treason:"退休",
        //   tdesc:"无",
        //   state:"退回修改",
        //   tele:"18811297897",
        //   adv:"原因有误",
        // }, {
        //   name:"c4",
        //   id:"4",
        //   time:"调查期1",
        //   oldnum:"300",
        //   newnum:"240",
        //   category:"",
        //   mreason:"自然减员",
        //   mdesc:"无",
        //   sreason:"产业结构调整",
        //   sdesc:"无",
        //   treason:"退休",
        //   tdesc:"无",
        //   state:"退回修改",
        //   tele:"18811297897",
        //   adv:"原因有误",
        // }],
        
        // tableData2:[
        //   {
        //   name:"c5",
        //   id:"5",
        //   time:"调查期1",
        //   state:"未上报",
        //   tele:"18811297897",
        //   mreason:"自然减员",
        //   mdesc:"无",
        //   sreason:"产业结构调整",
        //   sdesc:"无",
        //   treason:"退休",
        //   tdesc:"无",
        //   },
        //   {
        //   name:"c6",
        //   id:"6",
        //   time:"调查期1",
        //   state:"未上报",
        //   tele:"18811297897",
        //   mreason:"自然减员",
        //   mdesc:"无",
        //   sreason:"产业结构调整",
        //   sdesc:"无",
        //   treason:"退休",
        //   tdesc:"无",
        //   },
        // ],
    
      }

    },
      beforeMount(){
        console.log("asd");
        console.log(this.tableData);
      }
      
    
    
  }
</script>
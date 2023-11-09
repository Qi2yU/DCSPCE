<template>
    <div>   
        <el-descriptions title="备案审核详细信息"  :column="2" border>
            <el-descriptions-item label="企业名称">{{item.company_name}}</el-descriptions-item>
            <el-descriptions-item label="组织机构代码">{{item.user_id}}</el-descriptions-item>
            <el-descriptions-item label="联系人">{{item.linksman}}</el-descriptions-item>
            <el-descriptions-item label="联系电话">{{item.phone }}</el-descriptions-item>
            <el-descriptions-item label="所属地区">{{item.city}}</el-descriptions-item>
            <el-descriptions-item label="联系地址">{{item.linaddress}}</el-descriptions-item>
            <el-descriptions-item label="邮政编码">{{item.post_num}}</el-descriptions-item>
            <el-descriptions-item label="企业性质">{{item.company_character}}</el-descriptions-item>
            <el-descriptions-item label="所属行业">{{item.company_industry}}</el-descriptions-item>
            <el-descriptions-item label="传真">{{item.fax_num}}</el-descriptions-item>
            <el-descriptions-item label="主要经营业务">{{item.company_business}}</el-descriptions-item>
            <el-descriptions-item label="Email">{{item.email}}</el-descriptions-item>
        </el-descriptions>
        
        <br>
        <!-- <el-row v-if="flag=''" type="flex" justify="end" ></el-row> -->
        <el-row v-if="flag==='待审核'" type="flex" justify="end">
            <el-button type="primary" size="medium" @click="pass()" :disabled="flag3">审核通过</el-button>
            <el-button type="danger" size="medium" @click="retreat()" :disabled="flag3">退回修改</el-button>
        </el-row>
        <el-row v-else-if="flag==='已通过'" type="flex" justify="end" >状态：审核通过</el-row>
        <el-row v-else-if="flag==='被驳回'" type="flex" justify="end" >状态：被驳回</el-row>
        <el-row v-else-if="flag==='未上报'" type="flex" justify="end" >状态：未上报</el-row>
        <el-row v-if="flag3">
            <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入内容"
                v-model="textarea">
            </el-input>
            <el-button type="info" plain style="float: right" @click="advise()">提交</el-button>
        </el-row>
        <el-row>
            
        </el-row>
    </div>
</template>

<script>
import axios from 'axios';
export default{

        
    
    data()
    {
        return{
               item: {},
                flag:this.$route.query.flag,
                id:this.$route.query.id,
                flag3:false,
                textarea:''       
        }
    },

    created:function(){
        console.log("调用axios");
      axios.post("http://localhost:8090/company_info/single",
      this.$route.query.id).then((response)=>{
        this.item = response.data;})
    },

    methods:{
        pass(){
            this.flag='已通过';
            console.log(this.flag);
        },
        retreat(){
            this.flag3=true;
        },
        advise(){
            this.$router.back();
        }
    }
}
</script>

<style>
  .el-row {
    margin-top: 20px;
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }</style>
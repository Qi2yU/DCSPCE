<template>
    <div>   
        <el-descriptions title="备案审核详细信息"  :column="2" border>
            <el-descriptions-item label="企业名称">{{item.name}}</el-descriptions-item>
            <el-descriptions-item label="组织机构代码">{{item.id}}</el-descriptions-item>
            <el-descriptions-item label="联系人">{{item.contact}}</el-descriptions-item>
            <el-descriptions-item label="联系电话">{{item.telephone }}</el-descriptions-item>
            <el-descriptions-item label="所属地区">{{item.region}}</el-descriptions-item>
            <el-descriptions-item label="联系地址">{{item.address}}</el-descriptions-item>
            <el-descriptions-item label="邮政编码">{{item.code}}</el-descriptions-item>
            <el-descriptions-item label="企业性质">{{item.charactor}}</el-descriptions-item>
            <el-descriptions-item label="所属行业">{{item.belong}}</el-descriptions-item>
            <el-descriptions-item label="传真">{{item.fax}}</el-descriptions-item>
            <el-descriptions-item label="主要经营业务">{{item.business}}</el-descriptions-item>
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
export default{
    data()
    {
        return{
               item: {
                    name:"xxxx公司",
                    id:"111111",
                    contact:"张三",
                    telephone:"1234567890",
                    region:"昆明市",
                    address:"昆明市xx路xx号",
                    code:"650000",
                    charactor:"私企",
                    belong:"制造业",
                    fax:"12345678",
                    business:"服装制造",
                    email:"123456@mail",
                },
                flag:this.$route.query.flag,
                flag3:false,
                textarea:''       
        }
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
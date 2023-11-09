<template>
    <div>
        <el-button v-if="flag2==='未上报'" type="primary" size="medium" style="float: right" @click="commit" :disabled="flag1==='未审核'">上报</el-button>
        <el-button v-else round style="float: right" :disabled="true">已上报</el-button>
    
        <el-descriptions title="详细信息"  :column="2" border>
            <el-descriptions-item label="企业名称">{{item.userName}}</el-descriptions-item>
            <el-descriptions-item label="企业编号">{{item.userId}}</el-descriptions-item>
            <el-descriptions-item label="调查期">{{item.time}}</el-descriptions-item>
            <el-descriptions-item label="建档期就业人数">{{ item.oldNum }}</el-descriptions-item>
            <el-descriptions-item label="调查期就业人数">{{item.nowNum}}</el-descriptions-item>
            <el-descriptions-item label="就业人数减少类型">{{item.decReason}}</el-descriptions-item>
            <el-descriptions-item label="主要原因">{{item.mainReason}}</el-descriptions-item>
            <el-descriptions-item label="主要原因说明">{{item.mdesc}}</el-descriptions-item>
            <el-descriptions-item label="次要原因">{{item.secReason}}</el-descriptions-item>
            <el-descriptions-item label="次要原因说明">{{item.explain}}</el-descriptions-item>
            <el-descriptions-item label="第三原因">{{item.treason}}</el-descriptions-item>
            <el-descriptions-item label="第三原因说明">{{item.tdesc}}</el-descriptions-item>
        </el-descriptions>

        <el-row v-if="flag1==='未审核'" type="flex" justify="end">
            <el-button type="primary" size="medium" @click="pass" :disabled="flag3">审核通过</el-button>
            <el-button type="danger" size="medium" @click="retreat" :disabled="flag3">退回修改</el-button>
        </el-row>
        <el-row v-else type="flex" justify="end" >状态：审核通过</el-row>
        <el-row v-if="flag3">
            <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入内容"
                v-model="textarea">
            </el-input>
            <el-button type="info" plain style="float: right" @click="advise">提交</el-button>
            <el-button type="info" plain style="float: right" @click="quit">取消</el-button>
        </el-row>
        <el-row>
            
        </el-row>
    </div>
</template>

<script>
export default{
    created(){
        this.$http.get("/detail",{
            params:{
                userID: this.userID,
            }
        }).then((response)=>{
            this.item=response.data;
    });
   
    },
    data()
    {
        return{
                  item:{},
            //    item: {
            //         useruserName:"c1",
            //         userId:"1",
            //         time:"调查期1",
            //         oldNum:"300",
            //         nowNum:"240",
            //         decReason:"",
            //         mainReason:"自然减员",
            //         mdesc:"爱的飒飒的煎熬搜代价是的拉升了打开时间来得及阿里斯顿看见啊临时端口骄傲了三大",
            //         secReason:"产业结构调整",
            //         explain:"撒登记卡山东矿机爱仕达看见啊收到货卡接收端和看撒湿哒哒红色经典",
            //         treason:"退休",
            //         tdesc:"安徽省金卡号圣诞节看哈数控刀具哈克斯接电话卡接电话看见啊山东矿机阿萨德还看见",
            //     },
                userID:this.$route.query.id,
                flag1:this.$route.query.flag1,
                flag2:this.$route.query.flag2,
                flag3:false,
                textarea:''
                
            
        }
    },
    methods:{
        pass(){
            this.flag1='审核';
            this.$http.get("/passbycity",{
            params:{
                userId: this.userID,
            }
        }).then((response)=>{});
            console.log(this.flag1);
        },
        commit(){
            this.$http.get("/singleupbycity",{
            params:{
                userId: this.userID,
            }
        }).then((response)=>{});
            this.flag2='已上报';
        },
        retreat(){
        
            this.flag3=true;
        },
        advise(){
            this.$http.get("/retreatbypro",{
            params:{
                userId: this.userID,
            }
        }).then((response)=>{});
            this.$router.back();
        },
        quit(){
            this.flag3=false;
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
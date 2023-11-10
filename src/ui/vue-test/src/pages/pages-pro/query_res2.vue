<template>
    <div>
        
    
        <el-descriptions title="详细信息"  :column="2" border>
            <el-descriptions-item label="企业名称">{{item.companyName}}</el-descriptions-item>
            <el-descriptions-item label="企业编号">{{item.userId}}</el-descriptions-item>
            <el-descriptions-item label="单位性质">{{item.character}}</el-descriptions-item>
            <el-descriptions-item label="所属行业">{{item.industry}}</el-descriptions-item>
            <el-descriptions-item label="所在市">{{item.city}}</el-descriptions-item>
            <el-descriptions-item label="所在区县">{{item.district}}</el-descriptions-item>
            
            <el-descriptions-item label="建档期就业人数">{{ item.oldNum }}</el-descriptions-item>
            <el-descriptions-item label="调查期就业人数">{{item.nowNum}}</el-descriptions-item>
            <el-descriptions-item label="就业人数减少类型">{{item.decReason}}</el-descriptions-item>
            <el-descriptions-item label="主要原因">{{item.mainReason}}</el-descriptions-item>
            
            <el-descriptions-item label="次要原因">{{item.secReason}}</el-descriptions-item>
            <el-descriptions-item label="次要原因说明">{{item.explain}}</el-descriptions-item>
            
        </el-descriptions>

       
    </div>
</template>

<script>
export default{
    created(){
        this.$http.get("/querydetail",{
            params:{
                tableName:this.tableName,
                userId: this.userId,
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
                userId:this.$route.query.userId,
                tableName:this.$route.query.tableName,
                flag1:this.$route.query.flag1,
                flag2:this.$route.query.flag2,
                flag3:false,
                textarea:''
                
            
        }
    },
    methods:{
        pass(){
            this.flag1='审核';
            this.$http.get("/passbypro",{
            params:{
                userId: this.userID,
            }
        }).then((response)=>{});
            console.log(this.flag1);
        },
        commit(){
            this.$http.get("/singleupbypro",{
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
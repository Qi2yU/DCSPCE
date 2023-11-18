
<template>
  <div :xl="6" :lg="7" class="bg-loadup">
    <el-row type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="6"></el-col>
      <el-col :span="10">
        <!--标题-->
        <h1 class="head">数据上报</h1>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
    <el-row  type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="5"></el-col>
      <el-col :span="20">
        <el-form :model="comCurData" ref="comCurData" label-width="150px" class="demo-dynamic">
          <el-form-item
            label="建档期就业人数"
            :rules="[
              { required: true, message: '请输入建档期就业人数', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
            <el-input  v-model="comCurData.docEmploymentNumber"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="调查期就业人数"
            :rules="[
              { required: true, message: '请输入调查期就业人数', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
            <el-input  v-model="comCurData.curEmploymentNumber"></el-input>
            </el-col>
          </el-form-item>
          <template v-if="isdecreased()">
            <el-form-item 
              v-show = false
              label="就业人数减少类型" 
              :rules="[{ required: true }]"
              class="reasonClass"
            >
              <el-col :span="15">
                <el-select  v-model="comCurData.numDecreasedReason" placeholder="" style="width:100%">
                  <el-option label="关闭破产" value="关闭停产"></el-option>
                  <el-option label="停业整顿" value="停业整顿"></el-option>
                  <el-option label="经济性裁员" value="经济性裁员"></el-option>
                  <el-option label="业务转移" value="业务转移"></el-option>
                  <el-option label="自然减员" value="自然减员"></el-option>
                  <el-option label="正常解除或终止劳动合同" value="正常接触或终止劳动合同"></el-option>
                  <el-option label="国际因素变化影响" value="国际因素变化影响"></el-option>
                  <el-option label="自然灾害" value="自然灾害"></el-option>
                  <el-option label="重大事件影响" value="重大事件影响"></el-option>
                  <el-option label="其他" value="其他"></el-option>
                </el-select>
              </el-col>
            </el-form-item>
          </template>
          <template v-if="isdecreased()">
            <el-form-item 
              label="主要原因"
              :rules="[{ required: true }]"
              class="reasonClass"
            >
              <el-col :span="15">
                <el-select  v-model="comCurData.mainReason" placeholder="" style="width:100%">
                  <el-option label="产业结构调整" value="产业结构调整"></el-option>
                  <el-option label="重大技术改革" value="重大技术改革"></el-option>
                  <el-option label="节能减排、淘汰落后产能" value="节能减排、淘汰落后产能"></el-option>
                  <el-option label="订单不足" value="订单不足"></el-option>
                  <el-option label="原材料涨价" value="原材料涨价"></el-option>
                  <el-option label="工资、社保等用工成本上升" value="工资、社保等用工成本上升"></el-option>
                  <el-option label="经营资金困难" value="经营资金困难"></el-option>
                  <el-option label="税收政策变化（包括税负增加或出口退税减少等）" value="税收政策变化（包括税负增加或出口退税减少等）"></el-option>
                  <el-option label="季节性用工" value="季节性用工"></el-option>
                  <el-option label="国际因素变化" value="国际因素变化"></el-option>
                  <el-option label="正常解除或终止劳动合同" value="正常解除或终止劳动合同"></el-option>
                  <el-option label="自然减员" value="自然减员"></el-option>
                  <el-option label="其他" value="其他"></el-option>
                </el-select>
              </el-col>
            </el-form-item>
          </template>
          <template v-if="isdecreased() && (isnaturallyDecreased() || isOthers())">
            <el-form-item 
              label="次要原因"
              :rules="[{ required: true }]"
              class="reasonClass"
            >
              <el-col :span="15">
                <el-select  v-model="comCurData.secondReason" placeholder="" style="width:100%">
                  <template v-if="isdecreased() && isnaturallyDecreased()">
                    <el-option label="退休" value="退休"></el-option>
                    <el-option label="退职" value="退职"></el-option>
                    <el-option label="死亡" value="死亡"></el-option>
                  </template>
                  <template v-if="isdecreased() && isOthers()">
                    <el-option label="自行离职" value="自行离职"></el-option>
                    <el-option label="工作调动、企业内部调剂" value="工作调动、企业内部调剂"></el-option>
                    <el-option label="劳动关系转移、劳务派遣" value="劳动关系转移、劳务派遣"></el-option>
                    <el-option label="招不上人来" value="招不上人来"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </template>
                </el-select>
              </el-col>
            </el-form-item>
          </template>
          <template v-if="isdecreased()">
            <el-form-item
              label="其他原因说明"
              :rules="[
                { required: true, message: '请输入邮箱地址', trigger: 'blur' }
              ]"
            >
              <el-col :span="15">
                <el-input
                  type="textarea"
                  :rows="5"
                   
                  placeholder=""
                  v-model="comCurData.reasonDetail">
                </el-input>
              </el-col>
            </el-form-item>
          </template>
          <el-form-item>
            <!-- <el-button type="primary" @click="addDomain">修改</el-button>
            <el-button @click="addDomain">确定</el-button> -->

            <el-button @click="go_back_collection">取消修改</el-button>
            <el-button type="primary" @click="updateEmployData">确定</el-button>
    
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="1"></el-col>
    </el-row>
  </div>
</template>

<script>
  var $this = {};
  export default {
    data() {
      return {
        comCurData: {
          docEmploymentNumber: '',
          curEmploymentNumber: '',
          numDecreasedReason: '',
          mainReason: '',
          secondReason: '',
          reasonDetail: ''
        }
      };
    },
    beforeCreate() {
      $this = this;
    },
    created:function(){
      this.userid = this.$http.userid
      console.log(this.$http.userid)
      this.$http.get("/get_company_collection_data",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        console.log(response)
        console.log(response.data)
        console.log(response.data.docEmploymentNumber)
        this.comCurData.docEmploymentNumber = response.data.docEmploymentNumber;
        this.comCurData.curEmploymentNumber = response.data.curEmploymentNumber > 0? response.data.curEmploymentNumber: '';
        this.comCurData.numDecreasedReason = response.data.numDecreasedReason > 0? response.data.numDecreasedReason: '';
        this.comCurData.mainReason = response.data.mainReason > 0? response.data.mainReason: '';
        this.comCurData.secondReason = response.data.secondReason > 0? response.data.secondReason: '';
        this.comCurData.reasonDetail = response.data.reasonDetail;
        // console.log("初始化结束");
      });
      // console.log("被创建");
    },
    methods: {
      updateEmployData(){
        // 需要检测一下合不合法
        
        // ... balabala
        console.log(this.comCurData);
        // 再提交到后端
        // this.$http.post("/data_collection").then((response)=>{});
               this.$http({
                         url: '/data_collection',
                         method:'post',
                         //发送格式为json
                         data:JSON.stringify({
                              companyID : this.$http.userid,    // todo 这个后面换成当前用户的id
                              docEmploymentNumber : this.comCurData.docEmploymentNumber,
                              curEmploymentNumber : this.comCurData.curEmploymentNumber,
                              numDecreasedReason : this.comCurData.numDecreasedReason,
                              mainReason : this.comCurData.mainReason,
                              secondReason : this.comCurData.secondReason,
                              reasonDetail : this.comCurData.reasonDetail,
                        }),
                        headers:
                        {
                          'Content-Type': 'application/json'
                        }
                       }).then((response)=>{
                        console.log(response)
                        this.$message.success('上报就业数据成功')
                        this.go_back_collection()
                      });
        this.go_back_collection();
      },
      go_back_collection(){
        $this.$router.push("/company/data_collection").catch(error => error);
        // console.log("go to data_collection");
      },
      isdecreased(){
        if(this.comCurData.docEmploymentNumber != ''  
            && this.comCurData.curEmploymentNumber != ''
            && this.comCurData.docEmploymentNumber > this.comCurData.curEmploymentNumber) return true;
        else return false;
      },
      isnaturallyDecreased(){
        if(this.comCurData.mainReason == "自然减员") return true;
        return false;
      },
      isOthers(){
        if(this.comCurData.mainReason == "其他") return true;
        return false;
      }
    }
  }
</script>


<style lanng="less"> 
.head {
  text-align: center;
  margin-top: 5%;
  margin-bottom: 5%;
  color: #000a0c;
  font-size: 40px;
}

.loadup-list{
  width:100%;
  margin-bottom: 5%;
}

.bg-loadup {
    height: 100%;
    /* background-image: url("../assets/backgroud.jpg"); */
    /* background-size: 200%; */

    .toolbar .el-form--inline .reasonClass div.el-input{
      width: 10px;
    }
  }

</style>
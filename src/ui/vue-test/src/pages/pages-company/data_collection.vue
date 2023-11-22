
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
    <template v-if="!isincollection()">
      <el-row  type="flex" class="row-bg row-two" justify="center" align="top">
        当前不处于调查期！
      </el-row>
    </template>
    <template v-if="isrefused()">
      <el-row type="flex" class="row-bg row-two" justify="center" align="top">
        您提交的就业数据被驳回，请重新填报后上交。驳回的理由为：
      </el-row>
      <el-row width="50%" type="flex" class="row-bg row-two" justify="center" align="top">
        <el-col :span="0"></el-col>
        <el-col :span="12">
          <el-input
            type="textarea"
            disabled
            :rows="2"
            size = small 
            v-model="refusedData.emplRefusedInfo"
            resize=none
            color=#b30e0e>
          </el-input>
        </el-col>
      </el-row>
    </template>
    <template v-if="isincollection()">
      <el-row  type="flex" class="row-bg row-two" justify="center" align="top">
        <template v-if="getstatus()==0" >当前就业信息上报状态为：未上报数据</template>
        <template v-else-if="getstatus()==1" >当前就业信息上报状态为：已上报未审核</template>
        <template v-else-if="getstatus()==2 || getstatus()==3" >当前就业信息上报状态为：市审核完毕</template>
        <template v-else-if="getstatus()==4 || getstatus()==5" >当前就业信息上报状态为：省审核完毕</template>
        <template v-else>当前就业信息上报状态为：被驳回未修改</template>
      </el-row>
    </template>
    <template v-if="isincollection()">
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
            <el-input disabled v-model="comCurData.docEmploymentNumber"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="调查期就业人数"
            :rules="[
              { required: true, message: '请输入调查期就业人数', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
            <el-input disabled v-model="comCurData.curEmploymentNumber"></el-input>
            </el-col>
          </el-form-item>
          <template v-if="isdecreased()">
            <el-form-item 
              label="就业人数减少类型" 
              :rules="[{ required: true }]"
              class="reasonClass"
            >
              <el-col :span="15">
                <el-select disabled v-model="comCurData.numDecreasedReason" placeholder="" style="width:100%">
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
            <el-form-item 
              label="主要原因"
              :rules="[{ required: true }]"
              class="reasonClass"
            >
              <el-col :span="15">
                <el-select disabled v-model="comCurData.mainReason" placeholder="" style="width:100%">
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
                <el-select disabled v-model="comCurData.secondReason" placeholder="" style="width:100%">
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
                  disabled 
                  placeholder=""
                  v-model="comCurData.reasonDetail"
                  resize = none>
                </el-input>
              </el-col>
            </el-form-item>
          </template>
          <el-form-item>
            <!-- <el-button type="primary" @click="addDomain">修改</el-button>
            <el-button @click="addDomain">确定</el-button> -->
            <el-button v-if="!isable2Modify()" disabled type="primary">修改</el-button>
            <el-button v-else type="primary" @click="modify_collection_data">修改</el-button>
            <el-button >确定</el-button>
            本期调查期持续时间：{{ collectionData.start_time }}-{{ collectionData.end_time }}
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="1"></el-col>
    </el-row>
    </template>
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
          reasonDetail: '',
          iscollected: '',
          status: '',
        },
        refusedData: {
          isRefused: '',
          emplRefusedInfo: '',
        },
        collectionData:{
          start_time:'',
          end_time:'',
        },
      };
    },
    beforeCreate() {
      $this = this;
    },
    created:function(){
      this.userid = this.$http.userid
      console.log(this.userid);
      this.$http.get("/get_company_collection_data",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        console.log(response.data)
        this.comCurData.docEmploymentNumber = response.data.docEmploymentNumber;
        this.comCurData.curEmploymentNumber = response.data.curEmploymentNumber > 0? response.data.curEmploymentNumber: '';
        this.comCurData.numDecreasedReason =  response.data.numDecreasedReason;
        this.comCurData.mainReason =  response.data.mainReason;
        this.comCurData.secondReason = response.data.secondReason;
        this.comCurData.reasonDetail = response.data.reasonDetail;
        this.comCurData.iscollected = response.data.valid;
        this.comCurData.status = response.data.status;
      });
      this.$http.get("/get_refused_info",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        // console.log(response)
        // console.log(response.data)
        this.refusedData.isRefused = response.data.is_refused;
        this.refusedData.emplRefusedInfo = response.data.emplInfoRefused;
      });
      this.$http.get("/table").then((response)=>{
        this.collectionData.start_time=response.data.start_time;
        this.collectionData.end_time=response.data.end_time;
      });

      // console.log("被创建");
    },
    methods: {
      modify_collection_data(){
        $this.$router.push("/company/data_collection_modify").catch(error => error);
        // console.log("go to data_collection_modify");
      },  
      isincollection(){
        return (this.comCurData.iscollected == 0) 
      },
      isable2Modify(){
        var status = this.comCurData.status;
        var flag = (status == 0 || status == 1 || status == 6 || status == 7)
        console.log(flag)
        return flag
      },
      getstatus(){
        return this.comCurData.status;
      },
      isrefused(){
        return (this.refusedData.emplRefusedInfo != null) 
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


  input::-webkit-input-placeholder {
    color: #b30e0e;
  }
  input::-moz-input-placeholder {
    color: #b30e0e;
  }
  input::-ms-input-placeholder {
    color: #b30e0e;
  }

</style>

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
    <el-row span="6"></el-row>
    <el-row  type="flex" class="row-bg row-two" justify="center" align="top">
      <el-col :span="5"></el-col>
      <el-col :span="20">
        <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="150px" class="demo-dynamic">
          <el-form-item
            label="建档期就业人数"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
            <el-input v-model="dynamicValidateForm.doc_num"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item
            label="调查期就业人数"
            :rules="[
              { required: true, message: '请输入邮箱地址', trigger: 'blur' }
            ]"
          >
            <el-col :span="15">
            <el-input v-model="dynamicValidateForm.query_num"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item 
            label="就业人数减少类型" 
            :rules="[{ required: true }]"
            class="reasonClass"
          >
            <el-col :span="15">
              <el-select v-model="dynamicValidateForm.descend_type" placeholder="请选择就业人数减少类型" style="width:100%">
                <el-option label="关闭破产" value="0"></el-option>
                <el-option label="停业整顿" value="1"></el-option>
                <el-option label="经济性裁员" value="2"></el-option>
                <el-option label="业务转移" value="3"></el-option>
                <el-option label="自然减员" value="4"></el-option>
                <el-option label="正常解除或终止劳动合同" value="5"></el-option>
                <el-option label="国际因素变化影响" value="6"></el-option>
                <el-option label="自然灾害" value="7"></el-option>
                <el-option label="重大事件影响" value="8"></el-option>
                <el-option label="其他" value="9"></el-option>
              </el-select>
            </el-col>
          </el-form-item>
          <el-form-item 
            label="主要原因"
            :rules="[{ required: true }]"
            class="reasonClass"
          >
            <el-col :span="15">
              <el-select v-model="dynamicValidateForm.main_reason" placeholder="请选择主要原因" style="width:100%">
                <el-option label="产业结构调整" value="0"></el-option>
                <el-option label="重大技术改革" value="1"></el-option>
                <el-option label="节能减排、淘汰落后产能" value="2"></el-option>
                <el-option label="订单不足" value="3"></el-option>
                <el-option label="原材料涨价" value="4"></el-option>
                <el-option label="工资、社保等用工成本上升" value="5"></el-option>
                <el-option label="经营资金困难" value="6"></el-option>
                <el-option label="税收政策变化（包括税负增加或出口退税减少等）" value="7"></el-option>
                <el-option label="季节性用工" value="8"></el-option>
                <el-option label="国际因素变化" value="9"></el-option>
                <el-option label="正常解除或终止劳动合同" value="10"></el-option>
                <el-option label="自然减员" value="11"></el-option>
                <el-option label="其他" value="12"></el-option>
              </el-select>
            </el-col>
          </el-form-item>

          <el-form-item 
            label="次要原因"
            :rules="[{ required: true }]"
            class="reasonClass"
          >
            <el-col :span="15">
              <el-select v-model="dynamicValidateForm.second_reason" placeholder="请选择次要原因" style="width:100%">
                <el-option label="自行离职" value="0"></el-option>
                <el-option label="工作调动、企业内部调剂" value="1"></el-option>
                <el-option label="劳动关系转移、劳务派遣" value="2"></el-option>
                <el-option label="退休" value="3"></el-option>
                <el-option label="退职" value="4"></el-option>
                <el-option label="死亡" value="5"></el-option>
                <el-option label="招不上人来" value="6"></el-option>
                <el-option label="其它" value="7"></el-option>
              </el-select>
            </el-col>
          </el-form-item>

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
                placeholder="请输入原因说明"
                v-model="textarea">
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <!-- <el-button type="primary" @click="addDomain">修改</el-button>
            <el-button @click="addDomain">确定</el-button> -->

            <el-button type="primary">修改</el-button>
            <el-button >确定</el-button>
    
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="1"></el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        dynamicValidateForm: {
          doc_num: '',
          query_num: '',
          descend_type: '',
          main_reason: '',
          second_reason: '',
          reason_detail: ''
        }
      };
    },
    methods: {
      // submitForm(formName) {
      //   this.$refs[formName].validate((valid) => {
      //     if (valid) {
      //       alert('submit!');
      //     } else {
      //       console.log('error submit!!');
      //       return false;
      //     }
      //   });
      // },
      // resetForm(formName) {
      //   this.$refs[formName].resetFields();
      // },
      // removeDomain(item) {
      //   var index = this.dynamicValidateForm.domains.indexOf(item)
      //   if (index !== -1) {
      //     this.dynamicValidateForm.domains.splice(index, 1)
      //   }
      // },
      // addDomain() {
      //   this.dynamicValidateForm.domains.push({
      //     value: '',
      //     key: Date.now()
      //   });
      // }
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
<template>
  <div class="record-information">
    
    <h1 class="page-title">企业备案信息</h1> 
    <el-form :model="form" :rules="rules" ref="form" label-width="120px" class="form">
      <el-row class="form-row">
        <el-col :span="12">
          <el-form-item label="所属地区" prop="region">
            <el-input v-model="form.region" disabled></el-input>
          </el-form-item>
          <el-form-item label="组织机构代码" prop="organizationCode">
            <el-input v-model="form.organizationCode"></el-input>
          </el-form-item>
          <el-form-item label="企业名称" prop="companyName">
            <el-input v-model="form.companyName"></el-input>
          </el-form-item>
          <el-form-item label="企业性质" prop="companyType">
            <el-select v-model="form.companyType" placeholder="请选择">
              <el-option label="类型A" value="A"></el-option>
              <el-option label="类型B" value="B"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属行业" prop="industry">
            <el-select v-model="form.industry" placeholder="请选择">
              <el-option label="行业A" value="A"></el-option>
              <el-option label="行业B" value="B"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="主要经营业务" prop="mainBusiness">
            <el-input v-model="form.mainBusiness" type="textarea" resize="none" rows="5"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          
          <el-form-item label="联系人" prop="contactPerson">
            <el-input v-model="form.contactPerson"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="contactAddress">
            <el-input v-model="form.contactAddress"></el-input>
          </el-form-item>
          <el-form-item label="邮政编码" prop="postalCode">
            <el-input v-model="form.postalCode"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="contactNumber">
            <el-input v-model="form.contactNumber"></el-input>
          </el-form-item>
          <el-form-item label="传真" prop="fax">
            <el-input v-model="form.fax"></el-input>
          </el-form-item>
          <el-form-item label="EMAIL" prop="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
        
      <el-row class="button-row">
        <el-col :span="20" class="text-center">
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">保存并上报</el-button>
          <el-button @click="resetForm('form')">重置</el-button>
        </el-form-item>
        </el-col>
      </el-row>

      <el-row class="status-row">
        <el-col :span="20" class="text-center">
          <el-form-item>
            <span :style="{ color: statusColor, 'font-weight': 'bold' }">{{ statusText }}</span>
            <!-- <el-input v-model="statusText" :value="statusText" :readonly="true" :style="{ color: statusColor, 'font-weight': 'bold' }"></el-input> -->
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        region: '丽江市', // 你需要填充企业所属地区的数据
        organizationCode: '',
        companyName: '',
        companyType: '',
        industry: '',
        mainBusiness: '',
        contactPerson: '',
        contactAddress: '',
        postalCode: '',
        contactNumber: '',
        fax: '',
        email: '',
      },
      rules: {
        region: [{ required: true, message: '请选择所属地区', trigger: 'change' }],
        organizationCode: [
          { required: true, message: '请输入组织机构代码', trigger: 'blur' },
          { pattern: /^[a-zA-Z0-9]{1,9}$/, message: '组织机构代码由字母和数字组成，不超过9位', trigger: 'blur' },
        ],
        companyName: [{ required: true, message: '请输入企业名称', trigger: 'blur' }],
        companyType: [{ required: true, message: '请选择企业性质', trigger: 'change' }],
        industry: [{ required: true, message: '请选择所属行业', trigger: 'change' }],
        mainBusiness: [{ required: true, message: '请输入主要经营业务', trigger: 'blur' }],
        contactPerson: [{ required: true, message: '请输入联系人', trigger: 'blur' }],
        contactAddress: [{ required: true, message: '请输入联系地址', trigger: 'blur' }],
        postalCode: [
          { required: true, message: '请输入邮政编码', trigger: 'blur' },
          { pattern: /^[0-9]{6}$/, message: '邮政编码必须为6位数字', trigger: 'blur' },
        ],
        contactNumber: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { pattern: /^(0\d{2,3}-\d{7,8})$|^(1[3456789]\d{9})$/, message: '请输入有效的电话号码', trigger: 'blur' },
        ],
        fax: [
          { required: true, message: '请输入传真', trigger: 'blur' },
          { pattern: /^(0\d{2,3}-\d{7,8})$/, message: '请输入有效的传真号码', trigger: 'blur' },
        ],
        email: [{ pattern: /^([a-zA-Z0-9]+[_|_\-|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_\-|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/, message: '请输入有效的邮箱', trigger: 'blur' }],
      },
      statusText: '当前状态： 未提交',
      statusColor: 'Danger',
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 执行保存并上报的操作
          // 在这里你可以提交表单数据到后端或者执行其他操作
          console.log('提交表单数据', this.form);
          this.$message({
            message: '提交成功，正在审核中',
            type: 'success',
          });
          this.statusText = '当前状态 待审核';
          this.statusColor = 'yellow';
        } else {
          console.log('表单验证失败');
          this.$message({
            message: '请填写正确的信息！',
            type: 'failed',
          });
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style>
.form {
  max-width: 1200px;
  margin: 0 auto;
}

.page-title {
  text-align: center; /* 居中标题 */
  font-size: 40px; /* 根据需要调整字体大小 */
  margin-bottom: 20px; /* 可根据需要调整标题与表单之间的间距 */
}

.text-center{
  text-align: center;
}

.form-row {
  margin-bottom: 40px;
}
</style>

<template>
  <div class="record-information">
    
    <h1 class="page-title">企业备案信息</h1> 
    <el-form :model="form" :rules="rules" ref="form" label-width="120px" class="form">
      <el-row class="status-row">
        <el-col :span="20" class="text-center">
          <el-form-item>
            <span :style="{ color: statusColor, 'font-weight': 'bold' }">
              <template v-if="getRecordStatus() == 0"> 当前备案信息状态：未上报</template>
              <template v-else-if="getRecordStatus() == 2"> 当前备案信息状态：已上报未审核</template>
              <template v-else-if="getRecordStatus() == 1"> 当前备案信息状态：已审核通过</template>
              <template v-else-if="getRecordStatus() == 3"> 当前备案信息状态：被驳回</template>
            
            </span>
            <!-- <el-input disabled v-model="statusText" :value="statusText" :readonly="true" :style="{ color: statusColor, 'font-weight': 'bold' }"></el-input> -->
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="isrefused()" type="flex" class="row-bg row-two" justify="center" align="top">
        您提交的就业数据被驳回，请重新填报后上交。驳回的理由为：
      </el-row>
      <el-row v-if="isrefused()" width="50%" type="flex" class="row-bg row-two" justify="center" align="top">
        <el-col :span="0"></el-col>
        <el-col :span="12">
          <el-input
            type="textarea"
            disabled
            :rows="2"
            size = small 
            v-model="refusedData.comRefusedInfo"
            resize=none
            color=#b30e0e>
          </el-input>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg row-two" justify="center" align="top">
        
        <el-col :span="10">
          <el-form-item label="所属地区" prop="region">
            <el-cascader
              v-model="form.region"
              :options="options"
              disabled
              placeholder="所在地区"
              :props="{ checkStrictly: true }"
              style="width: 200px;height: 50px;"
              clearable>
            </el-cascader>
          </el-form-item>
          <el-form-item label="组织机构代码" prop="organizationCode">
            <el-input disabled v-model="form.organizationCode"></el-input>
          </el-form-item>
          <el-form-item label="企业名称" prop="companyName">
            <el-input disabled v-model="form.companyName"></el-input>
          </el-form-item>
          <el-form-item label="企业性质" prop="companyType">
            <el-select disabled v-model="form.companyType" placeholder="请选择">
              <el-option label="类型A" value="A"></el-option>
              <el-option label="类型B" value="B"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属行业" prop="industry">
            <el-select disabled v-model="form.industry" placeholder="请选择">
              <el-option label="行业A" value="A"></el-option>
              <el-option label="行业B" value="B"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="主要经营业务" prop="mainBusiness">
            <el-input disabled v-model="form.mainBusiness" type="textarea" resize="none" rows="5"></el-input>
          </el-form-item>

          
          <el-form-item label="联系人" prop="contactPerson">
            <el-input disabled v-model="form.contactPerson"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="contactAddress">
            <el-input disabled v-model="form.contactAddress"></el-input>
          </el-form-item>
          <el-form-item label="邮政编码" prop="postalCode">
            <el-input disabled v-model="form.postalCode"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="contactNumber">
            <el-input disabled v-model="form.contactNumber"></el-input>
          </el-form-item>
          <el-form-item label="传真" prop="fax">
            <el-input disabled v-model="form.fax"></el-input>
          </el-form-item>
          <el-form-item label="EMAIL" prop="email">
            <el-input disabled v-model="form.email"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
        
      <el-row class="button-row">
        <el-col :span="20" class="text-center">
        <el-form-item>
          <el-button type="primary" @click="gotoModify()">修改</el-button>
        </el-form-item>
        </el-col>
      </el-row>

      
    </el-form>
  </div>
</template>

<script>
var $this = {};
export default {
  data() {
    return {
      userid: '',
      form: {
        region:[],
        city: '', // 你需要填充企业所属地区的数据
        district: '',
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
        isvalid: '',
      },
      refusedData: {
          isRefused: '',
          comRefusedInfo: '',
      },
      rules: {
        region: [{ required: true, message: '请选择所属地区', trigger: 'change' }],
        // organizationCode: [
        //   { required: true, message: '请输入组织机构代码', trigger: 'blur' },
        //   { pattern: /^[a-zA-Z0-9]{1,9}$/, message: '组织机构代码由字母和数字组成，不超过9位', trigger: 'blur' },
        // ],
        companyName: [{ required: true, message: '请输入企业名称', trigger: 'blur' }],
        // companyType: [{ required: true, message: '请选择企业性质', trigger: 'change' }],
        // industry: [{ required: true, message: '请选择所属行业', trigger: 'change' }],
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
        // fax: [
        //   { required: true, message: '请输入传真', trigger: 'blur' },
        //   { pattern: /^(0\d{2,3}-\d{7,8})$/, message: '请输入有效的传真号码', trigger: 'blur' },
        // ],
        email: [{ pattern: /^([a-zA-Z0-9]+[_|_\-|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_\-|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/, message: '请输入有效的邮箱', trigger: 'blur' }],
      },
      // statusText: '当前状态： 未提交',
      statusColor: 'Danger',
      options: [{
          value: '玉溪市',
          label: '玉溪市',
          
          children: [{
            value: '通海县',
            label: '通海县',
          }, {
            value: '红塔区',
            label: '红塔区',
            
          },{
            value:'江川区',
            label:'江川区'
          },{
            value:'澄江市',
            label:'澄江市'
          }]
        },
        {
          value:'昆明市',
          label:'昆明市',
          children:[{
            value:'官渡区',
            label:'官渡区'
          },{
            value:'五华区',
            label:'五华区'
          },{
            value:'盘龙区',
            label:'盘龙区'
          },{
            value:'西山区',
            label:'西山区'
          },{
            value:'呈贡区',
            label:'呈贡区'
          }]
        },
        {
          value:'曲靖市',
          label:'曲靖市',
          children:[{
            value:'麒麟区',
            label:'麒麟区'
          },{
            value:'宣威区',
            label:'宣威区'
          },{
            value:'罗平区',
            label:'罗平区'
          },{
            value:'陆良县',
            label:'陆良县'
          }]
        },
        {
          value:'昭通市',
          label:'昭通市',
          children:[{
            value:'镇雄县',
            label:'镇雄县'
          },{
            value:'昭阳区',
            label:'昭阳区'
          },{
            value:'宜良县',
            label:'宜良县'
          }]

        },
        {
          value:'保山市',
          label:'保山市',
          children:[{
            value:'隆阳区',
            label:'隆阳区'
          },{
            value:'腾冲市',
            label:'腾冲市'
          }]

        },
        {
          value:'普洱市',
          label:'普洱市',
          children:[{
            value:'思茅区',
            label:'思茅区'
          },{
            value:'景东彝族自治县',
            label:'景东彝族自治县'
          }]
        },
        {
          value:'丽江市',
          label:'丽江市',
          children:[{
            value:'古城区',
            label:'古城区'
          },{
            value:'玉龙纳西族自治县',
            label:'玉龙纳西族自治县'
          }]
        },
        {
          value:'临沧市',
          label:'临沧市',
          children:[{
            value:'临翔区',
            label:'临翔区'
          }]
        },
        {
          value:'楚雄彝族自治州',
          label:'楚雄彝族自治州',
          children:[{
            value:'楚雄市',
            label:'楚雄市'
          },{
            value:'元谋县',
            label:'元谋县'
          }]
        },
        {
          value:'红河哈尼族彝族自治州',
          label:'红河哈尼族彝族自治州',
          children:[{
            value:'蒙自市',
            label:'蒙自市'
          },{
            value:'开远市',
            label:'开远市'
          },{
            value:'个旧市',
            label:'个旧市'
          },{
            value:'建水县',
            label:'建水县'
          }]
        },
        {
          value:'文山壮族苗族自治州',
          label:'文山壮族苗族自治州',
          children:[{
            value:'文山市',
            label:'文山市'
          },{
            value:'富宁县',
            label:'富宁县'
          }]
        },
        {
          value:'西双版纳傣族自治州',
          label:'西双版纳傣族自治州',
          children:[{
            value:'景洪市',
            label:'景洪市'
          },{
            value:'勐海县',
            label:'勐海县'
          }]
        },
        {
          value:'大理白族自治州',
          label:'大理白族自治州',
          children:[{
            value:'大理市',
            label:'大理市'
          },{
            value:'祥云县',
            label:'祥云县'
          }]
        },
        {
          value:'德宏傣族景颇族自治州',
          label:'德宏傣族景颇族自治州',
          children:[{
            value:'芒市',
            label:'芒市'
          },{
            value:'瑞丽市',
            label:'瑞丽市'
          }]
        },
        {
          value:'怒江傈僳族自治州',
          label:'怒江傈僳族自治州',
          children:[{
            value:'泸水市',
            label:'泸水市'
          }]
        },
        {
          value:'迪庆藏族自治州',
          label:'迪庆藏族自治州',
          children:[{
            value:'香格里拉市',
            label:'香格里拉市'
          }]
        }
      ],
    };
  },
  beforeCreate() {
      $this = this;
  },
  created:function(){
      this.userid = this.$http.userid
      console.log(this.userid);
      this.$http.get("/get_com_info_com",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        console.log(response.data)
        var cominfo = response.data;
        this.form.city = cominfo.city
        this.form.district = cominfo.district
        this.form.region = [cominfo.city, cominfo.district]
        console.log(this.form.region)
        this.form.organizationCode = cominfo.company_id
        this.form.companyName = cominfo.company_name
        this.form.companyType = cominfo.company_character
        this.form.industry = cominfo.company_industry
        this.form.mainBusiness = cominfo.company_business
        this.form.contactPerson = cominfo.linksman
        this.form.contactAddress = cominfo.linaddress
        this.form.postalCode = cominfo.post_num
        this.form.contactNumber = cominfo.phone
        this.form.fax = cominfo.fax_num
        this.form.email = cominfo.email
        this.form.isvalid = cominfo.is_valid

      });
      // console.log("被创建");

      this.$http.get("/get_refused_info",{
        params: {
          userid: this.userid
        }
      }).then((response)=>{
        // console.log(response)
        console.log(response.data)
        this.refusedData.comRefusedInfo = response.data.comInfoRefused;
        this.refusedData.isRefused = response.data.is_refused;
        
      });
    },
  methods: {
    getRecordStatus(){
      // console.log(this.form.is)
      return this.form.isvalid
    },
    gotoModify(){
      $this.$router.push("/company/record_modify").catch(error => error);
    },
    isrefused(){
      return this.refusedData.isRefused==1 && this.refusedData.comRefusedInfo != ''
    }
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

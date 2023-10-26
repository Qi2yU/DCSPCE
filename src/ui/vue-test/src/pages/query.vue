<template>
<div>
  <h2>数据查询</h2>
<el-form :inline="true" :model="formInline" class="demo-form-inline">
  <el-form-item label="企业名称">
    <el-input v-model="formInline.name" placeholder="企业名称"></el-input>
  </el-form-item>
  <el-form-item label="企业编号">
    <el-input v-model="formInline.num" placeholder="企业编号"></el-input>
  </el-form-item>
</el-form>

<el-form :inline="true" :model="formInline1" class="demo-form-inline">
  <el-form-item label="单位性质">
    <el-select v-model="formInline1.xingzhi" placeholder="单位性质">
      <el-option label="国企" value="guoqi"></el-option>
      <el-option label="私企" value="siqi"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="所属行业">
    <el-select v-model="formInline1.hangye" placeholder="所属行业">
      <el-option label="餐饮业" value="canying"></el-option>
      <el-option label="工业" value="gong"></el-option>
      <el-option label="旅游业" value="lvyou"></el-option>
    </el-select>
  </el-form-item>
</el-form>

<el-form :inline="true"  class="demo-form-inline">
<el-form-item label="所在地区">
  <el-cascader
    :options="options"
    :props="{ checkStrictly: true }"
    clearable></el-cascader>
</el-form-item>
</el-form>

<el-form :inline="true" :model="form" class="demo-form-inline">
  <el-form-item label="活动时间">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
    </el-col>
    <el-col class="line" :span="2">-</el-col>
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
    </el-col>
  </el-form-item>
</el-form>
<el-form>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">查询</el-button>
  </el-form-item>
</el-form>
</div>

</template>
<script>
  export default {
    data() {
      return {
        formInline: {
          name: '',
          num:'',
          region: ''
        },
        formInline1:{
          xingzhi:'',
          hangye:'',
        },
        options: [{
          value: 'zhinan',
          label: '玉溪市',
          children: [{
            value: 'shejiyu',
            label: '通海县',
            children: [{
              value: 'yizhi',
              label: '秀山镇'
            }, {
              value: 'fankui',
              label: '河西镇'
            }, {
              value: 'xiaolv',
              label: '四街镇'
            },]
          }, {
            value: 'da',
            label: '红塔区',
            children: [{
              value: 'cexiangdaohang',
              label: '侧向导航'
            }, {
              value: 'dingbudaohang',
              label: '顶部导航'
            }]
          }]
        },
        {
          value:'ad',
          label:'昆明市',
        },
        {
          value:'sa',
          label:'曲靖市',
        }
      ],
      form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
        this.$router.push('/queryres');
      }
    }
  }
</script>
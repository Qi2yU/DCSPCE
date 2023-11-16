<template>
  <div>
    <h2>数据查询</h2>
  <el-form :inline="true"  class="demo-form-inline">
    <el-form-item label="企业名称">
      <el-input v-model="companyName" placeholder="企业名称" :value="defaultval"></el-input>
    </el-form-item>
    <el-form-item label="企业账号">
      <el-input v-model="userId" placeholder="企业编号"></el-input>
    </el-form-item>
  </el-form>
  
  <el-form :inline="true"  class="demo-form-inline">
    <el-form-item label="单位性质">
      <el-select v-model="character" placeholder="单位性质" clearable>
        <el-option label="国企" value="国企"></el-option>
        <el-option label="私企" value="私企"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="所属行业">
      <el-select v-model="industry" placeholder="所属行业" clearable>
        <el-option label="餐饮业" value="餐饮业"></el-option>
        <el-option label="工业" value="工业"></el-option>
        <el-option label="旅游业" value="旅游业"></el-option>
      </el-select>
    </el-form-item>
  </el-form>
  
  <el-form :inline="true"  class="demo-form-inline">
  <el-form-item label="所在地区">
    <el-cascader
      v-model="cas_val"
      :options="filteredOptions"
      
      clearable></el-cascader>
  </el-form-item>
  </el-form>
  
  <el-form :inline="true" :model="form" class="demo-form-inline">
    <el-form-item label="起始时间">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" value-format="yyyy-MM-dd" style="width: 200px;height: 50px;"></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="结束时间">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.date2" value-format="yyyy-MM-dd" style="width: 200px;height: 50px;"></el-date-picker>
      </el-col>
    </el-form-item>
  </el-form>
  <el-form style="margin-left: 68%;">
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>
  </div>
  
  </template>
  <script>
    export default {
      created(){
          this.getCity();
      },
      data() {
        return {
          defaultval:'all',
          cas_val:[],
          companyName:'',
          userId:'',
          character:'',
          industry:'',
          user:this.$route.query.userId,
          city:'',
          district:'',
          cityMapping: {
                '5301': '昆明市',
                '5303':'曲靖市',
                '5304':'玉溪市',
                '5305':'保山市',
                '5306':'昭通市',
                '5307':'丽江市',
                '5308':'普洱市',
                '5309':'临沧市',
                '5323':'楚雄彝族自治州',
                '5325':'红河哈尼族彝族自治州',
                '5326':'文山壮族苗族自治州',
                '5328':'西双版纳傣族自治州',
                '5329':'大理白族自治州',
                '5331':'德宏傣族景颇族自治州',
                '5333':'怒江傈僳族自治州',
                '5334':'迪庆藏族自治州'
                
              },
          
        form: {
            
            date1: '',
            date2: '',
            
          }
        }
      },
      computed: {
      filteredOptions() {
        return this.options.map(option => {
          const isYuxiCity = option.value === this.city;
          return {
            ...option,
            disabled: !isYuxiCity,
          };
        });
      },
      options() {
        return [
          {
            value: '玉溪市',
            label: '玉溪市',
            children: [
              {
                value: '通海县',
                label: '通海县',
              },
              {
                value: '红塔区',
                label: '红塔区',
              }
            ]
          },
          {
            value: '昆明市',
            label: '昆明市',
            children: [
              {
                value: '官渡区',
                label: '官渡区',
              }
            ]
          },
          {
            value: '曲靖市',
            label: '曲靖市',
            children: [
              {
                value: '无人区',
                label: '无人区',
              }
            ]
          },
          {
            value: '普洱市',
            label: '普洱市',
            children: [
              {
                value: '抚仙湖区',
                label: '抚仙湖区',
              }
            ]
          }
        ];
      },
    },
      methods: {
        onSubmit() {
          if(this.companyName=='')
          this.companyName=this.defaultval;
          if(this.userId=='')
          this.userId=this.defaultval;
          if(this.character=='')
          this.character=this.defaultval;
          if(this.industry=='')
          this.industry=this.defaultval;
          if(this.cas_val.length==2)
          this.district=this.cas_val[1];
          if(this.cas_val.length==1)
          this.district=this.defaultval;
          if(this.cas_val.length==0)
          this.district=this.defaultval;
          this.$router.push({path:'/government-city/queryres',
            query:{
              companyName:this.companyName,
            userId:this.userId,
            character:this.character,
            industry:this.industry,
            city:this.city,
            district:this.district,
              date1:this.form.date1,
              date2:this.form.date2
            }});
        },
        getCity() {
      // 截取前4位
      const prefix = this.user.substring(0, 4);

      // 查找对照表中的映射
      this.city = this.cityMapping[prefix] || '未知城市';
    },
            show(val,data){
                console.log(val);
                console.log(data);
                if(data.property=="committed")
                this.$router.push({path:'/government-city/committed',
                  query:{city:this.city}});
                else if(data.property=="uncommitted")
                this.$router.push({path:'/government-city/uncommitted',
                  query:{city:this.city}});
            }
      }
    }
  </script> 
  
<style>
.el-form{
  margin-left: 20%;
}
.el-input{
  width: 200px;
  height: 50px;
}
</style>
  
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
      <el-option label="民企" value="民企"></el-option>
      <el-option label="外企" value="外企"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="所属行业">
    <el-select v-model="industry" placeholder="所属行业" clearable>
      <el-option label="航空运输业" value="航空运输业"></el-option>
      <el-option label="金融业" value="金融业"></el-option>
      <el-option label="互联网行业" value="互联网行业"></el-option>
      <el-option label="食品制造业" value="食品制造业"></el-option>
      <el-option label="交通业" value="交通业"></el-option>
      <el-option label="建造业" value="建造业"></el-option>
    </el-select>
  </el-form-item>
</el-form>

<el-form :inline="true"  class="demo-form-inline">
<el-form-item label="所在地区">
  <el-cascader
    v-model="cas_val"
    :options="options"
    placeholder="所在地区"
    :props="{ checkStrictly: true }"
    style="width: 200px;height: 50px;"
    clearable></el-cascader>
</el-form-item>
</el-form>

<!-- <el-form :inline="true" :model="form" class="demo-form-inline">
  <el-form-item label="起始时间">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" value-format="yyyy-MM-dd" style="width: 200px;height: 50px;"></el-date-picker>
    </el-col>
   </el-form-item> 
  <el-form-item label="终止时间">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.date2" value-format="yyyy-MM-dd" style="width: 200px;height: 50px;" @change="exam"></el-date-picker>
    </el-col>
  </el-form-item>
</el-form> -->
<el-form :inline="true"  class="demo-form-inline">
  <el-form-item label="起始月份">
    <el-date-picker
      v-model="date1"
      type="month"
      value-format="yyyy-MM"
      @change="exam"
      placeholder="选择月份">
    </el-date-picker>
  </el-form-item>
  <el-form-item label="结束月份">
    <el-date-picker
      v-model="date2"
      type="month"
      value-format="yyyy-MM"
      @change="exam"
      placeholder="选择月份">
    </el-date-picker>
  </el-form-item>
</el-form>


<el-form style="margin-left: 68%;">
  <el-form-item>
    <el-button type="primary" @click="Submit" :disabled="flag">查询</el-button>
    <!-- <el-button type="primary" @click="onSubmit" :disabled="flag">查询</el-button> -->
  </el-form-item>
</el-form>
</div>

</template>
<script>
  export default {
    created(){
        
    },
    
    data() {
      return {
        flag:true,
        
        date1:'',
        date2:'',
        defaultval:'all',
        cas_val:[],
        companyName:'',
        userId:'',
        character:'',
        industry:'',
        city:'',
        district:'',
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
      form: {
          
          date1: '',
          date2: '',
          
        }
      }
    },
    
    methods: {
      Submit(){
          console.log(this.date1);
          console.log(this.date2);
          if(this.companyName=='')
        this.companyName=this.defaultval;
        if(this.userId=='')
        this.userId=this.defaultval;
        if(this.character=='')
        this.character=this.defaultval;
        if(this.industry=='')
        this.industry=this.defaultval;
        if(this.cas_val.length==0)
        {
          this.city=this.defaultval;
          this.district=this.defaultval;
        }
        if(this.cas_val.length==1)
        {
          this.city=this.cas_val[0];
          this.district=this.defaultval;
        }
        if(this.cas_val.length==2)
        {
          this.city=this.cas_val[0];
          this.district=this.cas_val[1];
        }
        


        this.$router.push({path:'/government-pro/queryres',
          query:{
            companyName:this.companyName,
            userId:this.userId,
            character:this.character,
            industry:this.industry,
            city:this.city,
            district:this.district,
            date1:this.date1,
            date2:this.date2
          }});
      },
      exam(){
        console.log(this.date2);
        
        if(this.date1>this.date2)
        this.flag=true;
        if(this.date1<=this.date2&&this.date1!=''&&this.date2!='')
        this.flag=false;
      },
    
      onSubmit() {
        if(this.companyName=='')
        this.companyName=this.defaultval;
        if(this.userId=='')
        this.userId=this.defaultval;
        if(this.character=='')
        this.character=this.defaultval;
        if(this.industry=='')
        this.industry=this.defaultval;
        if(this.cas_val.length==0)
        {
          this.city=this.defaultval;
          this.district=this.defaultval;
        }
        if(this.cas_val.length==1)
        {
          this.city=this.cas_val[0];
          this.district=this.defaultval;
        }
        if(this.cas_val.length==2)
        {
          this.city=this.cas_val[0];
          this.district=this.cas_val[1];
        }

        this.$router.push({path:'/government-pro/queryres',
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
      }
    }
  }
</script> 

<style scoped>
.el-form{
  margin-left: 20%;
  
}
.el-input{
  width: 200px;
  height: 50px;
}
.el-select{
  width: 200px;
  height: 50px;
}
.el-cascader{
  width:200px;
  height: 50px;
}

</style>

<template>
  <div class="block">
    <span class="demonstration">选择日期</span>
    <el-date-picker
      v-model="value"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
    </el-date-picker>
    <el-select v-model="value1" placeholder="地区选择">
      <el-option
        v-for="item in options1"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
    <el-select v-model="value2" placeholder="所属行业">
      <el-option
        v-for="item in options2"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
    <el-select v-model="value3" placeholder="企业性质">
      <el-option
        v-for="item in options3"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
    <el-button type="success"  @click = "queryHandler">分析</el-button>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        value:'',
        value1: '',
        value2: '',
        value3: '',
        options1:[{
          value:'选项1',
          label:'a市'
        },{
          value:'选项2',
          label:'b市'
        },{
          value:'选项3',
          label:'c市'
        }],
        options2:[{
          value:'选项1',
          label:'a行业'
        },{
          value:'选项2',
          label:'b行业'
        },{
          value:'选项3',
          label:'c行业'
        }],
        options3:[{
          value:'选项1',
          label:'私企'
        },{
          value:'选项2',
          label:'国企'
        },],


      };
    }
  };
</script>
<template>
  <div>
    <el-select v-model="value" placeholder="地区选择">
      <el-option
        v-for="item in options1"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled">
      </el-option>
    </el-select>
    <el-button type="success"  @click = "queryHandler">查询</el-button>


  </div>
</template>




<script>
  export default{
    data(){
      return{
        options1:[{
            value: '选项1',
            label: 'a市'
        },{
          value: '选项2',
          label: 'b市',
        }, {
          value: '选项3',
          label: 'c市'
        }, {
            value: '选项4',
            label: 'd市'
          },{
          value: '选项5',
          label: 'e市',
        }, {
          value: '选项6',
          label: 'f市'
        },  {
          value: '选项7',
          label: 'g市'
        }],
        value:'',
        radio:'1',
      }
    },

    methods:{
      queryHandler(){
        this.$emit("query-event", this.value);
      },

    }
  }
</script>
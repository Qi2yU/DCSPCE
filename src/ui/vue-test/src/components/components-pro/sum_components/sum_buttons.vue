 
 <template>
  <div>

  </div>


 </template>

 <script>
  export default{
    data(){
      return{
        options1:[{
            value: '选项1',
            label: '企业性质'
          },{
          value: '选项2',
          label: '所属行业',
        }, {
          value: '选项3',
          label: '调查期'
        }, {
            value: '选项4',
            label: '地区'
          },{
          value: '选项5',
          label: '企业季度',
        }, {
          value: '选项6',
          label: '企业月度'
        },  {
          value: '选项7',
          label: '企业年度'
        }],

        options2:[{
          value: '选项1',
          label: '就业人数'
        }],
        options3:[{
          value: '选项1',
          label: '求和'
        },{
          value: '选项2',
          label: '平均值',
        }, {
          value: '选项3',
          label: '最大值'
        }, {
          value: '选项4',
          label: '最小值'
        }],
        value1:"",
        value2:"",
        value3:"",
        value4:"",
      }
    },
    methods:{
      SumHandler(){

        this.$emit("sum-event", this.value1, this.value4);
      },
    }
  }

 </script>
 
 
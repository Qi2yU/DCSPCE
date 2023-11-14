<template>
  <div>



  </div>
</template>




<script>
  export default{
    data(){
      return{
        options1:[],
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
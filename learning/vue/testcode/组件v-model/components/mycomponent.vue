<template>
	<div>
		<h1>mycomponent</h1>
    搜索:<input type="text"  v-model="content">
	</div>
</template>

<script>
	export default{
    data(){
      return{
        content: "",
      }
    },
    methods:{

    },
    watch:{
      content(newValue,oldValue){
        this.$emit("searchEvent", newValue)
      }
    },
   
  }

</script>
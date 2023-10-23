<template>
	<div>
		<h1>mycomponent</h1>
    <mycomponentdeep/>
	</div>
</template>

<script>
  import mycomponentdeep from './mycomponentdeep.vue';
	export default{
    data(){
      return{
        content: "子组件数据",
      }
    },
    methods:{

    },
    watch:{

    },
    components:{
      mycomponentdeep
    }
   
  }

</script>
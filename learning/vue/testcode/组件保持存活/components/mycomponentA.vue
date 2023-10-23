<template>
	<div>
		<h1>A</h1>
    
	</div>
</template>

<script>
	export default{
    data(){
      return{
        content: "组件数据",
      }
    },
    methods:{

    },
    watch:{

    },

    unmounted(){
      console.log("组件注销")
    }
   
  }

</script>
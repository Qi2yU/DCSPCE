<template>
	<div>
		<h1>mycomponent_deep</h1>
    <p>deep:{{ msg }}</p>
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
    inject:["msg"],
  }

</script>
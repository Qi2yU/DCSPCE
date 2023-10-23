<template>
	<div>
		<mycomponent/>//第三步引用
	</div>
</template>

<script>
	
	import mycomponent from './components/mycomponent.vue'//第一步导入
	

	export default {
    components:{
      mycomponent,//第二部注册
    }
	}
</script>

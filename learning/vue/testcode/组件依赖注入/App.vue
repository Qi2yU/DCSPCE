<template>
	<div>
		<mycomponent />

    
	</div>

  
</template>

<script>
	
	import mycomponent from './components/mycomponent.vue'
	

	export default {
    data(){
      return{
        content:"祖先数据",
      }
    },
    methods:{
    },
    components:{
      mycomponent,
    },
    provide(){
      return{
        msg:this.content,
      }
    }
	}
</script>

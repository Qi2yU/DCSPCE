<template>
	<div>
		<mycomponent @searchEvent="dataHandler"/> 
   搜索内容<p>{{ content }}</p>
	</div>
</template>

<script>
	
	import mycomponent from './components/mycomponent.vue'
	

	export default {

    data(){
      return{
        content:"",
      }
    },
    methods:{
      dataHandler(data){
        this.content = data
      }
    },
    components:{
      mycomponent,
    }
	}
</script>

<template>

	<div>
    <keep-alive>
      <component :is="componentId"></component>
    </keep-alive>
		
    <button @click="changeHandler">切换组件</button>
	</div>

  
</template>

<script>
	
	import mycomponentA from './components/mycomponentA.vue'
  import mycomponentB from './components/mycomponentB.vue'
	

	export default {
    data(){
      return{
        content:"祖先数据",
        componentId:"mycomponentA",
      }
    },
    methods:{
      changeHandler(){
        this.componentId = this.componentId == "mycomponentA" ? "mycomponentB" : "mycomponentA"
      }
    },
    components:{
      mycomponentA,
      mycomponentB
    },
    mounted(){
      console.log("已注册")
    }
    
	}
</script>

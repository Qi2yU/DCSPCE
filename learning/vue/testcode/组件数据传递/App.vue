<template>
	<div>
		<mycomponent :data = "object"  @some-event = "dataHander"/> 
    <p>{{ arry }}</p>
	</div>
</template>

<script>
	
	import mycomponent from './components/mycomponent.vue'
	

	export default {

    data(){
      return{
        object:{
          id : 1,
          msg :"这是父组件的字符串",
        },
        arry:"",
      }
    },
    methods:{
      dataHander(data){
        this.arry = data;
      }
    },
    components:{
      mycomponent,
    }
	}
</script>

<template>
	<div>
		<mycomponent v-slot="slotProps">
      <!-- 插槽内容 -->
    <h3>{{ content }} - {{ slotProps.text }}</h3> 
    <!-- <template #head>
        <p>head</p>
    </template>
    <template #tail>
      <div>
        <p>tail</p>
      </div>
    </template> -->
    </mycomponent> 
    
	</div>

  
</template>

<script>
	
	import mycomponent from './components/mycomponent.vue'
	

	export default {
    data(){
      return{
        content:"插槽内容",
      }
    },
    methods:{
    },
    components:{
      mycomponent,
    }
	}
</script>

<template>
	<div>
		<h1>mycomponent</h1>
       <!-- <slot name="head" :text = "content">默认值</slot> 插槽出口 -->
       <!-- <slot name="tail">默认值</slot> 插槽出口 -->
    <slot :text = "content">默认值</slot>
	</div>
</template>

<script>
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
   
  }

</script>
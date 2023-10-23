<template>
	<div>
		<h1>mycomponent</h1>
    <h1>{{ data.id }}</h1>
    <h1>{{ data.msg }}</h1>
    <button @click="clickHander">点击我向父组件发送数据</button>
	</div>
</template>

<script>
	export default{
    data(){
      return{
        msg:"这是子组件的字符串"
      }
    },
    methods:{
      clickHander(){
        this.$emit("some-event", this.msg)
      }
    },
    props: {data:Object, num : Number},//props
  }

</script>
<style>
</style>
<template>
  <div id="user">
    <h1>查看通知</h1>
    <div style="margin: 20px 0;"></div>
    通知标题：
    <el-input
    type="text"
    placeholder="选中的通知标题"
    v-model="text"
    maxlength="50"
    show-word-limit
    >
    </el-input>
    通知内容：
    <el-input
    type="textarea"
    :rows="15"
    placeholder="选中的通知内容"
    v-model="textarea"
    maxlength="2000"
    show-word-limit
    >
    </el-input>
    发布日期：{{date}}
    <div style="margin-top: 20px">
    <el-button type="success" @click="jump()">确定</el-button>
  </div>
  </div>
</template>

<script>
export default {
  name: 'User',
  data() {
    return {
      text: '',
      textarea: '',
        date:'2016-05-03'
      
    }
  },
  methods:{
    jump(){
        this.$router.push("/company/notice");
    }
  }
}
</script>
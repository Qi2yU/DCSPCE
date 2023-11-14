<style>
</style>
<template>
  <div id="user">
    <h1>新增通知</h1>
    <div style="margin: 20px 0;"></div>
    通知标题：
    <el-input
    type="text"
    placeholder="请输入内容"
    v-model="form.text"
    maxlength="50"
    show-word-limit
    >
    </el-input>
    通知内容：
    <el-input
    type="textarea"
    :rows="15"
    placeholder="请输入内容"
    v-model="form.textarea"
    maxlength="2000"
    show-word-limit
    >
    </el-input>
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
      form:{
        text: '',
        textarea: '',
        current_date: new data()
      }
    }
  },
  methods:{
    jump(){
      const formData={
        text:this.form.text,
        textarea:this.form.textarea,
        gov_id: $id//全局变量
      };
      this.$http.post("/gov_add_notice/add",formData).then(response=>{console.log('成功发送到后端', response);});
      this.$router.push("/layout/notice");
    }
  }
}
</script>
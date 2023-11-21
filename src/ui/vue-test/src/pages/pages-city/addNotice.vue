<template>
  <div>
    <h1>新增通知</h1>
    <div style="margin: 20px 0;"></div>
    通知标题
    <el-input
      type="text"
      placeholder="填写标题"
      v-model="msg_title"
      maxlength="50"
      show-word-limit>
    </el-input>
    通知内容：
    <el-input
      type="textarea"
      :rows="20"
      placeholder="填写内容"
      v-model="msg_content"
      maxlength="2000"
      show-word-limit>
    </el-input>
    <div style="margin-top: 20px;">
      <el-button type="success" @click="addNotice()">发布</el-button>
    </div>
  </div>
</template>

<script>
  var $this = {};
  export default {
    data() {
      return {
        noticeInfo: {

        },
        msg_title: '',
        msg_content: '',
        msg_to_where: '',
      };
    },
    methods: {
      addNotice() {
        console.log(this.msg_title);
        console.log(this.msg_content);
        this.msg_to_where = this.$http.userid.substring(0, 4);
        this.$http({
          url: '/gov_notice/addnotice',
          method: 'post',
          data:JSON.stringify({
            userId: this.$http.userid,
            msg_title: this.msg_title,
            msg_content: this.msg_content,
            msg_to_where: this.msg_to_where,
          }),
          headers:
          {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          const return_value = response.data;
          console.log(return_value),
          this.$router.push('/government-city/notice');
        });
      },
      selectCity() {
        console.log(this.msg_to_where);
      },
    },
  };
</script>
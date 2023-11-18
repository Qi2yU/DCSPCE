<style>
</style>
<template>
  <div id="user">
    <h1>编辑通知</h1>
    <div style="margin: 20px 0;"></div>
    通知标题：
    <el-input
    type="text"
    placeholder="选中的原有标题"
    v-model="editData.msg_title"
    maxlength="50"
    show-word-limit
    >
    </el-input>
    通知内容：
    <el-input
    type="textarea"
    :rows="15"
    placeholder="选中的原有内容"
    v-model="editData.msg_content"
    maxlength="2000"
    show-word-limit
    >
    </el-input>
    <el-select @change="selectCity" class="el-select" v-model="values" placeholder="发布地区">
      <el-option value="5300" label="全省">全省</el-option>
      <el-option value="5301" label="昆明市">昆明市</el-option>
      <el-option value="5303" label="曲靖市">曲靖市</el-option>
      <el-option value="5304" label="玉溪市">玉溪市</el-option>
      <el-option value="5305" label="保山市">保山市</el-option>
      <el-option value="5306" label="昭通市">昭通市</el-option>
      <el-option value="5307" label="丽江市">丽江市</el-option>
      <el-option value="5308" label="普洱市">普洱市</el-option>
      <el-option value="5309" label="临沧市">临沧市</el-option>
      <el-option value="5323" label="楚雄彝族自治州">楚雄彝族自治州</el-option>
      <el-option value="5325" label="红河哈尼族彝族自治州">红河哈尼族彝族自治州</el-option>
      <el-option value="5326" label="文山壮族苗族自治州">文山壮族苗族自治州</el-option>
      <el-option value="5328" label="西双版纳傣族自治州">西双版纳傣族自治州</el-option>
      <el-option value="5329" label="大理白族自治州">大理白族自治州</el-option>
      <el-option value="5331" label="德宏傣族景颇族自治州">德宏傣族景颇族自治州</el-option>
      <el-option value="5333" label="怒江傈僳族自治州">怒江傈僳族自治州</el-option>
      <el-option value="5334" label="迪庆藏族自治州">迪庆藏族自治州</el-option>
    </el-select>
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
      editData: {
        msg_title: "",
        msg_content: "",
      },
      values: "",
      id: '',
    };
  },
  mounted() {
    this.editData.msg_title = this.$route.query.msg_title;
    this.editData.msg_content = this.$route.query.msg_content;
    this.id = this.$route.query.id;
    console.log(this.editData);
    console.log(this.id);
  },
  methods:{
    jump(){
      this.$http({
        url: '/gov_notice/editNotice',
        method: 'post',
        data: JSON.stringify({
          msg_title: this.editData.msg_title,
          msg_content: this.editData.msg_content,
          msg_to_where: this.values,
          id: this.id,
        }),
        headers:
        {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        console.log(response.data);
        this.$router.push("/government-pro/notice");
      });
    },
    selectCity() {
      console.log(this.values);
    },
  },
}
</script>
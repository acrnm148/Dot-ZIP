<template>
  <div class="modal-body">
    {{ curContent }}
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import http from "@/apis/axios.js";
Vue.use(CKEditor);

export default {
  props: ["qna"],
  computed: {
    ...mapGetters(["currentUser", "curContent"])
  },
  data() {
    return {
      content: "",
      CKEditor: null,
      attachFile: false, //체크박스와 연동 위함
      fileList: []
    };
  },
  methods: {},
  mounted() {
    this.userClsf = this.currentUser.userClsf;
    console.log("mounted" + this.userClsf);
  }
};
</script>

<style scoped>
/* deep Selector */
.modal >>> .ck-editor__editable {
  height: 200px;
}
.modal >>> .thumbnail-wrapper {
  margin-top: 5px;
}
.modal >>> .thumbnail-wrapper img {
  width: 100px !important;
  margin-right: 5px;
  max-width: 100%;
}
textarea {
  width: 100%;
  height: 200px;
  padding: 10px;
  box-sizing: border-box;
  border: solid 2px #1e90ff;
  border-radius: 5px;
  font-size: 16px;
  resize: none;
}
</style>

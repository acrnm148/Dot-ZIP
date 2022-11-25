<template>
  <div class="mb-3">
    <label for="contentInsert" class="form-label">내용</label>
    <!-- CKEditor insert-->
    <textarea id="content" v-model="content"></textarea>
    <button
      @click="qnaAnswerInsert"
      id="btnQnaInsert"
      type="button"
      class="btn btn-sm btn-primary float-end"
    >
      등록
    </button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import http from "@/apis/axios.js";
Vue.use(CKEditor);

export default {
  props: ["qna"],
  computed: {
    ...mapGetters(["currentUser"])
  },
  data() {
    return {
      content: "",
      CKEditor: null,
      attachFile: false, //체크박스와 연동 위함
      fileList: []
    };
  },
  methods: {
    initUI() {
      this.content = "";
      this.CKEditor.setData("");
      this.attachFile = false;
      document.querySelector("#inputFileUploadInsert").value = "";
    },
    changeFile(fileEvent) {
      //파일 미리보기
      //첨부할 파일 목록
      this.fileList = [];

      const fileArray = Array.from(fileEvent.target.files);
      fileArray.forEach(file => {
        this.fileList.push(URL.createObjectURL(file));
      });
    },
    async qnaAnswerInsert() {
      let formData = new FormData(); //for multipart
      formData.append("qnaNum", this.qna.qnaId);
      formData.append("content", this.content);
      formData.append("userSeq", this.currentUser.userSeq);

      //file 각각을 formData에 추가
      let attachFiles = document.querySelector("#inputFileUploadInsert").files;
      if (attachFiles.length > 0) {
        const fileArray = Array.from(attachFiles);
        fileArray.forEach(file => formData.append("file", file));
      }

      try {
        let response = await http.post("/qna/ans", formData);
        let { data } = response;
        console.log(data);
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          //등록성공
          // this.$notify.success("글이 등록되었습니다.");
          this.closeModal();
        }
      } catch (error) {
        // this.$alertify.error("글이 등록 과정에서 오류가 발생하였습니다.");
      }
    },
    closeModal() {
      this.$emit("call-parent-insert");
    }
  },
  created() {
    console.log("test: " + this.qna.qnaId);
  },
  async mounted() {
    try {
      this.CKEditor = await ClassicEditor.create(
        document.querySelector("#divEditorInsert")
      );
    } catch (error) {
      // console.error(error);
    }

    //bootstrap event 처리자 등록
    //show.bs.modal <= 해당 모달창이 show될 때 호출
    let $this = this; //$this <= vue component
    this.$el.addEventListener("show.bs.modal", function() {
      // 현 시점의 this는 event 처리자, vue component가 아니다.
      $this.initUI();
    });
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

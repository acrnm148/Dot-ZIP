<template>
  <!-- Modal Insert-->
  <!--부트스트랩의 클래스가 들어가있어야 함-->
  <div
    class="modal fade"
    id="insertModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 쓰기</h5>
          <img
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            src="@/assets/img/board/x_icon.png"
            style="width:20px"
          />
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="titleInsert" class="form-label">제목</label>
            <input
              type="text"
              class="form-control"
              id="titleInsert"
              v-model="title"
            />
          </div>
          <div class="mb-3">
            <label for="contentInsert" class="form-label">내용</label>
            <!-- CKEditor insert-->
            <div id="divEditorInsert2"></div>
          </div>
          <div class="mb-3">
            <div class="form-check">
              <input
                v-model="attachFile"
                class="form-check-input"
                type="checkbox"
                value=""
                id="chkFileUploadInsert"
              />
              <label class="form-check-label" for="chkFileUploadInsert"
                >파일 추가</label
              >
            </div>
          </div>
          <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
            <input
              @change="changeFile"
              type="file"
              id="inputFileUploadInsert"
              multiple
            />
            <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
              <!-- vue 방식으로 첨부파일 이미지 thumbnail 을 보이는 방법 -->
              <img
                v-for="(file, index) in fileList"
                :key="index"
                v-bind:src="file"
                alt=""
              />
            </div>
          </div>
          <button
            @click="qnaInsert"
            id="btnQnaInsert"
            type="button"
            class="btn btn-sm btn-primary float-end"
          >
            등록
          </button>
        </div>
      </div>
    </div>
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
  computed: {
    ...mapGetters(["currentUser"])
  },
  data() {
    return {
      title: "",
      CKEditor: null,
      attachFile: false, //체크박스와 연동 위함
      fileList: []
    };
  },
  methods: {
    initUI() {
      this.title = "";
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
    async qnaInsert() {
      let formData = new FormData(); //for multipart
      formData.append("title", this.title);
      formData.append("content", this.CKEditor.getData());
      formData.append("userSeq", this.currentUser.userSeq);

      //file 각각을 formData에 추가
      let attachFiles = document.querySelector("#inputFileUploadInsert").files;
      if (attachFiles.length > 0) {
        const fileArray = Array.from(attachFiles);
        fileArray.forEach(file => formData.append("file", file));
      }

      try {
        let response = await http.post("/qna", formData);
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
  async mounted() {
    try {
      console.log("??");
      this.CKEditor = await ClassicEditor.create(
        document.querySelector("#divEditorInsert2")
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
</style>

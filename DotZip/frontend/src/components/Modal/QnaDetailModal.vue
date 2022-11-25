<template>
  <!-- Modal Detail-->
  <div
    class="modal fade"
    id="detailModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 상세</h5>
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
          <table class="table table-hover">
            <tbody>
              <tr>
                <td>글 번호</td>
                <td>{{ qna.qnaId }}</td>
              </tr>
              <tr>
                <td>제목</td>
                <td>{{ qna.title }}</td>
              </tr>
              <tr>
                <td>내용</td>
                <td v-html="qna.content"></td>
              </tr>
              <tr>
                <td>작성자</td>
                <td>{{ masking(qna.userEmail) }}</td>
              </tr>
              <tr>
                <td>작성일시</td>
                <td>{{ qna.regDate }} {{ qna.regTime }}</td>
              </tr>
              <!-- 첨부파일 -->
              <tr>
                <td colspan="2">첨부파일</td>
              </tr>
              <tr v-if="qna.fileList.length > 0">
                <td colspan="2">
                  <div v-for="(file, index) in qna.fileList" :key="index">
                    <span class="fileName">{{ file.fileName }}</span>
                    &nbsp;&nbsp;
                    <a
                      type="button"
                      class="btn btn-outline btn-success btn-xs"
                      v-bind:href="file.fileUrl"
                      v-bind:download="file.fileName"
                      >내려받기</a
                    >
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <qna-answer-insert-modal
                    v-bind:qna="qna"
                    v-show="qna.ansYN == 'N'"
                  ></qna-answer-insert-modal>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <qna-answer-detail-modal
                    v-bind:qna="qna"
                    v-show="qna.ansYN == 'Y'"
                  >
                  </qna-answer-detail-modal>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import QnaAnswerInsertModal from "@/components/Modal/QnaAnswerInsertModal.vue";
import QnaAnswerDetailModal from "@/components/Modal/QnaAnswerDetailModal.vue";
export default {
  components: { QnaAnswerInsertModal, QnaAnswerDetailModal },
  props: ["qna"],
  data() {
    return {};
  },
  methods: {
    changeToUpdate() {
      this.$emit("call-parent-change-to-update");
    },
    changeToDelete() {
      this.$emit("call-parent-change-to-delete");
    },
    async qnaDetail() {
      try {
        console.log(this.currentUser.userSeq);

        // let obj = {
        //   qnaNum: qnaId,
        //   userSeq: this.currentUser.userSeq
        // };
        console.log("id:" + this.content);
        let { data } = await http.get(`/qna/ans/${this.qna.qnaId}`);
        console.log("test~~~~" + data.dto.content);
        this.content = data.dto.content;

        if (data.result == "login") {
          this.$router.push("/users/login");
        } else {
          console.log(data.dto);
          // 날짜, 시간 분리
          let { regDt } = data.dto;
          let qnaNew = {
            regDate: util.makeDateStr(
              regDt.date.year,
              regDt.date.month,
              regDt.date.day,
              "-"
            ),
            regTime: util.makeTimeStr(
              regDt.time.hour,
              regDt.time.minute,
              regDt.time.second,
              ":"
            ),
            ...data.dto //나머지는 여기서 받음 - 3dot operator rest
          };
          console.log("userClsf: " + this.currentUser.userClsf);
          console.log("userSeq: " + this.currentUser.userSeq);
          console.log("userSeq: " + data.dto.userSeq);
          if (
            this.currentUser.userClsf == "003" ||
            this.currentUser.userSeq == data.userSeq
          ) {
            //현재 qna 교체
            // this.qna = qnaNew;
            this.detailModal.show();
          } else {
            alert("비밀글로 작성자만 볼 수 있습니다.");
          }
        }
      } catch (error) {
        // console.error(error);
        // this.$notify.error("서버에 문제가 생겼습니다.");
      }
    },
    // 마스킹 이메일 처리 //
    masking(email) {
      // var len = email.split("@")[0].length; // ******@gmail.com
      var len = email.split("@")[0].length - 3; // AB***@gamil.com
      return email.replace(new RegExp(".(?=.{0," + len + "}@)", "g"), "*");
    }
  },
  created() {}
};
</script>

<style></style>

<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.QnA')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <b-card class="mb-4" :title="$t('menu.QnA')">
          <table class="table table-hover" id="list">
            <colgroup>
              <col width="10%" />
              <col width="40%" />
              <col width="10%" />
              <col width="15%" />
              <col width="10%" />
            </colgroup>
            <thead>
              <tr>
                <th scope="col">No</th>
                <th scope="col">제목</th>
                <th scope="col">상태</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일자</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in list"
                :key="index"
                @click="qnaDetail(row.qnaId)"
                style="cursor: pointer"
              >
                <td>{{ row.qnaId }}</td>
                <td>{{ row.title }}</td>
                <td v-show="row.ansYN == 'Y'"><strong>답변완료</strong></td>
                <td v-show="row.ansYN == 'N'">답변대기</td>
                <td>{{ masking(row.userEmail) }}</td>
                <td>{{ row.regDt.date | makeDateStr(".") }}</td>
              </tr>
            </tbody>
          </table>
          <pagination-u-i
            v-bind:listRowCount="listRowCount"
            v-bind:pageLinkCount="pageLinkCount"
            v-bind:currentPageIndex="currentPageIndex"
            v-bind:totalListItemCount="totalListItemCount"
            v-on:call-parent-move-page="movePage"
          ></pagination-u-i>
          <button
            v-show="userClsf == '001'"
            class="btn btn-success"
            type="button"
            @click="showInsertModal"
          >
            글쓰기
          </button>

          <insert-modal
            v-on:call-parent-insert="closeAfterInsert"
          ></insert-modal>
          <detail-modal
            v-bind:qna="qna"
            v-on:call-parent-change-to-update="changeToUpdate"
            v-on:call-parent-change-to-delete="changeToDelete"
          ></detail-modal>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
import http from "@/apis/axios.js"; //axios객체
import util from "@/apis/util.js";
import PaginationUI from "@/components/Common/PaginationUI.vue";
import InsertModal from "@/components/Modal/QnaInsertModal.vue"; //vue 컴포넌트
import DetailModal from "@/components/Modal/QnaDetailModal.vue"; //vue 컴포넌트
import { Modal } from "bootstrap";
export default {
  components: {
    PaginationUI,
    // Pagination,
    InsertModal,
    DetailModal
  },
  computed: {
    ...mapGetters(["currentUser", "curContent"])
  },
  data() {
    return {
      userClsf: "",

      boardId: 0,
      userSeq: 0,

      limit: 10,
      offset: 0,
      searchWord: "",
      list: [],

      //row
      totalListItemCount: 0,
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      // modal
      insertModal: null, //bootstrap Modal 객체를 할당(ui component를 전달)
      detailModal: null,
      updateModal: null,

      //detail
      qna: {
        ansYN: "",
        qnaId: 0,
        userSeq: 0,
        type: 0,
        title: "",
        content: "",
        userEmail: "",
        regDate: "", //백엔드로부터 받는 dto는 regDt만 받는데 이를 분해해서 regDate, regTime으로 나누려는 생각
        regTime: "",
        password: "",
        fileList: []
      }
    };
  },
  methods: {
    ...mapActions(["getContentAction"]),

    async qnaDetail(qnaId) {
      try {
        console.log(qnaId);
        console.log(this.currentUser.userSeq);

        let obj = {
          qnaId: qnaId,
          userSeq: this.currentUser.userSeq
        };
        let { data } = await http.get(`/qna/${qnaId}`, obj);
        this.cnt = data.count;
        console.log(this.cnt);

        console.log(data);
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
            this.currentUser.userSeq == data.dto.userSeq
          ) {
            //현재 board 교체
            this.qna = qnaNew;
            this.detailModal.show();
          } else {
            alert("비밀글로 작성자만 볼 수 있습니다.");
          }
        }
      } catch (error) {
        // console.error(error);
        // this.$notify.error("서버에 문제가 생겼습니다.");
      }
      this.getContentAction(qnaId);
    },
    async qnaList() {
      console.log(this.userClsf);
      let params = {
        limit: this.limit,
        offset: this.offset
      };

      try {
        // axios가 json으로 parameter를 보내는 방법 : 객체를 전달
        let response = await http.get("/qna", { params });
        let { data } = response;
        // console.log(response);

        // interceptor session check fail
        if (data.result == "login") {
          this.$router.push("/users/login");
        } else {
          this.list = data.list;
          this.totalListItemCount = data.count;
        }
      } catch (error) {
        // console.error(error);
        // this.$alertify.error("서버에 문제가 생겼습니다.");
      }
    },
    changeToUpdate() {
      this.detailModal.hide();
      this.updateModal.show();
    },
    movePage(pageIndex) {
      this.offset = (pageIndex - 1) * this.listRowCount;
      this.currentPageIndex = pageIndex;
      this.qnaList();
    },
    showInsertModal() {
      this.insertModal.show();
    },
    closeAfterInsert() {
      this.insertModal.hide();
      this.qnaList();
    },
    closeAfterUpdate() {
      this.updateModal.hide();
      this.qnaList();
    },
    changeToDelete() {
      this.detailModal.hide();
      var $this = this;
      $this.qnaDelete();
    },
    // 마스킹 이메일 처리 //
    masking(email) {
      // var len = email.split("@")[0].length; // ******@gmail.com
      var len = email.split("@")[0].length - 3; // AB***@gamil.com
      return email.replace(new RegExp(".(?=.{0," + len + "}@)", "g"), "*");
    }
  },
  created() {
    this.userClsf = this.currentUser.userClsf;
    this.qnaList();
  },
  mounted() {
    //모달 객체를 생성해서 data의 변수에 할당
    this.insertModal = new Modal(document.querySelector("#insertModal"));
    this.detailModal = new Modal(document.querySelector("#detailModal"));
  },
  filters: {
    makeDateStr(date, type) {
      return util.makeDateStr(date.year, date.month, date.day, type); //import한 util을 이용해서 filter
    }
  },
  watch: {
    qna() {
      this.qnaList();
    }
  }
};
</script>
<style>
#list {
  text-align: center;
}
</style>

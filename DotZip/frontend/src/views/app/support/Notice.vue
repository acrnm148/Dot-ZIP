<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.notice')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <b-card class="mb-4" :title="$t('menu.notice')">
          <!-- <b-table striped hover :items="list"></b-table> -->
          <table class="table table-hover" id="list">
            <colgroup>
              <col width="10%" />
              <col width="40%" />
              <col width="10%" />
              <col width="15%" />
              <col width="8%" />
            </colgroup>
            <thead>
              <tr>
                <th scope="col">No</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일자</th>
                <th scope="col">조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in list"
                :key="index"
                @click="boardDetail(row.boardId)"
                style="cursor: pointer"
              >
                <td>{{ row.boardId }}</td>
                <td>{{ row.title }}</td>
                <td>{{ row.userName }}</td>
                <td>{{ row.regDt.date | makeDateStr(".") }}</td>
                <td>{{ row.readCount }}</td>
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
            v-show="userClsf == '003'"
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
            v-bind:board="board"
            v-on:call-parent-change-to-update="changeToUpdate"
            v-on:call-parent-change-to-delete="changeToDelete"
          ></detail-modal>
          <update-modal
            v-bind:board="board"
            v-on:call-parent-update="closeAfterUpdate"
          ></update-modal>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import http from "@/apis/axios.js"; //axios객체
import util from "@/apis/util.js";
import PaginationUI from "@/components/Common/PaginationUI.vue";
// import Pagination from "@/components/Common/Pagination.vue";
import InsertModal from "@/components/Modal/BoardInsertModal.vue"; //vue 컴포넌트
import DetailModal from "@/components/Modal/BoardDetailModal.vue"; //vue 컴포넌트
import UpdateModal from "@/components/Modal/BoardUpdateModal.vue";
import { Modal } from "bootstrap";
export default {
  components: {
    PaginationUI,
    // Pagination,
    InsertModal,
    DetailModal,
    UpdateModal
  },
  computed: {
    ...mapGetters(["currentUser"])
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
      board: {
        boardId: 0,
        title: "",
        content: "",
        userName: "",
        regDate: "", //백엔드로부터 받는 dto는 regDt만 받는데 이를 분해해서 regDate, regTime으로 나누려는 생각
        regTime: "",
        readCount: 0,
        fileList: [],
        sameUser: false
      }
    };
  },
  methods: {
    async boardDetail(boardId) {
      //row
      // 백엔드 요청 - 결과
      // DetailModal <- 결과 : data항목에 board 객체를 추가하고 props로 DetailModal에 넘겨준다. 백엔드 요청 결과를 data의 board를 변경하면 자동 반영
      // DetailModal show
      try {
        console.log(boardId);
        console.log(this.currentUser.userSeq);
        // let response = await http.post(`/boards`, {boardId: boardId, userSeq: this.currentUser.userSeq});
        // let {data} = response;
        let obj = {
          boardId: boardId,
          userSeq: this.currentUser.userSeq
        };
        let { data } = await http.post(`/boards`, obj);

        console.log(data);
        if (data.result == "login") {
          this.$router.push("/users/login");
        } else {
          console.log(data.dto);
          // 날짜, 시간 분리
          let { regDt } = data.dto;
          let boardNew = {
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

          //현재 board 교체
          this.board = boardNew;

          this.detailModal.show();
        }
      } catch (error) {
        console.error(error);
        // this.$notify.error("서버에 문제가 생겼습니다.");
      }
    },
    async boardList() {
      console.log(this.userClsf);
      let params = {
        limit: this.limit,
        offset: this.offset,
        searchWord: this.searchWord
      };

      try {
        // axios가 json으로 parameter를 보내는 방법 : 객체를 전달
        let response = await http.get("/boards", { params });
        let { data } = response;
        console.log(response);

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
      this.boardList();
    },
    showInsertModal() {
      this.insertModal.show();
    },
    closeAfterInsert() {
      this.insertModal.hide();
      this.boardList();
    },
    closeAfterUpdate() {
      this.updateModal.hide();
      this.boardList();
    },
    changeToDelete() {
      this.detailModal.hide();
      var $this = this;
      $this.boardDelete();
      // this.$alertify.confirm(
      //   "이 글을 삭제하시겠습니까?",
      //   function() {
      //     //this.boardDelete(); //이렇게 쓰는 this는 컴포넌트를 가리키는게 아님.
      //     $this.boardDelete();
      //   },
      //   function() {
      //     console.log("cancel");
      //   }
      // );
    },
    async boardDelete() {
      try {
        let response = await http.delete("/admin/boards/" + this.board.boardId);
        let { data } = response;
        if (data.result == "login") {
          this.$router.push("/users/login");
        } else {
          // this.$alertify.success("글이 삭제되었습니다.");
          this.boardList();
        }
      } catch (error) {
        // console.error(error);
      }
    }
  },
  created() {
    this.userClsf = this.currentUser.userClsf;
    this.boardList();
  },
  mounted() {
    //모달 객체를 생성해서 data의 변수에 할당
    this.insertModal = new Modal(document.querySelector("#insertModal"));
    this.detailModal = new Modal(document.querySelector("#detailModal"));
    this.updateModal = new Modal(document.querySelector("#updateModal"));
  },
  filters: {
    makeDateStr(date, type) {
      return util.makeDateStr(date.year, date.month, date.day, type); //import한 util을 이용해서 filter
    }
  },
  watch: {
    board() {
      this.boardList();
    }
  }
};
</script>
<style>
#list {
  text-align: center;
}
</style>

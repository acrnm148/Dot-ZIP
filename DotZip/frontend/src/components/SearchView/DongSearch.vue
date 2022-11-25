<template>
  <b-row>
    <b-colxx xxs="12">
      <b-card class="mb-2">
        <b-form>
          <b-row>
            <b-colxx xxs="12" md="4">
              <b-form-group label="시">
                <v-select
                  @input="onClickSidoSelect"
                  v-model="sido"
                  :options="sidoList"
                  :dir="direction"
                />
              </b-form-group>
            </b-colxx>
            <b-colxx xxs="12" md="4">
              <b-form-group label="구">
                <v-select
                  @input="onClickGugunSelect"
                  v-model="gugun"
                  :options="gugunList"
                  :dir="direction"
                />
              </b-form-group>
            </b-colxx>
            <b-colxx xxs="12" md="4">
              <b-form-group label="동">
                <v-select v-model="dong" :options="dongList" :dir="direction" />
              </b-form-group>
            </b-colxx>
          </b-row>
          <b-row class="button-wrap">
            <div class="checkbox-wrap">
              <b-button
                variant="outline-primary"
                class="mr-2 px-4"
                @click="checkButtonCheck(1)"
                :pressed="checkedCheckboxes.indexOf(1) !== -1"
                >{{ $t("button.hospital") }}</b-button
              >
              <b-button
                variant="outline-primary"
                class="mr-2"
                @click="checkButtonCheck(2)"
                :pressed="checkedCheckboxes.indexOf(2) !== -1"
                >{{ $t("button.convenience") }}</b-button
              >
              <b-button
                variant="outline-primary"
                class="mr-2 px-4"
                @click="checkButtonCheck(3)"
                :pressed="checkedCheckboxes.indexOf(3) !== -1"
                >{{ $t("button.bank") }}</b-button
              >
            </div>

            <div v-if="flag">
              <b-button
                @click="onClickDeleteBookmarkedArea"
                v-if="isBookmarkedArea"
                class="mb-1 mr-4"
                variant="danger"
                >관심 지역 해제</b-button
              >
              <b-button
                @click="onClickAddBookmarkedArea"
                v-else
                class="mb-1 mr-4"
                variant="success"
                >관심 지역 등록</b-button
              >

              <b-button
                @click="onClickSearchBtn"
                class="mb-1"
                variant="primary default"
                >{{ $t("button.search") }}</b-button
              >
            </div>
            <div v-else>
              <b-button
                @click="onClickSearchBtn"
                class="mb-1"
                variant="primary default"
                >{{ $t("button.search") }}</b-button
              >
            </div>
          </b-row>
        </b-form>
      </b-card>
    </b-colxx>
  </b-row>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import { defaultDirection } from "@/constants/config";

export default {
  data() {
    return {
      checkedCheckboxes: [],
      sido: { value: null, label: "선택하세요" },
      gugun: null,
      dong: null,
      direction: defaultDirection,
      curHouseList: [],
      flag: false
    };
  },
  components: {
    "v-select": vSelect
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_HOUSE_LIST();
    this.getSidoList();
    if (this.$route.params.item) {
      const {
        cityCode,
        gugunCode,
        dongCode,
        cityName,
        gugunName,
        name
      } = this.$route.params.item;
      this.sido = { value: cityCode, label: cityName };
      this.gugun = { value: gugunCode, label: gugunName };
      this.dong = { value: dongCode, label: name };
      this.onClickSearchBtn();
    }
  },
  computed: {
    ...mapGetters([
      "sidoList",
      "gugunList",
      "dongList",
      "houseList",
      "house",
      "currentUser",
      "isBookmarkedArea"
    ])
  },
  watch: {
    houseList() {
      return (this.curHouseList = [...this.houseList]);
    },
    dong() {
      if (this.dong !== null && this.dong.value !== null) {
        this.flag = true;
        const dongCode = this.dong.value;
        this.getBookMarkAreaAction({
          dongCode,
          userSeq: this.currentUser.userSeq
        });
      }
    }
  },
  methods: {
    ...mapActions([
      "getSidoList",
      "getGugunList",
      "getDongList",
      "getHouseListByDong",
      "toggleList",
      "getBookMarkAreaAction",
      "deleteBookMarkAreaAction",
      "addBookMarkAreaAction"
    ]),
    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "SET_IS_BOOKMARK_AREA"
    ]),
    onClickDeleteBookmarkedArea() {
      this.deleteBookMarkAreaAction({
        userSeq: this.currentUser.userSeq,
        dongCode: this.dong.value
      });
      this.$store.commit("SET_IS_BOOKMARK_AREA", false);
      this.$notify(
        "error",
        "북마크 삭제",
        "해당 지역의 북마크가 삭제되었습니다.",
        {
          duration: 3000,
          permanent: false
        }
      );
    },
    onClickAddBookmarkedArea() {
      this.addBookMarkAreaAction({
        userSeq: this.currentUser.userSeq,
        dongCode: this.dong.value
      });
      this.$store.commit("SET_IS_BOOKMARK_AREA", true);
      this.$notify(
        "success",
        "북마크 등록",
        "해당 지역의 북마크가 등록되었습니다.",
        {
          duration: 3000,
          permanent: false
        }
      );
    },
    onClickSidoSelect() {
      // gugunList 가져오기
      this.CLEAR_GUGUN_LIST();
      this.gugun = { value: null, label: "선택하세요" };
      this.dong = null;
      this.flag = false;
      if (this.sido.value !== null) {
        this.getGugunList(this.sido.label);
      } else {
        this.gugun = null;
      }
    },
    onClickGugunSelect() {
      // dongList 가져오기
      this.CLEAR_DONG_LIST();
      this.flag = false;
      this.dong = { value: null, label: "선택하세요" };
      if (this.gugun.value !== null) {
        this.getDongList(this.gugun.label);
      }
    },

    async onClickSearchBtn() {
      // 클릭시 현재 dong 이름으로 데이터 뽑아오기
      if (this.sido.value === null) {
        this.$notify("error", "Search Error", "시를 선택해주세요", {
          duration: 3000,
          permanent: false
        });
      } else if (this.dong === null) {
        this.$notify("error", "Search Error", "구를 선택해주세요.", {
          duration: 3000,
          permanent: false
        });
      } else if (this.dong.value === null) {
        this.$notify("error", "Search Error", "동을 선택해주세요", {
          duration: 3000,
          permanent: false
        });
      } else {
        console.log(this.currentUser.userSeq);
        await this.getHouseListByDong({
          dong: this.dong.label,
          userSeq: this.currentUser.userSeq
        });
        // 이미 검색 창이 열려있다면 토글 작동 할 필요 없다.
        if (this.curHouseList.length === 0) {
          // 없는 경우에는
          this.$notify(
            "error",
            "Search Error",
            "검색 결과가 존재하지 않습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
          document.querySelector(".house-list-wrapper").classList.add("close");
          this.toggleList(false);
        } else {
          document
            .querySelector(".house-list-wrapper")
            .classList.remove("close");
          this.toggleList(true);
        }
      }
    },
    checkButtonCheck(i) {
      const index = this.checkedCheckboxes.indexOf(i);
      if (index === -1) {
        this.checkedCheckboxes.push(i);
      } else {
        this.checkedCheckboxes.splice(index, 1);
      }
    }
  }
};
</script>

<style>
.select-form {
  display: flex;
}
.button-wrap {
  padding: 0 15px;
  justify-content: space-between;
}
.checkbox-wrap {
  display: flex;
}
</style>

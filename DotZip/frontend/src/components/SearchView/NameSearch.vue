<template>
  <div class="search-wrap">
    <input v-model="searchWord" type="text" />
    <button @click="onClickSearchBtn" class="name-search-btn">검색</button>
  </div>
</template>

<script>
import { mapMutations, mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      searchWord: "",
      curHouseList: []
    };
  },
  created() {
    this.CLEAR_HOUSE_LIST();
  },
  computed: {
    ...mapGetters(["currentUser", "houseList"])
  },
  watch: {
    houseList() {
      this.curHouseList = [...this.houseList];
    }
  },
  methods: {
    ...mapMutations(["CLEAR_HOUSE_LIST"]),
    ...mapActions([
      "getHouseListByName",
      "toggleList",
      "getBookMarkAreaAction",
      "deleteBookMarkAreaAction",
      "addBookMarkAreaAction",
      "SET_IS_BOOKMARK_AREA"
    ]),
    async onClickSearchBtn() {
      console.log(this.currentUser.userSeq);
      await this.getHouseListByName({
        searchWord: this.searchWord,
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
        document.querySelector(".house-list-wrapper").classList.remove("close");
        this.toggleList(true);
      }
    }
  }
};
</script>

<style>
.search-wrap {
  width: 400px;
  display: flex;
  align-items: center;
  margin: 0 auto;
}

.search-wrap > input {
  height: 32px;
  flex-basis: 80%;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
.name-search-btn {
  border: none;
  outline: none;
  cursor: pointer;
  font-weight: 700;
  font-size: 20px;
  flex-basis: 15%;
  background-color: #74b9ff;
  border-radius: 15px;
  margin-left: 10px;
}
</style>

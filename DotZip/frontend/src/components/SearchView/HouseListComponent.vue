<template>
  <div style="width:100%;height:100%">
    <div class="house-list-wrapper close">
      <div class="result_list_wrapper">
        <house-item-component v-for="(item, index) in houseList" :key="index" :item="item"></house-item-component>
      </div>
      <button @click="onClickOpenListBtn" class="open_list_btn">
        <i v-if="isOpen" class="simple-icon-arrow-left"></i>
        <i v-else class="simple-icon-arrow-right"></i>
      </button>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";
import HouseItemComponent from "./HouseItemComponent.vue";

export default {
  components: {HouseItemComponent},
  computed: {
    ...mapMutations(["CLEAR_TOGGLE_LIST"]),
    ...mapGetters(["houseList", "house", "isOpen"]),
  },
  methods: {
    ...mapActions(["toggleList"]),
    onClickOpenListBtn() {
      if (this.isOpen) {
        document.querySelector(".house-list-wrapper").classList.add("close");
        this.toggleList(false);
      } else {
        document.querySelector(".house-list-wrapper").classList.remove("close");
        this.toggleList(true);
      }
    },
  },
  watch: {
    isOpen() {
      console.log(this.isOpen);
    },
  },
  mounted() {
    this.$store.commit("CLEAR_TOGGLE_LIST");
  },
};
</script>

<style>
.house-list-wrapper {
  width: 400px;
  height: 100%;
  background-color: white;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 2 !important;
  transition-duration: 1500ms;
  -webkit-box-shadow: 2px 0px 6px 1px #3d3d3d;
  box-shadow: 2px 0px 6px 1px #3d3d3d;
}

.house-list-wrapper.close {
  transform: translateX(-400px);
}

.open_list_btn {
  all: unset;
  text-align: center;
  line-height: 52px;
  width: 22px;
  height: 49px;
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
  background-color: white;
  font-size: 20px;
  position: absolute;
  right: -23px;
  top: 300px;
  transition-duration: 1000ms;
}

.open_list_btn:hover {
  background-color: #3188ff;
}
.result_list_wrapper {
  width: 100%;
  height: 100%;
  overflow-y: scroll;
}

/* 아래의 모든 코드는 영역::코드로 사용 */
.result_list_wrapper::-webkit-scrollbar {
  width: 10px; /* 스크롤바의 너비 */
}
.result_list_wrapper::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #3188ff; /* 스크롤바의 색상 */

  border-radius: 10px;
}

.result_list_wrapper::-webkit-scrollbar-track {
  background: rgba(33, 122, 244, 0.1); /*스크롤바 뒷 배경 색상*/
}
</style>

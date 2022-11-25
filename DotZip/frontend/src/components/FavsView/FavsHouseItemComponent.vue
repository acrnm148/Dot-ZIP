<template>
  <div class="house_card">
    <div class="house_info">
      <h3 class="house_info_title">
        {{ item.aptName }}
      </h3>
      <div class="house_info_description">
        <span>거래 금액 : {{ item.dealAmount }}만원</span>
        <span>전용 면적 : {{ item.area }}</span>
        <span
          >거래일 : {{ item.dealYear }}.{{ item.dealMonth }}.{{
            item.dealDay
          }}</span
        >
      </div>
    </div>
    <div class="list_item_btn_wrapper">
      <i
        class="simple-icon-location-pin moveHouseLocation"
        @click="onClickMoveHouseLocation"
      ></i>
      <font-awesome-icon
        @click="onClickAddBookMarkHouse"
        class="favs_btn"
        :class="{ bookmarked: getIsBookmarked() }"
        icon="fa-solid fa-star"
      />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  components: {},
  data() {
    return {
      curHouse: {
        aptName: "",
        area: "",
        buildYear: "",
        cityName: "",
        code: "",
        dealAmount: "",
        dealDay: "",
        dealMonth: "",
        dealYear: "",
        dong: "",
        floor: "",
        gugunName: "",
        houseNo: 0,
        jibun: "",
        lat: "",
        lng: "",
        no: 0
      }
    };
  },
  props: ["item"],
  computed: {
    ...mapGetters(["house", "currentUser"])
  },
  methods: {
    ...mapActions([
      "getHouse",
      "addBookMarkHouseAction",
      "deleteBookMarkHouseAction"
    ]),
    onClickMoveHouseLocation() {
      // 1. 클릭시 해당 위치로 마커 이동
      // 1.1 현재 선택된 위치를 store에 저장하고 , 클릭시 해당 값을 변경
      // 2. 클릭시 상세 정보 데이터를 가져온다.
      // 2.1 그 안에 위도 경도 데이터가 담겨져 있기 때문에
      this.getHouse(this.item.houseNo);
      console.log(this.house);
    },
    onClickDetailHouseBtn() {
      // 클릭시 Detail 정보 가져 오고 가공해서 화면에 보여주기
    },
    onClickAddBookMarkHouse() {
      // 클릭시 BookMark에 해당 집을 추가하고 , 즉각적으로 색변경 (야매로도 가능)

      // 클릭 시 부모에 이벤트 전달
      // 부모에서 해야할 일
      // 만약 현재 북마크가 체크된 상태라면 관심매물 해제 함수를 진행
      if (this.getIsBookmarked()) {
        try {
          this.deleteBookMarkHouseAction({
            houseNo: this.item.houseNo,
            userSeq: this.currentUser.userSeq
          });

          this.$notify(
            "success",
            "BOOKMARK DELETE",
            "MY 관심 매물에서 제거 했습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
        } catch (error) {
          this.$notify(
            "error",
            "BOOKMARK ERROR",
            "해당 지역을 관심 매물에서 제거하지 못했습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
        }
      } else {
        // 북마크가 아닌 상태라면 관심매물 추가 함수를 진행
        try {
          this.addBookMarkHouseAction({
            houseNo: this.item.houseNo,
            userSeq: this.currentUser.userSeq
          });
          this.$notify(
            "success",
            "BOOKMARK ADD",
            "MY 관심 매물을 추가 했습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
        } catch (error) {
          this.$notify(
            "error",
            "BOOKMARK ERROR",
            "해당 지역을 관심 매물에 추가하지 못했습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
        }
      }

      if (this.getIsBookmarked()) {
        this.item.bookmarked = 0;
      } else {
        this.item.bookmarked = 1;
      }
    },
    getIsBookmarked() {
      if (this.item.bookmarked === 1) {
        return true;
      } else {
        return false;
      }
    }
  },
  watch: {
    house() {
      this.curHouse = this.house;
    }
  },
  mounted() {
    console.log(this.item);
  }
};
</script>

<style>
.house_card {
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px 6px 20px;
  border-bottom: 1px solid lightgray;
}
.house_info {
  flex-basis: 80%;
}
.house_info_title {
  display: flex;
  font-size: 24px;
  align-items: center;
  font-weight: 500;
}
.moveHouseLocation {
  font-size: 25px;
  transition-duration: 800ms;
  cursor: pointer;
  position: absolute;
  right: 21px;
  top: 17px;
  color: #f15b6c;
}

.moveHouseLocation:hover {
  color: #f15b6c;
}

.house_info_description {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}
.house_info_description span {
  margin-bottom: 5px;
}
.house_info_description span:last-child {
  margin-bottom: 0;
}

.list_item_btn_wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.favs_btn {
  font-size: 25px;
  cursor: pointer;
  transition-duration: 800ms;
  margin-bottom: 20px;
}

.favs_btn.bookmarked {
  color: #fff200;
}

.favs_btn:hover {
  color: #fff200;
}
</style>

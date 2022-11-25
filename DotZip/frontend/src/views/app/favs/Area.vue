<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.fArea')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12">
        <b-card class="mb-4">
          <div class="area_wrap">
            <div
              v-for="(item, index) in bookmarkAreaList"
              :key="index"
              class="area_item"
            >
              <div class="name_wrap">
                {{ item.cityName }}
                {{ item.gugunName }}
                {{ item.name }}
              </div>
              <div class="control_wrap">
                <b-button @click="moveSearchDongPage(item)" variant="primary">
                  매물 보러 가기
                </b-button>
                <b-button
                  @click="onClickDeleteBookMarkArea(item)"
                  variant="danger"
                  >해제</b-button
                >
              </div>
            </div>
          </div>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["currentUser", "bookmarkAreaList"])
  },
  methods: {
    ...mapActions(["getBookMarkAreaListAction", "deleteBookMarkAreaAction"]),
    moveSearchDongPage(item) {
      this.$router.push({
        name: "SearchDong",
        params: {
          item
        }
      });
    },
    onClickDeleteBookMarkArea(item) {
      // 클릭시 BookMark에 해당 집을 추가하고 , 즉각적으로 색변경 (야매로도 가능)

      // 클릭 시 부모에 이벤트 전달
      // 부모에서 해야할 일
      // 만약 현재 북마크가 체크된 상태라면 관심매물 해제 함수를 진행
      if (item.bookmarked) {
        console.log(item);
        try {
          this.deleteBookMarkAreaAction({
            userSeq: this.currentUser.userSeq,
            dongCode: item.dongCode
          });
          this.$store.commit("SET_IS_BOOKMARK_AREA", false);
          this.$notify(
            "success",
            "북마크 삭제",
            "해당 지역의 북마크가 삭제되었습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
        } catch (error) {
          this.$notify(
            "error",
            "BOOKMARK ERROR",
            "해당 지역을 관심 지역에서 제거하지 못했습니다.",
            {
              duration: 3000,
              permanent: false
            }
          );
        }
      }

      // if (this.getIsBookmarked()) {
      //   this.item.bookmarked = 0;
      // } else {
      //   this.item.bookmarked = 1;
      // }
    }
  },
  created() {
    this.getBookMarkAreaListAction({
      userSeq: this.currentUser.userSeq,
      limit: 10,
      offset: 0
    });
  }
};
</script>
<style>
.area_wrap {
  width: 50%;
  height: 400px;
  margin: 0 auto;
}
.area_item {
  height: 60px;
  padding: 10px;
  font-size: 18px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid black;
}
</style>

<template>
  <div>
    <b-row>
      <b-colxx xxs="12">
        <piaf-breadcrumb :heading="$t('menu.home')" />
        <div class="separator mb-5"></div>
      </b-colxx>
    </b-row>
    <b-row>
      <b-colxx xxs="12" class="profile-page-wrap">
        <b-card no-body class="profile-wrap">
          <div class="position-absolute card-top-buttons profile-info-wrap">
            <b-button
              @click="onClickOpenEditModal"
              variant="outline-white"
              class="icon-button"
            >
              <i class="simple-icon-pencil" />
            </b-button>
          </div>
          <single-lightbox
            thumb="/assets/img/profiles/noProfile.jpg"
            large="/assets/img/profiles/noProfile.jpg"
            class-name="card-img-top"
          />
          <b-card-body>
            <div class="user__info__wrap">
              <b-badge
                v-if="getUserType"
                class="user__type"
                pill
                variant="warning"
                >일반회원</b-badge
              >

              <b-badge v-else class="user__type" pill variant="dark"
                >관리자</b-badge
              >

              <p class="text-muted home__area__title mb-2">
                {{ $t("menu.userName") }}
              </p>
              <p class="mb-3 home__username">
                {{ this.currentUser.userName }}
              </p>
              <p class="text-muted home__area__title mb-2">
                {{ $t("menu.email") }}
              </p>
              <p class="mb-3 home__username">
                {{ this.currentUser.userEmail }}
              </p>
              <!-- <p class="text-muted text-small mb-2">{{ $t("pages.location") }}</p>
            <p class="mb-3">Nairobi, Kenya</p> -->
              <p class="text-muted mb-2 home__area__title">
                {{ $t("pages.area-title") }}
              </p>
              <p class="mb-3">
                <b-badge
                  v-for="(item, index) in bookmarkAreaList"
                  :key="index"
                  variant="primary"
                  class="mr-2 mb-2 home__area"
                  @click="onClickBookmarkArea(item.name)"
                  pill
                  >{{ item.name }}</b-badge
                >
              </p>
            </div>
          </b-card-body>
        </b-card>
        <b-card class="rightContent">
          <router-view></router-view>
        </b-card>
      </b-colxx>
    </b-row>
  </div>
</template>

<script>
import SingleLightbox from "@/components/Profile/SingleLightbox";
import { mapGetters, mapActions, mapMutations, mapState } from "vuex";
import { lineChartData } from "@/data/charts.js";

export default {
  // 얻어야 하는 데이터
  // 내 관심 지역 limit 4개 -> 현재 관심 지역이 없다면 클릭하면
  // SearchDong 페이지로 이동시키는 버튼 추가
  // 현재 로그인 한 사람의 정보
  data() {
    return {
      repAreaList: [],
      isNotExistBookmarkArea: false,
      lineChartData
    };
  },
  computed: {
    ...mapGetters([
      "currentUser",
      "bookmarkAreaList",
      "bookmarkedHouseCountOfUser",
      "bookmarkedAreaCountOfUser"
    ]),
    ...mapState(["chartData"])
  },
  methods: {
    ...mapMutations(["SET_SELECTED_AREA"]),
    ...mapActions([
      "getBookMarkAreaListAction",
      "getBookMarkAreaCountAction",
      "getBookMarkHouseCountAction"
    ]),
    getUserType() {
      if (this.currentUser.userClsf === "001") {
        return true;
      } else if (this.currentUser.userClsf === "003") {
        return false;
      }
    },
    onClickBookmarkArea(name) {
      this.SET_SELECTED_AREA(name);
      const data = [];
      for (let i = 0; i < 7; i++) {
        const num = Math.floor(Math.random() * 100) + 1;
        data.push(num);
      }
      this.lineChartData.datasets.data = [...data];
      this.chartData = [...this.lineCharData];
    },
    onClickOpenEditModal() {}
  },
  components: {
    "single-lightbox": SingleLightbox
  },
  created() {
    this.getBookMarkAreaListAction({
      userSeq: this.currentUser.userSeq,
      limit: 5,
      offset: 0
    });

    this.getBookMarkAreaCountAction(this.currentUser.userSeq);
    this.getBookMarkHouseCountAction(this.currentUser.userSeq);
  },
  watch: {
    bookmarkAreaList() {
      if (this.bookmarkAreaList.length > 0) {
        this.isNotExistBookmarkArea = false;
      } else {
        this.isNotExistBookmarkArea = true;
      }
    }
  }
};
</script>

<style>
.profile-page-wrap {
  position: relative;
  display: flex;
  justify-content: space-between;
}
.profile-wrap {
  flex-basis: 20%;
}

.user__info__wrap {
  position: relative;
}

.user__type {
  font-size: 13px;
  position: absolute;
  right: 0;
  top: 0;
}
.home__username {
  font-size: 20px;
}
.home__area__title {
  font-size: 15px;
  font-weight: 500;
}
.home__area {
  font-size: 14px;
  cursor: pointer;
}

.rightContent {
  flex-basis: 78%;
  background-color: white;
}
</style>

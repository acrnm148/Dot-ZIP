<template>
  <div class="dashboard__wrapper">
    <div class="dashboard__main">
      <div class="follow__wrap">
        <!-- 현재 팔로잉 수 & 현재 팔로워 수 -->
        <icon-card
          class="follow__wrap__item"
          :title="$t('dashboards.follower')"
          icon="simple-icon-user"
          :value="followerCount"
          :code="follower"
        />
        <icon-card
          class="follow__wrap__item"
          :title="$t('dashboards.following')"
          icon="simple-icon-user"
          :value="followingCount"
          :code="following"
        />
        <icon-card
          class="follow__wrap__item"
          :title="$t('dashboards.area-count')"
          icon="iconsminds-map2"
          :value="bookmarkedAreaCountOfUser"
          :code="area"
        />
        <icon-card
          class="follow__wrap__item"
          :title="$t('dashboards.house-count')"
          icon="iconsminds-home"
          :value="bookmarkedHouseCountOfUser"
          :code="house"
        />
      </div>
      <div class="chart__wrap">
        <!-- header : 차트로 관심지역으로 등록한 대표 3개 지역의 버튼을 생성 -->
        <div v-if="isNone" class="chart__header">{{ name }}</div>
        <div v-else class="chart__header">{{ name }}</div>
        <!-- content chart : 차트로 현재 선택된 지역의 거래량을 년도를 기준으로 차트 생성 -->
        <!-- 또는 내가 관심 지역으로 등록한 동을 팔로잉한 사람 중 몇명이 관심 지역으로 등록했는지 차트로 보여주기 -->
        <div class="chart__main">
          <b-row>
            <b-colxx xxs="12" class="mb-4">
              <div class="chart-container">
                <line-chart :data="lineChartData" shadow />
              </div>
            </b-colxx>
          </b-row>
        </div>
      </div>
    </div>
    <div class="dashboard__aside">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import IconCard from "@/components/Cards/IconCard";
import LineChart from "@/components/Charts/Line";
import { lineChartData } from "@/data/charts.js";
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      following: "following",
      follower: "follower",
      area: "area",
      house: "house",
      lineChartData,
      name: "관심 지역 클릭해주세요.",
      isNone: true
    };
  },
  computed: {
    ...mapGetters([
      "selectedArea",
      "userList",
      "bookmarkedHouseCountOfUser",
      "bookmarkedAreaCountOfUser",
      "followingCount",
      "followerCount",
      "currentUser"
    ])
  },
  components: {
    IconCard,
    "line-chart": LineChart
  },
  methods: {
    ...mapActions([
      "getUsersAction",
      "getFriendCountAction",
      "getFollowerListAction",
      "getFollowingListAction"
    ])
  },
  created() {
    this.isNone = true;
    this.name = "관심지역 평균 거래량";
    this.getUsersAction(this.currentUser.userSeq);
    this.getFriendCountAction(this.currentUser.userSeq);
    this.getFollowerListAction(this.currentUser.userSeq);
    this.getFollowingListAction(this.currentUser.userSeq);
  },
  watch: {
    selectedArea() {
      if (this.name === "") {
        this.name = "관심 지역을 클릭해주세요";
      } else {
        this.name = `${this.selectedArea} 거래량`;
        this.isNone = false;
      }
    },
    lineChartData() {
      console.log(this.lineChartData);
    }
  }
};
</script>

<style>
.dashboard__wrapper {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
}

.dashboard__main {
  width: 63%;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.follow__wrap {
  width: 100%;
  flex-basis: 30%;
  display: flex;
  justify-content: space-between;
}
.follow__wrap__item {
  flex-basis: 24%;
  cursor: pointer;
}

.chart__wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.chart__header {
  width: 100%;
  flex-basis: 10%;
  font-size: 24px;
  font-weight: 900;
  margin-bottom: 20px;
}

.chart__main {
  width: 100%;
  flex-basis: 90%;
}

.dashboard__aside {
  width: 35%;
  padding: 10px;
}
.aside__list__wrap {
  width: 100%;
  height: 100%;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  -webkit-box-shadow: 4px 6px 10px 2px #6f7a80;
  box-shadow: 4px 6px 10px 2px #6f7a80;
}

.aside__list__header {
  font-size: 18px;
  height: 50px;
  border-bottom: 2px solid #dfe6e9;
  display: flex;
  justify-content: center;
  align-items: center;
}
.aside__list__scroll {
  height: 580px;
  padding: 10px 20px;
  overflow-y: scroll;
}

/* 아래의 모든 코드는 영역::코드로 사용 */
.aside__list__scroll::-webkit-scrollbar {
  width: 10px; /* 스크롤바의 너비 */
}
.aside__list__scroll::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #3188ff; /* 스크롤바의 색상 */

  border-radius: 10px;
}

.aside__list__scroll::-webkit-scrollbar-track {
  background: rgba(33, 122, 244, 0.1); /*스크롤바 뒷 배경 색상*/
}
</style>

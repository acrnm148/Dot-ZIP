<template>
  <div class="aside__list__wrap">
    <!-- aside__list__header : 팔로잉한 회원을 보는 버튼 & 팔로잉할 수 있는 유저 리스트를 보는 버튼 -->
    <div class="aside__list__header">
      FOLLOWING
    </div>
    <!-- aside__list__scroll : 현재 팔로잉한 회원을 리스트로 보여줌 -->
    <!-- aside__list__scroll : 팔로잉 할 수 있는 전체 회원 유저를 리스트로 보여줌 -->
    <div class="aside__list__scroll">
      <list-with-user-item
        v-for="(item, index) in followingList"
        :item="item"
        :key="index"
        :listType="following"
      >
      </list-with-user-item>
    </div>
  </div>
</template>
<script>
import ListWithUserItem from "@/components/Listing/ListWithUserItem";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      following: "following"
    };
  },
  components: {
    ListWithUserItem
  },
  computed: {
    ...mapGetters(["followingList"])
  }
};
</script>
<style></style>

<template>
  <div class="d-flex flex-row mb-3 pb-3 border-bottom">
    <img
      :src="item.userProfileImageUrl"
      :alt="item.userName"
      class="img-thumbnail border-0 rounded-circle list-thumbnail align-self-center xsmall"
    />
    <div class="user__item__wrapper">
      <div class="pl-3 pr-2 user__item__right">
        <div class="user__item__username">{{ item.userName }}</div>
        <div v-if="listType === 'user'" @click="onClickFollowBtn(item.userSeq)">
          <b-badge variant="primary" class="following__toggle__btn"
            >FOLLOWING</b-badge
          >
        </div>
        <div
          v-else-if="listType === 'following'"
          @click="onClickUnFollowBtn(item.userSeq)"
        >
          <b-badge variant="danger" class="following__toggle__btn"
            >UNFOLLOWING</b-badge
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: ["item", "listType"],
  computed: {
    ...mapGetters(["currentUser"])
  },
  methods: {
    ...mapActions(["followingUserAction", "unFollowingUserAction"]),
    onClickFollowBtn(toId) {
      this.followingUserAction({ toId, userSeq: this.currentUser.userSeq });
    },
    onClickUnFollowBtn(toId) {
      this.unFollowingUserAction({ toId, userSeq: this.currentUser.userSeq });
    }
  }
};
</script>

<style scoped>
.user__item__wrapper {
  width: 100%;
  display: flex;
  align-items: center;
}
.user__item__right {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.user__item__username {
  font-size: 18px;
}
.following__toggle__btn {
  font-size: 13px;
  cursor: pointer;
}
</style>

<template>
  <div class="icon-row-item">
    <b-card @click="onClickItem" class="home-icon-wrap mb-4 text-center">
      <i :class="icon" />
      <p class="card-text font-weight-semibold mb-0">{{ title }}</p>
      <p class="lead text-center">{{ value }}</p>
    </b-card>
  </div>
</template>
<script>
export default {
  props: {
    title: { type: String, default: "icon-card-title" },
    icon: { type: String, default: "iconsminds-clock" },
    value: { type: Number, default: 0 },
    code: { type: String }
  },
  methods: {
    onClickItem() {
      console.log();
      if (this.code === "follower") {
        console.log(this.code);
        this.$router
          .push({
            name: "FollowList"
          })
          .catch(() => {});
      } else if (this.code === "following") {
        this.$router
          .push({
            name: "FollowingList"
          })
          .catch(() => {});
      } else if (this.code === "area") {
        this.$router
          .push({
            name: "FavsArea"
          })
          .catch(() => {});
      } else if (this.code === "house") {
        this.$router
          .push({
            name: "FavsHouse"
          })
          .catch(() => {});
      }
    }
  }
};
</script>

<style scope>
.home-icon-wrap {
  font-size: 24px;
}
</style>

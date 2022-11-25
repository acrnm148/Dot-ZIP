<template>
  <nav class="navbar fixed-top">
    <div class="d-flex align-items-center navbar-left">
      <a
        href="#"
        class="menu-button d-none d-md-block"
        @click.prevent.stop="
          changeSideMenuStatus({
            step: menuClickCount + 1,
            classNames: menuType,
            selectedMenuHasSubItems
          })
        "
      >
        <menu-icon />
      </a>
    </div>
    <router-link class="navbar-logo" :to="adminRoot">
      <span class="logo d-none d-xs-block"></span>
    </router-link>

    <div class="navbar-right">
      <div class="d-none d-md-inline-block align-middle mr-3">
        <switches
          id="tool-mode-switch"
          v-model="isDarkActive"
          theme="custom"
          class="vue-switcher-small"
          color="primary"
        />
      </div>
      <div class="header-icons d-inline-block align-middle">
        <div class="position-relative d-none d-sm-inline-block">
          <div class="btn-group">
            <b-button
              variant="empty"
              class="header-icon btn-sm"
              @click="toggleFullScreen"
            >
              <i
                :class="{
                  'd-inline-block': true,
                  'simple-icon-size-actual': fullScreen,
                  'simple-icon-size-fullscreen': !fullScreen
                }"
              />
            </b-button>
          </div>
        </div>
      </div>
      <div class="user d-inline-block">
        <b-dropdown
          class="dropdown-menu-right"
          right
          variant="empty"
          toggle-class="p-0"
          menu-class="mt-3"
          no-caret
        >
          <!-- 로그인 시 현재 컴포넌트의 data인 currentUser을 변경해준다. -->
          <template slot="button-content">
            <span class="name mr-1">{{ currentUser.userName }}</span>
            <span>
              <img
                :alt="currentUser.userName"
                :src="currentUser.userProfileImageUrl"
              />
            </span>
          </template>
          <b-dropdown-item @click="onClickLogout">Sign out</b-dropdown-item>
        </b-dropdown>
      </div>
    </div>
  </nav>
</template>

<script>
import Switches from "vue-switches";

import { mapGetters, mapMutations, mapActions } from "vuex";
import { MenuIcon } from "../../components/Svg";
import { menuHiddenBreakpoint, adminRoot } from "../../constants/config";
import { getThemeColor, setThemeColor } from "../../utils";
export default {
  components: {
    "menu-icon": MenuIcon,
    switches: Switches
  },
  data() {
    return {
      fullScreen: false,
      menuHiddenBreakpoint,
      isDarkActive: false,
      adminRoot
    };
  },
  methods: {
    ...mapMutations(["changeSideMenuStatus"]),
    ...mapActions(["userLogout"]),
    onClickMoveMyPage() {
      this.$router.push("/app/my");
    },
    onClickLogout() {
      console.log(this.currentUser);
      this.userLogout({ userEmail: this.currentUser.userEmail });
      this.$router.push("/user/login");
    },

    toggleFullScreen() {
      const isInFullScreen = this.isInFullScreen();

      var docElm = document.documentElement;
      if (!isInFullScreen) {
        if (docElm.requestFullscreen) {
          docElm.requestFullscreen();
        } else if (docElm.mozRequestFullScreen) {
          docElm.mozRequestFullScreen();
        } else if (docElm.webkitRequestFullScreen) {
          docElm.webkitRequestFullScreen();
        } else if (docElm.msRequestFullscreen) {
          docElm.msRequestFullscreen();
        }
      } else {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitExitFullscreen) {
          document.webkitExitFullscreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      }
      this.fullScreen = !isInFullScreen;
    },
    isInFullScreen() {
      return (
        (document.fullscreenElement && document.fullscreenElement !== null) ||
        (document.webkitFullscreenElement &&
          document.webkitFullscreenElement !== null) ||
        (document.mozFullScreenElement &&
          document.mozFullScreenElement !== null) ||
        (document.msFullscreenElement && document.msFullscreenElement !== null)
      );
    }
  },
  computed: {
    ...mapGetters({
      currentUser: "currentUser",
      menuType: "getMenuType",
      menuClickCount: "getMenuClickCount",
      selectedMenuHasSubItems: "getSelectedMenuHasSubItems"
    })
  },
  created() {
    const color = getThemeColor();
    this.isDarkActive = color.indexOf("dark") > -1;
  },
  watch: {
    isDarkActive(val) {
      let color = getThemeColor();
      let isChange = false;
      if (val && color.indexOf("light") > -1) {
        isChange = true;
        color = color.replace("light", "dark");
      } else if (!val && color.indexOf("dark") > -1) {
        isChange = true;
        color = color.replace("dark", "light");
      }
      if (isChange) {
        setThemeColor(color);
        setTimeout(() => {
          window.location.reload();
        }, 500);
      }
    }
  }
};
</script>

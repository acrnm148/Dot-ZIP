import Vue from "vue";
import VueRouter from "vue-router";
import AuthGuard from "@/utils/auth.guard";
import { adminRoot } from "@/constants/config";
import store from "../store";
// import { UserRole } from "../utils/auth.roles";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: () => import(/* webpackChunkName: "home" */ "@/views/home")
  },
  {
    path: adminRoot,
    component: () => import(/* webpackChunkName: "app" */ "@/views/app"),
    beforeEnter: (to, from, next) => {
      if (!store.state.user.isLogin) {
        next("/");
      } else {
        return next();
      }
    },
    redirect: `${adminRoot}/home`,
    // meta: { loginRequired: true },
    children: [
      {
        path: "home",
        component: () => import("@/views/app/home"),
        redirect: `${adminRoot}/home/dashboard`,
        children: [
          {
            name: "DashBoard",
            path: "dashboard",
            redirect: `${adminRoot}/home/dashboard/users`,
            component: () => import("@/views/app/home/DashBoard"),
            children: [
              {
                name: "FollowList",
                path: "follower",
                component: () => import("@/components/Follow/FollowList")
              },
              {
                name: "FollowingList",
                path: "following",
                component: () => import("@/components/Follow/FollowingList")
              },
              {
                name: "UserList",
                path: "users",
                component: () => import("@/components/Follow/UserList")
              }
            ]
          }
        ]
      },
      {
        path: "search",
        component: () => import("@/views/app/search"),
        redirect: `${adminRoot}/search/area`,
        children: [
          {
            name: "SearchDong",
            path: "area",
            component: () => import("@/views/app/search/Area")
            // meta: { roles: [UserRole@Admin, UserRole@Editor] },
          },
          {
            path: "name",
            component: () => import("@/views/app/search/Name")
            // meta: { roles: [UserRole@Admin, UserRole@Editor] },
          }
        ]
      },
      {
        path: "favs",
        component: () => import("@/views/app/favs"),
        redirect: `${adminRoot}/favs/fArea`,
        children: [
          {
            name: "FavsArea",
            path: "fArea",
            component: () => import("@/views/app/favs/Area")
          },
          {
            name: "FavsHouse",
            path: "fHouse",
            component: () => import("@/views/app/favs/House")
          }
        ]
      },

      {
        path: "support",
        component: () => import("@/views/app/support"),
        redirect: `${adminRoot}/support/notice`,
        children: [
          {
            path: "notice",
            component: () => import("@/views/app/support/Notice")
          },
          {
            path: "QnA",
            component: () => import("@/views/app/support/QnA")
          },
          {
            path: "contact",
            component: () => import("@/views/app/support/Contact")
          }
        ]
      }
    ]
  },
  {
    path: "/error",
    component: () => import("@/views/Error")
  },
  {
    path: "/user",
    component: () => import("@/views/user"),
    redirect: "/user/login",
    children: [
      {
        path: "login",
        component: () => import("@/views/user/Login")
      },
      {
        path: "register",
        component: () => import("@/views/user/Register")
      },
      {
        path: "forgot-password",
        component: () => import("@/views/user/ForgotPassword")
      },
      {
        path: "reset-password",
        component: () => import("@/views/user/ResetPassword")
      }
    ]
  },
  {
    path: "*",
    component: () => import("@/views/Error")
  }
];

const router = new VueRouter({
  linkActiveClass: "active",
  routes,
  mode: "history"
});
router.beforeEach(AuthGuard);
export default router;

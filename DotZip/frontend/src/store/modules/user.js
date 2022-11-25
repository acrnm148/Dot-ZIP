import jwtDecode from "jwt-decode";
import {
  signinAPI,
  signupAPI,
  findByEmail,
  tokenRegeneration,
  logout,
  getUsers
} from "@/apis/user.js";
import { adminRoot } from "@/constants/config";

export default {
  state: {
    // currentUser: isAuthGuardActive ? getCurrentUser() : currentUser,
    loginError: null,
    processing: false,
    isValidToken: false,
    userList: [],
    // forgotMailSuccess: null,
    // resetPasswordSuccess: null
    isLogin: false,
    isKakao: false,
    currentUser: {
      userSeq: 0,
      userName: "",
      userPassword: "",
      userEmail: "",
      userProfileImageUrl: "",
      userRegisterDate: "",
      userClsf: ""
    }
  },
  getters: {
    userList: state => state.userList,
    isLogin: state => state.isLogin,
    currentUser: state => state.currentUser,
    processing: state => state.processing,
    loginError: state => state.loginError
  },
  mutations: {
    SET_USER_LIST(state, payload) {
      state.userList = [...payload];
    },
    SET_LOGIN(state) {
      state.isLogin = true;
      state.processing = false;
      state.loginError = null;
    },
    SET_IS_LOGIN(state, payload) {
      state.isLogin = payload;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_PROCESSING(state, payload) {
      state.processing = payload;
    },
    SET_LOGOUT(state) {
      state.currentUser.userSeq = 0;
      state.isLogin = false;
      state.isKakao = false;
      state.currentUser.userPassword = "";
      state.currentUser.userName = "";
      state.currentUser.userEmail = "";
      state.currentUser.userClsf = "";
      state.currentUser.userProfileImageUrl = "";
      state.currentUser.userRegisterDate = "";
    },
    SET_USER_INFO(state, payload) {
      state.currentUser = { ...payload };
      state.isLogin = true;
    },
    SET_KAKAO(state) {
      state.info.isKakao = true;
      state.info.isLogin = true;
    },
    SET_ERROR(state, payload) {
      state.loginError = payload;
      state.currentUser = null;
      state.processing = false;
    },
    // setForgotMailSuccess(state) {
    //   state.loginError = null;
    //   state.currentUser = null;
    //   state.processing = false;
    //   state.forgotMailSuccess = true;
    // },
    // setResetPasswordSuccess(state) {
    //   state.loginError = null;
    //   state.currentUser = null;
    //   state.processing = false;
    //   state.resetPasswordSuccess = true;
    // },
    SET_CLEAR_ERROR(state) {
      state.loginError = null;
    }
  },
  actions: {
    async getUsersAction({ commit }, userSeq) {
      await getUsers(
        userSeq,
        ({ data }) => {
          commit("SET_USER_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    async login({ commit }, { email, password }) {
      commit("SET_CLEAR_ERROR");
      commit("SET_PROCESSING", true);
      await signinAPI(
        {
          userEmail: email,
          userPassword: password
        },
        ({ data, status }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            commit("SET_LOGIN");
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            if (status === 202) {
              commit("SET_ERROR", "아이디 또는 비밀번호가 틀렸습니다.");
            } else {
              commit("SET_ERROR", "서버에 문제가 발생했습니다.");
            }
            commit("SET_IS_VALID_TOKEN", false);
            setTimeout(() => {
              commit("SET_CLEAR_ERROR");
            }, 3000);
          }
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findByEmail(
        decodeToken.userEmail,
        ({ data }) => {
          if (data.message === "success") {
            console.log(data);
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async error => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("access-token")
      );
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async error => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userEmail,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_LOGOUT");
                commit("SET_IS_VALID_TOKEN", false);
                router.push(`${adminRoot}/user/login`);
              },
              error => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, { userEmail }) {
      await logout(
        userEmail,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_LOGOUT");
            commit("SET_IS_VALID_TOKEN", false);
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("refresh-token");
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        error => {
          console.log(error);
        }
      );
    }
  }
  // logout({ commit }) {
  //   commit('SET_LOGOUT');
  // },
  // forgotPassword({ commit }, payload) {
  //   commit("clearError");
  //   commit("setProcessing", true);
  // firebase
  //   .auth()
  //   .sendPasswordResetEmail(payload.email)
  //   .then(
  //     user => {
  //       commit('clearError')
  //       commit('setForgotMailSuccess')
  //     },
  //     err => {
  //       commit('setError', err.message)
  //       setTimeout(() => {
  //         commit('clearError')
  //       }, 3000)
  //     }
  //   )
  // },
  // resetPassword({ commit }, payload) {
  // commit("clearError");
  // commit("setProcessing", true);
  // firebase
  //   .auth()
  //   .confirmPasswordReset(payload.resetPasswordCode, payloa. d.newPassword)
  //   .then(
  //     user => {
  //       commit('clearError')
  //       commit('setResetPasswordSuccess')
  //     },
  //     err => {
  //       commit('setError', err.message)
  //       setTimeout(() => {
  //         commit('clearError')
  //       }, 3000)
  //     }
  //   )
  // },
  // signOut({ commit }) {
  // firebase
  //   .auth()
  //   .signOut()
  //   .then(() => {
  //     setCurrentUser(null);
  //     commit('setLogout')
  //   }, _error => { })
  // }
};

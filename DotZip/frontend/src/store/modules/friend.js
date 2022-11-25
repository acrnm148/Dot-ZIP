import {
  followingUser,
  unFollowingUser,
  getFriendCount,
  getFollowerList,
  getFollowingList
} from "@/apis/friend.js";

export default {
  state: {
    followingCount: 0,
    followerCount: 0,
    followingList: [],
    followerList: []
  },
  getters: {
    followingCount: state => state.followingCount,
    followerCount: state => state.followerCount,
    followingList: state => state.followingList,
    followerList: state => state.followerList
  },
  mutations: {
    SET_FOLLOWING_COUNT(state, payload) {
      state.followingCount = payload;
    },
    SET_FOLLOWER_COUNT(state, payload) {
      state.followerCount = payload;
    },
    SET_FOLLOWING_LIST(state, payload) {
      state.followingList = [...payload];
    },
    SET_FOLLOWER_LIST(state, payload) {
      state.followerList = [...payload];
    }
  },
  actions: {
    followingUserAction: ({ commit, dispatch }, payload) => {
      followingUser(
        payload,
        ({ data }) => {
          // dispatch를 이용해 팔로잉 리스트 다시 뽑고, 친구 수 다시 계산
          console.log(data);
          dispatch("getFriendCountAction", data.dto.userSeq);
          dispatch("getFollowingListAction", data.dto.userSeq);
          dispatch("getUsersAction", data.dto.userSeq);
        },
        error => {
          console.log(error);
        }
      );
    },
    unFollowingUserAction: ({ commit, dispatch }, payload) => {
      unFollowingUser(
        payload,
        ({ data }) => {
          // dispatch를 이용해 팔로잉 리스트 다시 뽑고, 친구 수 다시 계산
          dispatch("getFriendCountAction", data.dto.userSeq);
          dispatch("getFollowingListAction", data.dto.userSeq);
          dispatch("getUsersAction", data.dto.userSeq);
        },
        error => {
          console.log(error);
        }
      );
    },
    getFriendCountAction: ({ commit }, userSeq) => {
      getFriendCount(
        userSeq,
        ({ data }) => {
          commit("SET_FOLLOWER_COUNT", data.followerCount);
          commit("SET_FOLLOWING_COUNT", data.followingCount);
        },
        error => {
          console.log(error);
        }
      );
    },

    getFollowerListAction: ({ commit }, userSeq) => {
      getFollowerList(
        userSeq,
        ({ data: { list } }) => {
          console.log(list);
          commit("SET_FOLLOWER_LIST", list);
        },
        error => {
          console.log(error);
        }
      );
    },
    getFollowingListAction: ({ commit }, userSeq) => {
      getFollowingList(
        userSeq,
        ({ data: { list } }) => {
          console.log(list);
          commit("SET_FOLLOWING_LIST", list);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};

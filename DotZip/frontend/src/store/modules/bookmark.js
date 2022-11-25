import {
  addBookMarkHouse,
  deleteBookMarkHouse,
  getBookMarkHouseList,
  addBookMarkArea,
  deleteBookMarkArea,
  getBookMarkAreaList,
  getBookMarkArea,
  getBookMarkAreaCount,
  getBookMarkHouseCount
} from "@/apis/bookmark.js";
export default {
  state: {
    bookmarkAreaList: [],
    bookmarkHouseList: [],
    selectedArea: "",
    isBookmarkedArea: false,
    bookmarkedAreaCountOfUser: 0,
    bookmarkedHouseCountOfUser: 0
  },
  mutations: {
    SET_BOOKMARK_AREA_LIST(state, payload) {
      state.bookmarkAreaList = [...payload.arealist];
    },
    SET_AREA_COUNT(state, payload) {
      state.bookmarkedAreaCountOfUser = payload;
    },
    SET_HOUSE_COUNT(state, payload) {
      state.bookmarkedHouseCountOfUser = payload;
    },
    SET_SELECTED_AREA(state, payload) {
      console.log(payload);
      state.selectedArea = payload;
    },
    SET_IS_BOOKMARK_AREA(state, payload) {
      state.isBookmarkedArea = payload;
    },
    SET_BOOKMARK_HOUSE_LIST(state, payload) {
      state.bookmarkHouseList = [...payload.houselist];
    }
  },
  getters: {
    bookmarkHouseList: state => state.bookmarkHouseList,
    bookmarkAreaList: state => state.bookmarkAreaList,
    isBookmarkedArea: state => state.isBookmarkedArea,
    selectedArea: state => state.selectedArea,
    bookmarkedAreaCountOfUser: state => state.bookmarkedAreaCountOfUser,
    bookmarkedHouseCountOfUser: state => state.bookmarkedHouseCountOfUser
  },
  actions: {
    getBookMarkAreaCountAction: ({ commit }, payload) => {
      getBookMarkAreaCount(
        payload,
        ({ data }) => {
          commit("SET_AREA_COUNT", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getBookMarkHouseCountAction: ({ commit }, payload) => {
      getBookMarkHouseCount(
        payload,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_COUNT", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    addBookMarkHouseAction: ({ commit }, payload) => {
      addBookMarkHouse(
        payload,
        ({ data }) => {
          console.log(data);
        },
        error => {
          console.log(error);
        }
      );
    },

    deleteBookMarkHouseAction: ({ commit, dispatch }, payload) => {
      console.log(payload);
      deleteBookMarkHouse(
        payload,
        ({ data }) => {
          console.log(data);
          dispatch("getBookMarkHouseListAction", payload.userSeq);
        },
        error => {
          console.log(error);
        }
      );
    },

    getBookMarkHouseListAction: ({ commit }, userSeq) => {
      getBookMarkHouseList(
        userSeq,
        ({ data }) => {
          console.log(data);
          commit("SET_BOOKMARK_HOUSE_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },

    addBookMarkAreaAction: ({ commit }, payload) => {
      addBookMarkArea(
        payload,
        ({ data }) => {
          console.log(data);
        },
        error => {
          console.log(error);
        }
      );
    },

    deleteBookMarkAreaAction: ({ commit, dispatch }, payload) => {
      console.log(payload);
      deleteBookMarkArea(
        payload,
        ({ data }) => {
          console.log(data);
          dispatch("getBookMarkAreaListAction", {
            userSeq: payload.userSeq,
            limit: 10,
            offset: 0
          });
        },
        error => {
          console.log(error);
        }
      );
    },
    getBookMarkAreaListAction: ({ commit }, { userSeq, limit, offset }) => {
      getBookMarkAreaList(
        {
          userSeq,
          limit,
          offset
        },
        ({ data }) => {
          console.log(data);
          commit("SET_BOOKMARK_AREA_LIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getBookMarkAreaAction: ({ commit }, payload) => {
      console.log(`action : ${payload}`);
      getBookMarkArea(
        payload,
        ({ data }) => {
          console.log(data);
          commit("SET_IS_BOOKMARK_AREA", data);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};

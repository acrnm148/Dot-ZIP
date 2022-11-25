import {
  getSido,
  getGuGun,
  getDong,
  getHouseByDong,
  getHouseByName,
  getHouseDetailByNo
} from "@/apis/house.js";

export default {
  state: {
    sidoList: [{ value: null, label: "선택하세요" }],
    gugunList: [{ value: null, label: "선택하세요" }],
    dongList: [{ value: null, label: "선택하세요" }],
    houseList: [],
    isOpen: false,
    house: {
      aptName: "",
      area: "",
      buildYear: "",
      cityName: "",
      code: "",
      dealAmount: "",
      dealDay: "",
      dealMonth: "",
      dealYear: "",
      dong: "",
      floor: "",
      gugunName: "",
      houseNo: 0,
      jibun: "",
      lat: "",
      lng: "",
      no: 0
    },
    chartData: []
  },
  mutations: {
    SET_SIDOLIST(state, payload) {
      console.log(payload);
      payload.sidoList.forEach(sido => {
        state.sidoList.push({ value: sido.code, label: sido.name });
      });
    },
    SET_GUGUNLIST(state, payload) {
      console.log(payload);
      payload.gugunList.forEach(gugun => {
        state.gugunList.push({ value: gugun.code, label: gugun.name });
      });
    },
    SET_DONGLIST(state, payload) {
      payload.dongList.forEach(dong => {
        state.dongList.push({ value: dong.code, label: dong.name });
      });
    },
    SET_TOGGLE_LIST(state, payload) {
      state.isOpen = payload;
    },
    SET_HOUSELIST(state, houseList) {
      state.houseList = [...houseList];
    },
    SET_HOUSE_DETAIL(state, house) {
      state.house = { ...house };
    },
    CLEAR_SIDO_LIST(state) {
      state.sidoList = [{ value: null, label: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.gugunList = [{ value: null, label: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongList = [{ value: null, label: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houseList = [];
      state.house = null;
    },
    CLEAR_TOGGLE_LIST(state) {
      state.isOpen = false;
    }
  },
  getters: {
    isOpen: state => state.isOpen,
    sidoList: state => state.sidoList,
    gugunList: state => state.gugunList,
    dongList: state => state.dongList,
    houseList: state => state.houseList,
    house: state => state.house
  },
  actions: {
    getSidoList: ({ commit }) => {
      getSido(
        ({ data }) => {
          commit("SET_SIDOLIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getGugunList: ({ commit }, sidoName) => {
      getGuGun(
        sidoName,
        ({ data }) => {
          commit("SET_GUGUNLIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getDongList: ({ commit }, gugunName) => {
      getDong(
        gugunName,
        ({ data }) => {
          commit("SET_DONGLIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getHouseListByDong: async ({ commit }, { dong, userSeq }) => {
      const params = {
        no: userSeq,
        dong: dong,
        limit: 10,
        offset: 0
      };
      await getHouseByDong(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSELIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getHouseListByName: async ({ commit }, { searchWord, userSeq }) => {
      const params = {
        no: userSeq,
        searchWord: searchWord,
        limit: 10,
        offset: 0
      };
      await getHouseByName(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSELIST", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    getHouse: ({ commit }, no) => {
      getHouseDetailByNo(
        no,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_DETAIL", data);
        },
        error => {
          console.log(error);
        }
      );
    },
    toggleList: ({ commit }, flag) => {
      commit("SET_TOGGLE_LIST", flag);
    }
  }
};

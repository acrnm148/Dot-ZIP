import { getContent } from "@/apis/QnA.js";

export default {
  state: {
    content: ""
  },
  getters: {
    curContent: state => state.content
  },
  mutations: {
    SET_CONTENT(state, payload) {
      state.content = payload;
    }
  },
  actions: {
    getContentAction: ({ commit }, qnaNum) => {
      console.log("스토어 액션");
      getContent(
        qnaNum,
        ({ data }) => {
          commit("SET_CONTENT", data.dto.content);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
};

import api from "./axios.js";

async function getContent(qnaNum, success, fail) {
  console.log("QNANUM" + qnaNum);
  await api
    .get(`/qna/ans/${qnaNum}`)
    .then(success)
    .catch(fail);
}

export { getContent };

import api from "./axios.js";

async function addBookMarkHouse({ userSeq, houseNo }, success, fail) {
  await api
    .post("/interest/house/add", null, {
      params: {
        userSeq,
        houseNo
      }
    })
    .then(success)
    .catch(fail);
}

async function deleteBookMarkHouse({ userSeq, houseNo }, success, fail) {
  await api
    .post("/interest/house/delete", null, {
      params: {
        userSeq,
        houseNo
      }
    })
    .then(success)
    .catch(fail);
}

async function getBookMarkHouseList(userSeq, success, fail) {
  await api
    .get(`/interest/house/${userSeq}`)
    .then(success)
    .catch(fail);
}

async function addBookMarkArea({ userSeq, dongCode }, success, fail) {
  await api
    .post("/interest/area/add", { userSeq, dongCode })
    .then(success)
    .catch(fail);
}

async function deleteBookMarkArea({ userSeq, dongCode }, success, fail) {
  await api
    .post("/interest/area/delete", { userSeq, dongCode })
    .then(success)
    .catch(fail);
}

async function getBookMarkAreaCount(userSeq, success, fail) {
  await api
    .get(`/interest/area/count/${userSeq}`)
    .then(success)
    .catch(fail);
}

async function getBookMarkHouseCount(userSeq, success, fail) {
  await api
    .get(`/interest/house/count/${userSeq}`)
    .then(success)
    .catch(fail);
}

async function getBookMarkAreaList({ userSeq, limit, offset }, success, fail) {
  await api
    .get(`/interest/area`, {
      params: {
        userSeq,
        limit,
        offset
      }
    })
    .then(success)
    .catch(fail);
}

async function getBookMarkArea({ userSeq, dongCode }, success, fail) {
  console.log(userSeq, dongCode);
  await api
    .post("/interest/area/bookmark", null, {
      params: {
        userSeq,
        dongCode
      }
    })
    .then(success)
    .catch(fail);
}

export {
  addBookMarkHouse,
  deleteBookMarkHouse,
  getBookMarkHouseList,
  addBookMarkArea,
  deleteBookMarkArea,
  getBookMarkAreaList,
  getBookMarkArea,
  getBookMarkHouseCount,
  getBookMarkAreaCount
};

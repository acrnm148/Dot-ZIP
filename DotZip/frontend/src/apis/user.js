import api from "./axios.js";

async function signinAPI(user, success, fail) {
  await api
    .post("/users/login", user)
    .then(success)
    .catch(fail);
}

async function getUsers(userSeq, success, fail) {
  await api
    .get(`/users/all/${userSeq}`)
    .then(success)
    .catch(fail);
}

async function signupAPI(user, success, fail) {
  await api
    .post("/users", user)
    .then(success)
    .catch(fail);
}

async function findByEmail(userEmail, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .get(`/users/info/${userEmail}`)
    .then(success)
    .catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem(
    "refresh-token"
  ); //axios header에 refresh-token 셋팅
  await api
    .post(`/users/refresh`, user)
    .then(success)
    .catch(fail);
}

async function logout(userEmail, success, fail) {
  await api
    .get(`/users/logout/${userEmail}`)
    .then(success)
    .catch(fail);
}

export {
  signinAPI,
  signupAPI,
  findByEmail,
  tokenRegeneration,
  logout,
  getUsers
};

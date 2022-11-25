import api from "./axios.js";

// 팔로잉 하기
async function followingUser({ toId, userSeq }, success, fail) {
  await api
    .post(`/friend`, { toId, userSeq })
    .then(success)
    .catch(fail);
}

// 언팔
async function unFollowingUser({ toId, userSeq }, success, fail) {
  await api
    .post(`/friend/delete`, { toId, userSeq })
    .then(success)
    .catch(fail);
}

// 팔로잉,팔로워 수 얻기
async function getFriendCount(userSeq, success, fail) {
  await api
    .get(`/friend/count/${userSeq}`)
    .then(success)
    .catch(fail);
}

// 팔로워 리스트
async function getFollowerList(userSeq, success, fail) {
  await api
    .get(`/friend/follower/${userSeq}`)
    .then(success)
    .catch(fail);
}

async function getFollowingList(userSeq, success, fail) {
  await api
    .get(`/friend/following/${userSeq}`)
    .then(success)
    .catch(fail);
}

export {
  followingUser,
  unFollowingUser,
  getFriendCount,
  getFollowerList,
  getFollowingList
};

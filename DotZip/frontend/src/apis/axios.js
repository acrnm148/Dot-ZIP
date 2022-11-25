import axios from "axios";

console.log(process.env.VUE_APP_API_BASE_URL);
// axios 객체 생성
export default axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

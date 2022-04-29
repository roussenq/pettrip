import axios from "axios";

const api = axios.create({
  baseURL: "https://pettrip-tcs.herokuapp.com",
});

export default api;

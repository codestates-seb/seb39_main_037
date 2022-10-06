import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";

console.log(process.env.REACT_APP_API_URL);
// instance 생성
const instance = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  headers: { "Access-Control-Allow-Origin": "*" },
  withCredentials: true,
  timeout: 100000,
});

// header 추가
// export const setAuthTokenHeader = (token: string) => {
//   instance.defaults.headers.common.Authorization = token;
// };
// request interceptor header 추가
const interceptorRequestFulfilled = (config: AxiosRequestConfig) => {
  // then 또는 catch로 처리되기 전에 요청과 응답을 가로챔
  if (localStorage.getItem("user-token")) {
    const userToken = JSON.parse(localStorage.getItem("user-token") || "");
    return {
      ...config,
      headers: {
        "Access-Control-Allow-Origin": "*",
        Authorization: `${userToken}`,
      },
    };
  }
  return {
    ...config,
    headers: {
      "Access-Control-Allow-Origin": "*",
    },
  };
};

instance.interceptors.request.use(interceptorRequestFulfilled);

// response interceptor
const responseInterceptorFulfilled = (res: AxiosResponse) => {
  console.log(res);
  if (res.status >= 200 && res.status < 300) {
    if (res.config.method === "post") {
      // post일 때는 전체 넘김
      return res;
    }
    return res.data;
  }

  return Promise.reject(...res.data);
};

const responseInterceptorRejected = (error: AxiosError | any) => {
  const errorMsg = error.response?.data?.message ?? "에러입니다";
  console.log(error);
  console.log(error.response);
  alert(errorMsg);
};

instance.interceptors.response.use(
  responseInterceptorFulfilled,
  responseInterceptorRejected,
);

export const get = <T>(...args: Parameters<typeof instance.get>) => {
  return instance.get<T, T>(...args);
};

export const post = <T>(...args: Parameters<typeof instance.post>) => {
  return instance.post<T, T>(...args);
};

export const patch = <T>(...args: Parameters<typeof instance.patch>) => {
  return instance.patch<T, T>(...args);
};

export const del = <T>(...args: Parameters<typeof instance.delete>) => {
  return instance.delete<T, T>(...args);
};

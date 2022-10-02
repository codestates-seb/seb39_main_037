import { get } from "Utils/api";

export const useLocation = () => {
  const getState = async () => {
    const res = await get(`/location/state`).then((r: any) => {
      console.log(r);
      return r;
    });
    return res;
  };

  const getCity = async (stateId: number) => {
    const res = await get(`/location/city/${stateId}`).then((r: any) => {
      console.log(r);
      return r;
    });
    return res;
  };

  return {
    getState,
    getCity,
  };
};

import { atom } from "recoil"; // recoil 상태관리

export const locationState = atom({
  key: "locationState",
  default: {
    locationId: 0,
    stateName: "",
    cityName: "",
  },
});

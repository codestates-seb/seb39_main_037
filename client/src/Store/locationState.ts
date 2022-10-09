import { atom } from "recoil"; // recoil 상태관리
import { recoilPersist } from "recoil-persist"; // 로컬스토리지에 저장됨

const { persistAtom } = recoilPersist();
export const locationState = atom({
  key: "locationState",
  default: {
    locationId: 0,
    stateName: "",
    cityName: "",
  },
  effects_UNSTABLE: [persistAtom],
});

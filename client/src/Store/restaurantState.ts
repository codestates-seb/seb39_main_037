import { atom } from "recoil"; // recoil 상태관리
import { recoilPersist } from "recoil-persist"; // 로컬스토리지에 저장됨
import { IRestaurant } from "Types";

const { persistAtom } = recoilPersist();
export const restaurantState = atom<IRestaurant>({
  key: "restaurantState",
  default: {
    restaurantId: 0,
    restaurantName: "",
    category: "",
    description: "",
    restaurantPhone: "",
    address: "",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 0,
    mapy: 0,
    locationId: 0,
    foodTypeName: "",
  },
  effects_UNSTABLE: [persistAtom],
});

import { atom } from "recoil"; // recoil 상태관리

export const restaurantState = atom({
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
});

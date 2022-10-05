import { get } from "Utils/api";

export const useRestaurant = () => {
  // 지역으로 식당찾기
  const getRestaurantByLocation = async ({ locationId, page }: any) => {
    const res = await get(`restaurant/location/${locationId}/${page}`).then(
      (r: any) => {
        console.log(r);
        return r;
      },
    );
    return res;
  };
  // 아이디로 식당 정보 찾기
  const getRestaruantById = async ({ restaurantId }: any) => {
    const res = await get(`/restaurant/${restaurantId}`).then((r: any) => {
      console.log(r);
      return r;
    });
    return res;
  };
  return { getRestaurantByLocation, getRestaruantById };
};

import { get } from "Utils/api";

export const useRestaurant = () => {
  // 지역으로 식당찾기
  const getRestaurantByLocation = async ({ locationId, page }: any) => {
    const res = await get(`restaurant/location/${locationId}/${page}`).then(
      (r: any) => {
        return r;
      },
    );
    return res;
  };
  // 아이디로 식당 정보 찾기
  const getRestaruantById = async ({ restaurantId }: any) => {
    const res = await get(`/restaurant/${restaurantId}`).then((r: any) => {
      return r;
    });
    return res;
  };
  // 검색으로 식당찾기

  const getRestaruantByTitle = async ({ foodid, locationId, page }: any) => {
    const res = await get(
      `select/dish/find/${foodid}/${locationId}/${page}`,
    ).then((r: any) => {
      return r;
    });
    return res;
  };
  return { getRestaurantByLocation, getRestaruantById, getRestaruantByTitle };
};

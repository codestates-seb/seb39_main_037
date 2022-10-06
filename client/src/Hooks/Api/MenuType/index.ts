import { get } from "Utils/api";

export const useMenuType = () => {
  const getMenuType = async () => {
    const res: any = await get("foodtype/all");

    return res;
  };
  const getRandomMenu = async (typeName: any) => {
    const res: any = await get(`select/dish/random?foodType=${typeName}`);

    return res;
  };
  return { getMenuType, getRandomMenu };
};

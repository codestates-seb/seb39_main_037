import { get } from "Utils/api";

export const useMenuType = () => {
  const getMenuType = async () => {
    const res: any = await get("foodtype/all").then((r: any) => {
      //   console.log(r);
      return r;
    });

    return res;
  };
  const getRandomMenu = async (typeName: any) => {
    const res: any = await get(`select/dish/random?foodType=${typeName}`).then(
      (r: any) => {
        console.log(r);
        return r;
      },
    );

    return res;
  };
  return { getMenuType, getRandomMenu };
};

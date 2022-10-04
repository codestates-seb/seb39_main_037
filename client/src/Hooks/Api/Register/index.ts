import { post } from "Utils/api";

interface IPostReview {
  reviewTitle: string;
  reviewBody: string;
  tasteStar: number;
  facilityStar: number;
  priceStar: number;
  reviewImgUrl: string | null;
  userId: number;
  restaurantId: number;
}
export const useRegister = () => {
  const postReview = async ({
    reviewTitle,
    reviewBody,
    tasteStar,
    facilityStar,
    priceStar,
    reviewImgUrl,
    userId,
    restaurantId,
  }: IPostReview) => {
    const res = await post(`/review/post`, {
      reviewTitle,
      reviewBody,
      tasteStar,
      facilityStar,
      priceStar,
      reviewImgUrl,
      userId,
      restaurantId,
    }).then((r: any) => {
      console.log(r);
      return r;
    });
    return res;
  };
  return { postReview };
};

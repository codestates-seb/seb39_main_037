import { del, get, patch, post } from "Utils/api";

interface IPatchReview {
  reviewId: number;
  userId: number;
  reviewTitle: string;
  reviewBody: string;
  tasteStar: number;
  facilityStar: number;
  priceStar: number;
}

export const useReview = () => {
  const getReviewByRestaurant = async ({ restaurantId, page }: any) => {
    const res = await get(`/review/restaurant/${restaurantId}/${page}`).then(
      (r: any) => {
        return r;
      },
    );
    return res;
  };
  const getReview = async ({ reviewId }: any) => {
    const res = await get(`/review/${reviewId}`).then((r: any) => {
      return r;
    });
    return res;
  };

  const patchReview = async ({
    reviewId,
    userId,
    reviewTitle,
    reviewBody,
    tasteStar,
    facilityStar,
    priceStar,
  }: IPatchReview) => {
    const res = await patch(`/review/edit`, {
      reviewId,
      userId,
      reviewTitle,
      reviewBody,
      tasteStar,
      facilityStar,
      priceStar,
    }).then((r: any) => {
      return r;
    });
    return res;
  };
  const delReview = async ({ reviewId }: any) => {
    const res = await del(`review/delete/${reviewId}`).then((r: any) => {
      return r;
    });
    return res;
  };

  const postThumb = async ({ reviewId, userId }: any) => {
    const res = await post(`thumbUp/post/${reviewId}`, { userId }).then(
      (r: any) => {
        return r;
      },
    );
    return res;
  };

  return {
    getReviewByRestaurant,
    getReview,
    patchReview,
    delReview,
    postThumb,
  };
};

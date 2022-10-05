export interface IReviewObj {
  reviewId: number;
  reviewTitle: string;
  reviewBody: string;
  view: number;
  thumbUp: number;
  createdAt: string;
  updatedAt: string;
  reviewImgUrl: null | string;
  restaurantId: number;
  restaurantName: string;
  tasteStar: number;
  facilityStar: number;
  priceStar: number;
  foodTypeId: number;
  locationId: number;
  userId: number;
  nickname: string;
}

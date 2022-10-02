export interface IRestaurant {
  restaurantId: number;
  restaurantName: string;
  category: string;
  description: null | string;
  restaurantPhone: null | string;
  address: string;
  aveTaste: number;
  aveFacility: number;
  avePrice: number;
  mapx: number;
  mapy: number;
  locationId: number;
  foodTypeName: null | string;
}

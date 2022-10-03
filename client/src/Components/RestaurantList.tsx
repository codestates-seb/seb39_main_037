import React from "react";
import { IRestaurant } from "Types";

const RestaurantList = ({ restaurants }: Array<IRestaurant> | any) => {
  return (
    <div>
      {restaurants.map((r: IRestaurant) => (
        <div key={r.restaurantId}>{r.restaurantName}</div>
      ))}
    </div>
  );
};

export default RestaurantList;

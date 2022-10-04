import React from "react";
import styled from "styled-components";
import { IRestaurant } from "Types";

const RestaurantList = ({ restaurants }: Array<IRestaurant> | any) => {
  return (
    <RestaurantWrapper>
      {restaurants.map((r: IRestaurant) => (
        <IndividualResWrapper key={r.restaurantId}>
          <RestaurantInpo>
            <div>{r.restaurantName}</div>
            <LocationAndNumber>
              <div>주소 : {r.address}</div>
              <div>
                전화번호 :{" "}
                {r.restaurantPhone === null ? "-" : r.restaurantPhone}
              </div>
            </LocationAndNumber>
            <AddInpo>
              <div>맛 : {r.aveTaste === 0 ? "-" : r.aveTaste} / 5</div>
              <div>시설 : {r.aveFacility === 0 ? "-" : r.aveTaste} / 5</div>
              <div>가격 : {r.avePrice === 0 ? "-" : r.avePrice} / 5</div>
            </AddInpo>
          </RestaurantInpo>
        </IndividualResWrapper>
      ))}
    </RestaurantWrapper>
  );
};

const RestaurantWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2rem;
`;

const IndividualResWrapper = styled.div`
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 1rem;
  gap: 2rem;
  background-color: #fafafa;
`;

const RestaurantInpo = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  gap: 2rem;
`;
// const;

const LocationAndNumber = styled.div`
  font-size: 13px;
`;

const AddInpo = styled.div`
  display: flex;
  gap: 1rem;
`;

export default RestaurantList;

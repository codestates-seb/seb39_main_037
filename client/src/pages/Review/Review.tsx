import Loading from "Components/Common/Loading";
import MenuType from "Components/Common/MenuType";
import PaginationForm from "Components/Common/Pagination/PaginationForm";
import RestaurantList from "Components/RestaurantList";
import { useRestaurant } from "Hooks/Api/Restaurant/index";
import useCurrentLocation from "Hooks/useCurrentLocation";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { IPageObj, IRestaurant } from "Types";

const Review = () => {
  useState<string[]>([]);
  const navigate = useNavigate();
  const { getRestaurantByLocation } = useRestaurant();
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [pageInfo, setPageInfo] = useState<IPageObj>();
  const [restaurantList, setRestaurantList] = useState<IRestaurant[] | null>();
  const menuClick = () => {
    navigate(`/random-recommend/map/:food_id`);
  };

  const { currentLocation } = useCurrentLocation();
  useEffect(() => {
    if (currentLocation.locationId === 0) {
      // 로케이션이 저장되어있지 않으면?
      navigate("/location");
    } else {
      getRestaurantByLocation({
        locationId: currentLocation.locationId,
        page: currentPage,
      }).then((r: any) => {
        setPageInfo(r.pageInfo);
        setRestaurantList(r.data);
      });
    }
  }, [currentPage]);
  console.log(restaurantList, pageInfo);
  if (!restaurantList && !pageInfo) return <Loading />;
  return (
    <ReviewWrapper>
      <RestaurantList restaurants={restaurantList} />

      {pageInfo && pageInfo?.totalPages > 1 && (
        <PaginationForm
          activePage={currentPage}
          totalItemsCount={pageInfo.totalElements}
          onChange={(e: any) => {
            console.log(e);
            setCurrentPage(e);
          }}
        />
      )}
    </ReviewWrapper>
  );
};

export default Review;

const ReviewWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
`;

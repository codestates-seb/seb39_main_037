import { SquareButtonForm } from "Components/Common/Button/SquareButtonForm";
import Loading from "Components/Common/Loading";
import PaginationForm from "Components/Common/Pagination/PaginationForm";
import ReviewList from "Components/List/ReviewList";
import { useRestaurant } from "Hooks/Api/Restaurant/index";
import { useReview } from "Hooks/Api/Review/index";
import useCurrentRestaurant from "Hooks/useCurrentRestaurant";
import useCurrentUser from "Hooks/useCurrentUser";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import styled from "styled-components";
import { IPageObj, IRestaurant, IReviewObj } from "Types";

const ReviewByRestaurant = () => {
  const { currentUser } = useCurrentUser();
  const { setCurrentRestaurant } = useCurrentRestaurant();
  const navigate = useNavigate();
  const [restaurantInfo, setRestaurantInfo] = useState<IRestaurant>();
  const [reviewList, setReviewList] = useState<IReviewObj[]>();
  const { getRestaruantById } = useRestaurant();
  const { getReviewByRestaurant } = useReview();
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [pageInfo, setPageInfo] = useState<IPageObj>();
  const { restaurant: restaurantId } = useParams();
  useEffect(() => {
    getRestaruantById({ restaurantId }).then((r: any) => {
      console.log(r);
      setRestaurantInfo(r);
    });
    getReviewByRestaurant({ restaurantId, page: currentPage }).then(
      (r: any) => {
        console.log(r);
        setReviewList(r.data);
        setPageInfo(r.pageInfo);
      },
    );
  }, [currentPage]);
  const handleClick = () => {
    if (restaurantInfo) {
      setCurrentRestaurant(restaurantInfo);
      navigate(`/register`);
    }
  };
  if (!restaurantInfo || !reviewList || !pageInfo) return <Loading />;
  return (
    <ReviewByRestaurantWrapper>
      <RestaurantInfo>
        <div className="left">
          <p style={{ fontWeight: "700" }}>{restaurantInfo?.restaurantName}</p>
          <p>주소:{restaurantInfo?.address}</p>
        </div>
        <div className="right">
          <span>맛 평점:{restaurantInfo?.aveTaste}</span>
          <span> 시설 평점:{restaurantInfo?.aveFacility}</span>
          <span>
            가격 평점:
            {restaurantInfo?.avePrice}
          </span>
        </div>
        {currentUser.userId !== 0 && (
          <SquareButtonForm
            title="리뷰쓰러가기"
            widthStyle="30%"
            onClick={handleClick}
          />
        )}
      </RestaurantInfo>
      <ReviewInfo>
        {reviewList?.map((r) => (
          <ReviewList review={r} key={r.reviewId} />
        ))}
      </ReviewInfo>
      {pageInfo.totalPages > 1 && (
        <PaginationForm
          activePage={currentPage}
          totalItemsCount={pageInfo.totalElements}
          onChange={(e: any) => {
            console.log(e);
            setCurrentPage(e);
          }}
        />
      )}
    </ReviewByRestaurantWrapper>
  );
};
export default ReviewByRestaurant;

const ReviewByRestaurantWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  margin-top: 30px;
  display: flex;
  flex-direction: column;
`;
const RestaurantInfo = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding-bottom: 30px;
  border-bottom: solid 0.5px gray;
  .left {
    display: flex;
    flex-direction: column;
    font-size: 20px;
    gap: 15px;
  }
  .right {
    display: flex;
    align-items: center;
    > span {
      margin: 3px;
    }
  }
`;
const ReviewInfo = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  flex-direction: column;
`;

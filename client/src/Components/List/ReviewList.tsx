import defaultFood from "Media/Image/defaultFood.png";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";
import { IReviewObj } from "Types";

const ReviewList = ({ review }: IReviewObj | any) => {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/review/${review.restaurantId}/${review.reviewId}`);
  };
  return (
    <ReviewListWrapper onClick={handleClick}>
      {review.nickname}
      <ReviewContent>
        <div>{review.reviewTitle}</div>
        <div>{review.reviewBody}</div>
      </ReviewContent>
      <div>
        {review.reviewImg ? (
          <RestaurantImg src={review.reviewImg} alt="" />
        ) : (
          <RestaurantImg src={defaultFood} alt="" />
        )}
      </div>
    </ReviewListWrapper>
  );
};
export default ReviewList;
const ReviewListWrapper = styled.div`
  list-style: none;
  display: flex;
  min-width: 70%;
  margin: 10px 25px;
  justify-content: space-between;
  align-content: center;
  align-items: center;
  padding: 32px 32px;
  height: 100px;
  border: 4px solid rgb(247, 247, 247);

  gap: 1rem;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    min-width: 30rem;
  }
`;

const RestaurantImg = styled.img`
  width: 80px;
  height: 80px;
`;

const ReviewContent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 60%;
  gap: 1rem;
  > div {
    width: 100%;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }
`;

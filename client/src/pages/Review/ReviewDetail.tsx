import {
  ArrowDropDown,
  ArrowDropUp,
  Favorite,
  FavoriteBorder,
  History,
} from "@mui/icons-material";
import ToastViewer from "Components/Common/Toast/ToastViewer";
import { useNavigate, useParams } from "react-router";
import styled from "styled-components";

const reviewMock = {
  reviewId: 1,
  reviewTitle: "리뷰",
  reviewBody: "리뷰 본문",
  view: 0,
  thumbUp: 0,
  createdAt: "2022-10-01",
  updatedAt: "2022-10-01",
  reviewImgUrl:
    "https://seb39pre37.s3.ap-northeast-2.amazonaws.com/reviewimg/e6365a98-49a1-4f8f-b4e0-74d53d1e7301_%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202022-09-23%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%202.31.04.png",
  restaurantId: 1,
  restaurantName: "코코이찌방야 압구정로데오점",
  tasteStar: 3,
  facilityStar: 2,
  priceStar: 3,
  foodTypeId: 1,
  locationId: 2,
  userId: 1,
  nickname: "jin2",
};
const ReviewDetail = () => {
  const { review_id: reviewId } = useParams();

  return (
    <ReviewDetailWrapper>
      <ReviewWrapper>
        <RestaurantInfo>
          <div className="left">
            <p>{reviewMock.restaurantName}</p>
            <p>작성자:{reviewMock.nickname}</p>
          </div>
          <div className="right">
            맛:{reviewMock.tasteStar} 시설:{reviewMock.facilityStar} 가격:
            {reviewMock.priceStar} <Favorite />
            {reviewMock.thumbUp}
          </div>
        </RestaurantInfo>

        <ToastViewer contents={reviewMock.reviewBody} />
      </ReviewWrapper>

      <CommentWrapper />
    </ReviewDetailWrapper>
  );
};

export default ReviewDetail;

const ReviewDetailWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
`;
const RestaurantInfo = styled.div`
  width: 100%;
  display: grid;
  align-items: center;
  grid-template-columns: max-content 1fr;
  .left {
    display: flex;
    flex-direction: column;
  }
`;
const ReviewWrapper = styled.div``;
const CommentWrapper = styled.div``;

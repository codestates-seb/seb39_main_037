import { Favorite } from "@mui/icons-material";
import CommentInput from "Components/Comment/CommentInput";
import CommentList from "Components/Comment/CommentList";
import PaginationForm from "Components/Common/Pagination/PaginationForm";
import ToastViewer from "Components/Common/Toast/ToastViewer";
import { useState } from "react";
import { useNavigate, useParams } from "react-router";
import styled from "styled-components";

const reviewMock = {
  reviewId: 1,
  reviewTitle: "리뷰",
  reviewBody: `# 코코이찌방야

  ### 처음가보는 맛집
  굳굳
  
  `,
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
  const [currentPage, setCurrentPage] = useState<number>(1);
  return (
    <ReviewDetailWrapper>
      <RestaurantInfo>
        <div className="left">
          <p style={{ fontWeight: "700" }}>{reviewMock.restaurantName}</p>
          <p>작성자:{reviewMock.nickname}</p>
        </div>
        <div className="right">
          <span>맛:{reviewMock.tasteStar}</span>
          <span> 시설:{reviewMock.facilityStar}</span>
          <span>
            가격:
            {reviewMock.priceStar}
          </span>
          <span>
            <Favorite />
          </span>
          <span>{reviewMock.thumbUp}</span>
        </div>
      </RestaurantInfo>
      <BodyWrapper>
        <ViewerWrapper>
          <ToastViewer contents={reviewMock.reviewBody} />
        </ViewerWrapper>
        <ReviewLine />
        <CommentWrapper>
          <CommentInputWrapper>
            <CommentInput reviewId={Number(reviewId)} />
          </CommentInputWrapper>
          <CommentList reviewId={Number(reviewId)} />
        </CommentWrapper>
      </BodyWrapper>

      <PaginationForm
        activePage={1}
        totalItemsCount={26}
        onChange={(e: any) => setCurrentPage(e)}
      />
      {/* <PaginationForm
        activePage={currentPage}
        totalItemsCount={totalElements}
        onChange={(e: any) => setCurrentPage(e)}
      /> */}
    </ReviewDetailWrapper>
  );
};

export default ReviewDetail;

const ReviewDetailWrapper = styled.div`
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
const ReviewLine = styled.div`
  width: 100%;
  border-bottom: solid 0.5px gray;
`;
const BodyWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ViewerWrapper = styled.div`
  display: flex;
  width: 80%;
  margin: 20px;
  min-height: 500px;
  max-height: 700px;
  overflow-y: auto;
`;
const CommentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
`;
const CommentInputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  border-bottom: dotted 0.5px gray;
`;

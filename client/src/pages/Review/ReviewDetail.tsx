import { Favorite } from "@mui/icons-material";
import CommentInput from "Components/Comment/CommentInput";
import CommentList from "Components/Comment/CommentList";
import Loading from "Components/Common/Loading";
import ToastViewer from "Components/Common/Toast/ToastViewer";
import { useReview } from "Hooks/Api/Review";
import useCurrentUser from "Hooks/useCurrentUser";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import styled from "styled-components";
import { IRestaurant, IReviewObj } from "Types";

const ReviewDetail = () => {
  const { getReview, postThumb } = useReview();
  const { review_id: reviewId } = useParams();
  const { currentUser } = useCurrentUser();
  const [currentReview, setCurrentReview] = useState<IReviewObj>();

  useEffect(() => {
    getReview({ reviewId }).then((r: any) => {
      setCurrentReview(r);
    });
  }, []);

  if (!currentReview) return <Loading />;
  return (
    <ReviewDetailWrapper>
      <RestaurantInfo>
        <div className="left">
          <p style={{ fontWeight: "700" }}>{currentReview.restaurantName}</p>
          <p>작성자:{currentReview.nickname}</p>
        </div>
        <div className="right">
          <span>맛:{currentReview.tasteStar}</span>
          <span> 시설:{currentReview.facilityStar}</span>
          <span>
            가격:
            {currentReview.priceStar}
          </span>
          <span className="thumb">
            <Favorite />
          </span>
          <span>{currentReview.thumbUp}</span>
        </div>
      </RestaurantInfo>
      <BodyWrapper>
        <ViewerWrapper>
          <ToastViewer contents={currentReview.reviewBody} />
        </ViewerWrapper>
        <ReviewLine />
        <CommentWrapper>
          {currentUser.userId !== 0 && (
            <CommentInputWrapper>
              <CommentInput reviewId={Number(reviewId)} />
            </CommentInputWrapper>
          )}

          <CommentList reviewId={Number(reviewId)} />
        </CommentWrapper>
      </BodyWrapper>
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
    .thumb {
      &:hover {
        opacity: 0.5;
      }
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

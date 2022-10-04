import { OutlinedInput } from "@mui/material";
import RatingImg from "Components/Common/RatingImg";
import { SquareButtonForm } from "Components/Common/SquareButtonForm";
import ToastEditor from "Components/Common/Toast/ToastEditor";
import { useRegister } from "Hooks/Api/Register";
import useCurrentUser from "Hooks/useCurrentUser";
import React, { useState } from "react";
import styled from "styled-components";

const ReviewRegister = () => {
  const { currentUser } = useCurrentUser(); // 로그인 한 유저
  const { postReview } = useRegister();
  const [thumbnail, setThumbnail] = useState<string | null>(null);
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const restaurant = { title: "지영반점", id: 15 };

  const [tasteStar, setTasteStar] = useState<number>(1);
  const [facilityStar, setFacilityStar] = useState<number>(1);
  const [priceStar, setPriceStar] = useState<number>(1);
  const handleSubmit = () => {
    postReview({
      reviewTitle: title,
      reviewBody: content,
      reviewImgUrl: thumbnail,
      tasteStar,
      facilityStar,
      priceStar,
      userId: currentUser.userId,
      restaurantId: restaurant.id,
    });
  };
  return (
    <ReviewRegisterWrapper>
      <ReviewRegisterContainer>
        <div className="title">{restaurant.title}리뷰쓰기</div>
        <ReviewForm>
          <InputLabel>
            <span>제목</span>
            <InputField
              type="text"
              name="nameForm"
              value={title}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                setTitle(e.target.value);
              }}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
          <RatingWrapper>
            맛:
            <RatingImg title="맛" setRate={setTasteStar} />
          </RatingWrapper>
          <RatingWrapper>
            시설:
            <RatingImg title="시설" setRate={setFacilityStar} />
          </RatingWrapper>
          <RatingWrapper>
            가격:
            <RatingImg title="가격" setRate={setPriceStar} />
          </RatingWrapper>
          <ToastEditor
            thumbnail={thumbnail}
            setThumbnail={setThumbnail}
            setContent={setContent}
          />
          <SquareButtonForm
            buttonColor="var(--green-color)"
            onClick={handleSubmit}
            widthStyle="100%"
            title="게시글 저장"
          />
        </ReviewForm>
      </ReviewRegisterContainer>
    </ReviewRegisterWrapper>
  );
};

export default ReviewRegister;

const ReviewRegisterWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
`;
const ReviewRegisterContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  .title {
    margin: 10px;
    font-size: 3rem;
  }
`;
const RatingWrapper = styled.div`
  display: flex;
`;
const ReviewForm = styled.div``;

const InputLabel = styled.div`
  width: 95%;
  padding: 0.5vw 0;
  font-size: 10pt;
  margin-bottom: 1vw;
`;
const InputField = styled(OutlinedInput)`
  width: 100%;
  font-size: 10pt;
`;

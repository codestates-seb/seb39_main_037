import RatingImg from "Components/Common/RatingImg";
import { SquareButtonForm } from "Components/Common/SquareButtonForm";
import ToastEditor from "Components/Common/Toast/ToastEditor";
import { useState } from "react";
import styled from "styled-components";

const ReviewRegister = () => {
  const [thumbnail, setThumbnail] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const restaurant = { title: "지영반점", id: 1 };
  const [tasteStar, setTasteStar] = useState<number>(1);
  const [facilityStar, setFacilityStar] = useState<number>(1);
  const [priceStar, setPriceStar] = useState<number>(1);
  const handleSubmit = () => {
    console.log(thumbnail, content, tasteStar, facilityStar, priceStar);
  };
  return (
    <ReviewRegisterWrapper>
      <ReviewRegisterContainer>
        <div className="title">{restaurant.title}리뷰쓰기</div>
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
        />
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

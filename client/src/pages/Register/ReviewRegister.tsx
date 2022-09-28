import ToastEditor from "Components/Common/Toast/ToastEditor";
import { useState } from "react";
import styled from "styled-components";
// interface IReviewRegister {
//   restaurant: object;
// }
const ReviewRegister = () => {
  const [thumbnail, setThumbnail] = useState<string>("");
  const [content, setContent] = useState<string>("");
  return (
    <ReviewRegisterWrapper>
      {/* <div className="title">{restaurant.title}리뷰쓰기</div> */}
      <ToastEditor
        thumbnail={thumbnail}
        setThumbnail={setThumbnail}
        setContent={setContent}
      />
    </ReviewRegisterWrapper>
  );
};

export default ReviewRegister;

const ReviewRegisterWrapper = styled.div`
  width: 100%;
`;

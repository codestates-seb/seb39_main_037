import React from "react";
import styled from "styled-components";

interface IButtonProps {
  onClick: (e: React.MouseEvent<HTMLElement>) => void;
  id: string;
}
export const CancelFinishButton = ({ onClick, id }: IButtonProps) => {
  return (
    <div>
      <ButtonCancel type="button" onClick={onClick} id={id}>
        취소
      </ButtonCancel>
      <ButtonFinish type="button" onClick={onClick} id={id}>
        완료
      </ButtonFinish>
    </div>
  );
};

const ButtonCancel = styled.button`
  background-color: #f1f1f1;
  border: 0;
  outline: 0;
  min-width: 70px;
  height: 32px;
  line-height: 30px;
  margin-right: 5px;
  cursor: pointer;
  &:hover {
    background-color: #d8d8d8;
  }
`;

const ButtonFinish = styled.button`
  background-color: var(--main-yellow-30);
  border: 0;
  outline: 0;
  min-width: 70px;
  height: 32px;
  line-height: 30px;
  cursor: pointer;
  &:hover {
    background-color: var(--main-yellow);
  }
`;

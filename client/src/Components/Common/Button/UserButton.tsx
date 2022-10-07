import React from "react";
import styled from "styled-components";

interface IButtonProps {
  text: string;
  onClick: (e: React.MouseEvent<HTMLElement>) => void;
}

const UserButton = ({ text, onClick }: IButtonProps) => {
  return (
    <>
      {text === "사진" && (
        <ButtonStyle type="button" onClick={onClick}>
          {text} 변경
        </ButtonStyle>
      )}

      {text === "비밀번호" && (
        <ButtonStyle type="button" onClick={onClick}>
          {text} 변경
        </ButtonStyle>
      )}

      {text === "닉네임" && (
        <ButtonStyle type="button" onClick={onClick}>
          {text} 변경
        </ButtonStyle>
      )}
    </>
  );
};

const ButtonStyle = styled.button`
  border: 1px solid #e5e5e5;
  background-color: #ffffff;
  min-width: 100px;
  border-radius: 3px;
  cursor: pointer;

  &:hover {
    border-color: #000000;
  }
`;

export default UserButton;

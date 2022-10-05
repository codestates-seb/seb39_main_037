import MenuType from "Components/Common/MenuType";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const Review = () => {
  const [selectemenuType, setSelectedmenuType] = useState<string>("");
  const [selectedmenuList, setSelectedmenuList] = useState<string[]>([]);
  const navigate = useNavigate();
  const menuClick = () => {
    navigate(`/random-recommend/map/:food_id`);
  };
  return (
    <ReviewWrapper>
      <MenuType
        setSelectedmenuList={setSelectedmenuList}
        setSelectedmenuType={setSelectedmenuType}
      />
    </ReviewWrapper>
  );
};

export default Review;

const ReviewWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
`;

import React, { useState } from "react";
import styled from "styled-components";

import { RandomMockupData } from "./RandomMockupData";

const Random = () => {
  const [isClick, setIsClick] = useState<boolean>(false);
  const [randomMenu, setRandomMenu] = useState("");

  const ClickRandom = (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();
    setIsClick(true);
    const randomRecommand =
      RandomMockupData[Math.floor(Math.random() * RandomMockupData.length)];
    setRandomMenu(randomRecommand.menu);
  };

  const allnum = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100];

  const newnum = [];

  while (allnum.length - 7) {
    const movenum = allnum.splice(
      Math.floor(Math.random() * allnum.length),
      1,
    )[0];
    newnum.push(movenum);
  }
  console.log(newnum);

  return (
    <Content>
      <DivBox>
        {RandomMockupData.map(({ menu, menuid }) => (
          <div key={menuid}>{menu}</div>
        ))}
      </DivBox>
      <div>
        <div>
          <button type="button" onClick={ClickRandom}>
            랜덤 추천
          </button>
        </div>
        <ul>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button">{randomMenu}</button>
            )}
          </li>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button">{randomMenu}</button>
            )}
          </li>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button">{randomMenu}</button>
            )}
          </li>
        </ul>
      </div>
    </Content>
  );
};

export default Random;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 2rem;
`;

const DivBox = styled.div`
  border: 1px solid #e5e5e5;
  display: flex;
  flex-wrap: wrap;
  padding: 1rem;
  gap: 3rem;
  max-width: 560px;
  min-width: 290px;
`;

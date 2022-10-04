import { RandomMockupData } from "pages/Random/RandomMockupData";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

interface IRandomprops {
  setSelectedmenuList: React.Dispatch<React.SetStateAction<string[]>>;
  setSelectedmenuType: React.Dispatch<React.SetStateAction<string>>;
}

const MenuType = ({
  setSelectedmenuType,
  setSelectedmenuList,
}: IRandomprops) => {
  const [classname, setClassname] = useState<string>("");

  const ClickMenu = (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();
    const target = e.currentTarget as HTMLInputElement;

    RandomMockupData.forEach(({ menuType, list, menuid }) => {
      if (menuType === target.innerText) {
        setSelectedmenuList(list);
        setSelectedmenuType(menuType);
        setClassname(`${menuid}`);
      }
    });
  };

  return (
    <DivBox>
      {RandomMockupData.map(({ menuType, menuid, imgURL }) => (
        <ButtonDiv
          key={menuid}
          onClick={(e) => ClickMenu(e)}
          className={classname === `${menuid}` ? "active" : `${menuid}`}
        >
          <img src={imgURL} alt={menuType} />
          <div>{menuType}</div>
        </ButtonDiv>
      ))}
    </DivBox>
  );
};

const DivBox = styled.div`
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  padding: 1rem;
  gap: 3rem;
  max-width: 660px;
  min-width: 290px;

  > .active {
    transition: all 0.2s;
    font-weight: bold;
    color: var(--main-yellow);

    &:hover {
      position: relative;
      box-shadow: none;
      transform: scale(1.1, 1.1);
    }
  }
`;

const ButtonDiv = styled.div`
  min-width: 80px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  cursor: pointer;

  > img {
    width: 50px;
    height: 50px;
  }
`;

export default MenuType;

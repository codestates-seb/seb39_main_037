import MenuType from "Components/Common/MenuType";
import { useMenuType } from "Hooks/Api/MenuType";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

interface Iselectedmenu {
  foodName: string;
  foodTypeName: string;
}
interface Itest {
  foodName: string;
  foodId: number;
}

type Iselectedmenulist = Array<Iselectedmenu>;
const Random = () => {
  const [isClick, setIsClick] = useState<boolean>(false);
  const [selectemenuType, setSelectedmenuType] = useState<string>("");
  const [data, setData] = useState<object[]>([]);
  const { getRandomMenu } = useMenuType();

  const navigate = useNavigate();
  const menuClick = (e: React.MouseEvent<HTMLElement>) => {
    const target = e.currentTarget as HTMLInputElement;

    navigate(`/random-recommend/map/${target.innerText}/${target.id}`);
  };

  const savedId: string[] = [];
  const savedMenu: string[] = [];
  const clickRandom = async (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();

    if (!selectemenuType) {
      setIsClick(false);
    } else {
      setIsClick(true);
    }

    const res = await getRandomMenu(selectemenuType);
    try {
      setData(res);
    } catch (err) {
      alert(err);
    }
  };
  if (data !== undefined) {
    data.map(({ foodId }: any) => savedId.push(foodId));
    data.map(({ foodName }: any) => savedMenu.push(foodName));
  }

  return (
    <Content>
      <MenuType setSelectedmenuType={setSelectedmenuType} />

      <RandomDiv>
        <div>
          <ButtonRandom type="button">
            {!selectemenuType ? (
              <div>랜덤 추천</div>
            ) : (
              <div onClick={clickRandom}>{`${selectemenuType} 랜덤 추천`}</div>
            )}
          </ButtonRandom>
        </div>
        <UlBox>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button" onClick={menuClick} id={savedId[0]}>
                {savedMenu[0]}
              </button>
            )}
          </li>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button" onClick={menuClick} id={savedId[1]}>
                {savedMenu[1]}
              </button>
            )}
          </li>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button" onClick={menuClick} id={savedId[2]}>
                {savedMenu[2]}
              </button>
            )}
          </li>
        </UlBox>
      </RandomDiv>
    </Content>
  );
};

export default Random;

const Content = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  padding: 2rem;
  align-items: center;
`;

const RandomDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 3rem;
`;

const ButtonRandom = styled.button`
  /* border: 0;
  outline: 0;
  width: 200px;
  height: 100px;
  background-color: var(--green-color);
  border-radius: 10px; */
  width: 200px;

  padding: 15px 30px;
  border-radius: 15px;
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  border: 3px solid #fce205;
  color: #6e6e6e;
  cursor: pointer;
  :hover {
    background-color: #fce205;
    color: #6e6e6e;
    transform: scale(1.3);
  }
`;

const UlBox = styled.ul`
  display: flex;
  gap: 3rem;
  padding: 3rem;
  flex-wrap: wrap;
  justify-content: center;

  > li {
    display: flex;
    justify-content: center;
    align-items: center;
    /* min-width: 150px; */
    > button {
      width: 100px;
      height: 50px;
      background-color: var(--nc-button-color);
      border-radius: 10px;
      outline: 0;
      border: 0;
      cursor: pointer;

      :hover {
        /* background-color: var(--main-yellow); */

        background-color: #fce205;
        color: #6e6e6e;
        transform: scale(1.3);
      }
    }
  }
`;

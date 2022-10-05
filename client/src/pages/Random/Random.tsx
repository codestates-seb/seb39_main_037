import MenuType from "Components/Common/MenuType";
import { useMenuType } from "Hooks/Api/MenuType";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

interface Iselectedmenu {
  foodName: string;
  foodTypeName: string;
}

type Iselectedmenulist = Array<Iselectedmenu>;
const Random = () => {
  const [isClick, setIsClick] = useState<boolean>(false);
  const [selectemenuType, setSelectedmenuType] = useState<string>("");
  const [selectedmenuList, setSelectedmenuList] = useState<string[]>([]);
  const [randomMenu, setRandomMenu] = useState<string[]>([]);
  const { getRandomMenu } = useMenuType();

  const [selectedmenulist, setSelectedmenulist] = useState<Iselectedmenulist>();
  const [test, settest] = useState<string[]>([]);

  const navigate = useNavigate();
  const menuClick = () => {
    navigate(`/random-recommend/map/:food_id`);
  };

  // const ClickRandom = (e: React.MouseEvent<HTMLElement>) => {
  //   e.stopPropagation();
  //   if (selectedmenuList.length === 0) {
  //     setIsClick(false);
  //   } else {
  //     setIsClick(true);
  //   }

  //   const selectIndex = (selectingNumber: number) => {
  //     const randomIndexArray = [];
  //     for (let i = 0; i < selectingNumber; i += 1) {
  //       const randomNum =
  //         selectedmenuList[Math.floor(Math.random() * selectedmenuList.length)];
  //       if (randomIndexArray.indexOf(randomNum) === -1) {
  //         randomIndexArray.push(randomNum);
  //       } else {
  //         i -= 1;
  //       }
  //     }
  //     return randomIndexArray;
  //   };
  //   if (selectedmenuList.length === 0) {
  //     setIsClick(false);
  //   } else {
  //     setRandomMenu(selectIndex(3));
  //   }
  // };
  const savedMenu1: string[] = [];
  const savedMenu: string[] = [];
  const clickRandom = async () => {
    await getRandomMenu(selectemenuType).then((res) => {
      console.log(res);

      res.map(({ foodName }: any) => {
        return savedMenu1.push(foodName);
      });

      settest(savedMenu1);
      setSelectedmenulist(res);
    });
    console.log(test);
    if (savedMenu.length === 0) {
      setIsClick(false);
    } else {
      setIsClick(true);
    }
    // e.stopPropagation();
  };
  console.log(selectedmenulist);
  if (!selectedmenulist) {
    console.log("not");
  } else {
    // selectedmenulist.map(({ foodName }) => savedMenu.push(foodName));
    // settest(selectedmenulist.map(({ foodName }) => foodName));
    selectedmenulist.map(({ foodName }) => savedMenu.push(foodName));
    console.log(savedMenu);
    // settest([...savedMenu]);
  }

  // console.log(test);
  // if()
  // if (selectedmenulist === undefined) {
  //   return <></>;
  // }

  console.log(savedMenu1);

  return (
    <Content>
      <MenuType
        setSelectedmenuList={setSelectedmenuList}
        setSelectedmenuType={setSelectedmenuType}
      />

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
              <button type="button" onClick={menuClick}>
                {test[0]}
              </button>
            )}
          </li>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button" onClick={menuClick}>
                {test[1]}
              </button>
            )}
          </li>
          <li>
            {isClick === false ? (
              <button type="button">메뉴</button>
            ) : (
              <button type="button" onClick={menuClick}>
                {test[2]}
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
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 2rem;
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
  width: 300px;

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

import { useMenuType } from "Hooks/Api/MenuType";
import { RandomMockupData } from "pages/Random/RandomMockupData";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

interface IRandomprops {
  setSelectedmenuType: React.Dispatch<React.SetStateAction<string>>;
}

const MenuType = ({ setSelectedmenuType }: IRandomprops) => {
  const [menu, setMenu] = useState<string[]>([]);
  const [menuList, setMenuList] = useState<string[]>([]);
  const [classname, setClassname] = useState<string>("");
  const { getMenuType, getRandomMenu } = useMenuType();
  const [nowmenuType, setNowmenuType] = useState();

  useEffect(() => {
    async function menuType() {
      getMenuType().then((res) => {
        setMenu(res.data);
      });
    }
    menuType();
  }, []);

  // const list: string[] = [];
  // useEffect(() => {
  //   async function menuList() {
  //     if (!nowmenuType) {
  //       console.log("not choice");
  //     } else {
  //       getRandomMenu(nowmenuType).then((res) => {
  //         // console.log(res);
  //         res.map(({ foodName }: any) => {
  //           return list.push(foodName);
  //         });
  //         // console.log(list);
  //         setMenuList(list);
  //       });
  //     }
  //   }
  //   menuList();
  // }, [nowmenuType]);
  // console.log(menuList);

  const ClickMenu = async (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();
    const target = e.currentTarget as HTMLInputElement;

    menu.forEach(({ foodTypeId, typeName }: any) => {
      if (typeName === target.innerText) {
        setSelectedmenuType(typeName);
        setClassname(`${foodTypeId}`);
        setNowmenuType(typeName);
      }
    });
  };

  return (
    <DivBox>
      {menu.map(({ typeName, foodTypeId, imgUrl }: any) => (
        <ButtonDiv
          key={foodTypeId}
          onClick={(e) => ClickMenu(e)}
          className={classname === `${foodTypeId}` ? "active" : `${foodTypeId}`}
        >
          <img src={imgUrl} alt={typeName} />
          <div>{typeName}</div>
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
  /* gap: 0.5rem; */

  max-width: 660px;
  /* min-width: 290px; */

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
  min-width: 90px;
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

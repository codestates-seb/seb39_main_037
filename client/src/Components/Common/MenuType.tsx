import { useMenuType } from "Hooks/Api/MenuType";
import { RandomMockupData } from "pages/Random/RandomMockupData";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

interface IRandomprops {
  setSelectedmenuList: React.Dispatch<React.SetStateAction<string[]>>;
  setSelectedmenuType: React.Dispatch<React.SetStateAction<string>>;
}
// interface Imenu {
//   typeName: string;
//   foodTypeId: number;
//   imgUrl: string;
// }

const MenuType = ({
  setSelectedmenuType,
  setSelectedmenuList,
}: IRandomprops) => {
  const [menu, setMenu] = useState<string[]>([]);
  const [classname, setClassname] = useState<string>("");
  const { getMenuType } = useMenuType();

  useEffect(() => {
    async function menuType() {
      getMenuType().then((res) => {
        setMenu(res.data);
      });
    }
    menuType();
  }, []);
  console.log(menu);

  const ClickMenu = (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();
    const target = e.currentTarget as HTMLInputElement;

    // RandomMockupData.forEach(({ menuType, list, menuid }) => {
    //   if (menuType === target.innerText) {
    //     setSelectedmenuList(list);
    //     setSelectedmenuType(menuType);
    //     setClassname(`${menuid}`);
    //   }
    // });
    menu.forEach(({ foodTypeId, typeName }: any) => {
      if (typeName === target.innerText) {
        setSelectedmenuType(typeName);
        setClassname(`${foodTypeId}`);
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
  padding: 2rem;
  gap: 3rem;
  /* gap: 0.5rem; */
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

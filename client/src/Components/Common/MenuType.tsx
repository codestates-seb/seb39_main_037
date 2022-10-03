import { RandomMockupData } from "pages/Random/RandomMockupData";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

interface IRandomprops {
  setSelectedmenuList: React.Dispatch<React.SetStateAction<string[]>>;
  setSelectedmenuType: React.Dispatch<React.SetStateAction<string>>;
}

const MenuType = ({
  setSelectedmenuList,
  setSelectedmenuType,
}: IRandomprops) => {
  const [tab, setTab] = useState<boolean>(false);
  const [classname, setClassname] = useState<string>("");
  const [test, settest] = useState<string>("not-active");
  const [test1, settest1] = useState<string[]>([]);
  const [test2, settest2] = useState<boolean[]>(
    Array(RandomMockupData.length).fill(false),
  );

  //   console.log(test2[0]);

  const ClickMenu = (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();
    setTab(!tab);
    const target = e.currentTarget as HTMLInputElement;

    RandomMockupData.forEach(({ menuType, list, menuid }) => {
      //   test2.push(false);
      //   console.log(test2);
      //   console.log(menuid - 1);

      if (menuType === target.innerText) {
        const copy = [...test2];
        test2.map((el, index) => copy[index] === true);
        // console.log(!test2[menuid - 1]);
        // !test2[menuid - 1];
        // console.log(test2[0]);
        console.log(copy);
        setSelectedmenuList(list);
        setSelectedmenuType(menuType);
        // settest2(test2[0]);
        // settest2(!test2[menuid - 1]);
        // target.className = "active";
      }
    });
  };
  return (
    <DivBox>
      {RandomMockupData.map(({ menuType, menuid, imgURL }) => (
        <div
          key={menuid}
          onClick={(e) => ClickMenu(e)}

          //   className={tab === true ? "active" : undefined}
        >
          <img src={imgURL} alt={menuType} />
          <div>{menuType}</div>
        </div>
      ))}
    </DivBox>
  );
};

const DivBox = styled.div`
  border: 1px solid #e5e5e5;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  padding: 1rem;
  gap: 3rem;
  max-width: 660px;
  min-width: 290px;
  > div {
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
  > .notactive {
    background-color: #fff;
    border: 1px solid #dbdee2;
    color: #404a5c;
  }
  > .active {
    background-color: #505bf0;
    color: #fff;
  }
`;

export default MenuType;

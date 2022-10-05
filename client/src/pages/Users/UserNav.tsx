import { ReactComponent as BasicUserImg } from "Asset/BasicUserImg.svg";
import Loading from "Components/Common/Loading";
import { useUsers } from "Hooks/Api/Users";
import { useEffect, useState } from "react";
import styled from "styled-components";

import { MockupData } from "./MockupData";
import { UserNavData } from "./UserNavData";
import UserNavItem from "./UserNavItem";
// interface IUserInfo {
//   null | string;
// }
const UsersNav = () => {
  const [userImg, setUserImg] = useState<null | string>();
  const { getUsers } = useUsers();
  async function getData() {
    await getUsers().then((res) => {
      setUserImg(res.profile);
    });
  }

  useEffect(() => {
    getData();
  }, []);
  // if (!userImg) {
  //   return <Loading />;
  // }
  // MockupData.user_img
  if (userImg === undefined) {
    return <Loading />;
  }

  return (
    <NavDiv>
      {userImg === null ? (
        <BasicUserImg />
      ) : (
        <img src={userImg} alt="프로필사진" />
      )}

      <Content>
        {UserNavData.map(({ link, text, id }) => (
          <UserNavItem key={id} link={link} text={text} />
        ))}
      </Content>
    </NavDiv>
  );
};

const NavDiv = styled.div`
  flex: 0.3;
  padding-top: 2rem;
  padding-left: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  top: 80px;
  z-index: 1000;
  > img {
    padding-bottom: 4rem;
    width: 200px;
    @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
      width: 80px;
      padding-bottom: 0;
      margin-right: 2rem;
    }
  }
  > svg {
    width: 150px;
    height: 200px;
    @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
      width: 60%;
      height: 60%;
      padding-bottom: 0;
      margin-right: 2rem;
    }
  }
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 2rem;

    justify-content: center;
  }
`;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 1rem;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    /* min-width: 300px; */
  }
`;

export default UsersNav;

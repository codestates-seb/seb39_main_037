import styled from "styled-components";

import { MockupData } from "./MockupData";
import { UserNavData } from "./UserNavData";
import UserNavItem from "./UserNavItem";

const UsersNav = () => {
  return (
    <NavDiv>
      <img src={`${MockupData.user_img}`} alt="프로필사진" />
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

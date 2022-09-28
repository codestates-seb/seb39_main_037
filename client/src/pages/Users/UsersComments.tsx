import { NavLink } from "react-router-dom";
import styled from "styled-components";

import { MockupComments } from "./MockupData";
import UsersNav from "./UserNav";

const UsersComments = () => {
  return (
    <Container>
      <UsersNav />
      <UsersContent>
        {MockupComments.map(
          ({
            commentid,
            title,
            restaurant,
            owner,
            restaurantImg,
            replyToUser,
          }) => (
            <ContainerLi key={commentid}>
              <FirstDiv>
                <NavLinkCss to="/review/:restaurant">
                  <div>{restaurant}</div>
                </NavLinkCss>
                <div>{owner.nickname}</div>
              </FirstDiv>
              <NavLinkCss to="/review/:restaurant/:review_id">
                <SecondDiv>
                  <div>{title}</div>
                  <div>{replyToUser.comment}</div>
                </SecondDiv>
              </NavLinkCss>
              <NavLinkCss to="/review/:restaurant/:review_id">
                <div>
                  <RestaurantImg src={restaurantImg} alt="음식 사진" />
                </div>
              </NavLinkCss>
            </ContainerLi>
          ),
        )}
      </UsersContent>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  padding-bottom: 40rem;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: flex;
    flex-direction: column;
    padding-left: 2rem;
  }
`;
const UsersContent = styled.section`
  width: calc(100% - 164px);
  display: flex;
  flex-direction: column;
  padding-top: 10rem;
`;
const ContainerLi = styled.li`
  list-style: none;
  display: flex;
  min-width: 70rem;

  justify-content: space-between;
  align-content: center;
  align-items: center;
  padding: 32px 32px;
  height: 100px;
  border: 4px solid rgb(247, 247, 247);
  margin-bottom: 1rem;
  gap: 1rem;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    min-width: 30rem;
  }
`;

const RestaurantImg = styled.img`
  width: 80px;
  height: 80px;
`;
const FirstDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 1rem;
  min-width: 3rem;
`;

const SecondDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  /* min-width: 50rem; */
`;

const NavLinkCss = styled(NavLink)`
  text-decoration: none;
  color: var(--font--color);
`;
export default UsersComments;

const test = [
  // 레스트랑 id랑 이름 , 해당 리뷰 작성한 유저 닉네임, 해당 리뷰 타이틀, 리뷰 대표 이미지 url,
  {
    commentId: 1,
    commentBody: "comment1",
    createdAt: "2022-09-28T02:29:50",
    updatedAt: "2022-09-28T02:29:50",
    userId: 1,
  },
  {
    commentId: 2,
    commentBody: "comment1",
    createdAt: "2022-09-28T02:30:04",
    updatedAt: "2022-09-28T02:30:04",
    userId: 1,
  },
  {
    commentId: 3,
    commentBody: "comment2",
    createdAt: "2022-09-28T02:30:10",
    updatedAt: "2022-09-28T02:30:10",
    userId: 1,
  },
];

import { NavLink } from "react-router-dom";
import styled from "styled-components";

import { MockupComments } from "./MockupData";
import UsersNav from "./UserNav";

const UsersLike = () => {
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

export default UsersLike;

const test = [
  // 필요한 부분 , reviewid는 해당 리뷰면 작성한 id와 닉네임? , 대표이미지 부분 url
  {
    userId: 1,
    reviewId: 2,
    restaurantId: 1,
    reviewTitle: "리뷰",
    reviewBody: "리뷰 본ㅇ문",
    restaurantName: "<b>강남</b>구청",
  },
  {
    userId: 1,
    reviewId: 1,
    restaurantId: 2,
    reviewTitle: "리뷰",
    reviewBody: "리뷰 본ㅇ문",
    restaurantName: "<b>신</b>마취통증의학과의원",
  },
];

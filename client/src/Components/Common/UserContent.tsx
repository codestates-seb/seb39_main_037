import defaultFood from "Media/Image/defaultFood.png";
import Review from "pages/Review/Review";
import { MockupComments } from "pages/Users/MockupData";
import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import styled from "styled-components";

import Loading from "./Loading";

const UserContent = ({ data }: any) => {
  const navigate = useNavigate();
  const menuClick = (e: React.MouseEvent<HTMLElement>) => {
    const target = e.currentTarget as HTMLInputElement;

    navigate(`/review/${target.id}`);
  };
  function textLengthOverCut(txt: string) {
    const len = 15;
    const lastTxt = "...";

    if (txt.length > len) {
      txt = txt.substring(0, len) + lastTxt;
    }
    return txt;
  }

  const url = window.location.href;

  const nowUrl = url.slice(url.lastIndexOf("/") + 1);

  if (data) {
    if (nowUrl === "review") {
      return (
        <UsersContent>
          {data.map(
            ({
              reviewId,
              restaurantName,
              nickname,
              reviewTitle,
              reviewBody,
              reviewImgUrl,
            }: any) => (
              <ContainerLi key={reviewId}>
                <FirstDiv>
                  <NavLinkCss to="/review/:restaurant/:review_id">
                    <div>{restaurantName}</div>
                  </NavLinkCss>
                  <div>{nickname}</div>
                </FirstDiv>
                <NavLinkCss to="/review/:restaurant/:review_id">
                  <SecondDiv>
                    <div>{textLengthOverCut(reviewTitle)}</div>
                    <div>{textLengthOverCut(reviewBody)}</div>
                  </SecondDiv>
                </NavLinkCss>
                <NavLinkCss to="/review/:restaurant/:review_id">
                  <div>
                    <RestaurantImg
                      src={reviewImgUrl === null ? defaultFood : reviewImgUrl}
                      alt="음식 사진"
                    />
                  </div>
                </NavLinkCss>
              </ContainerLi>
            ),
          )}
        </UsersContent>
      );
    }
    if (nowUrl === "like") {
      return (
        <UsersContent>
          {data.map(
            ({
              reviewId,
              restaurantName,
              reviewNickName,
              reviewTitle,
              reviewBody,
              reviewPhotoUrl,
            }: any) => (
              <ContainerLi key={reviewId}>
                <FirstDiv>
                  <NavLinkCss to="/review/:restaurant">
                    <div>{restaurantName}</div>
                  </NavLinkCss>
                  <div>{reviewNickName}</div>
                </FirstDiv>
                <NavLinkCss to="/review/:restaurant/:review_id">
                  <SecondDiv>
                    <div>{textLengthOverCut(reviewTitle)}</div>
                    <div>{textLengthOverCut(reviewBody)}</div>
                  </SecondDiv>
                </NavLinkCss>
                <NavLinkCss to="/review/:restaurant/:review_id">
                  <div>
                    <RestaurantImg
                      src={
                        reviewPhotoUrl === null ? defaultFood : reviewPhotoUrl
                      }
                      alt="음식 사진"
                    />
                  </div>
                </NavLinkCss>
              </ContainerLi>
            ),
          )}
        </UsersContent>
      );
    }

    if (nowUrl === "comment") {
      return (
        <UsersContent>
          {data.map(
            ({
              commentId,
              restaurantName,
              reviewNickName,
              reviewTitle,
              commentBody,
              reviewPhotoUrl,
            }: any) => (
              <ContainerLi key={commentId}>
                <FirstDiv>
                  <NavLinkCss to="/review/:restaurant">
                    <div>{restaurantName}</div>
                  </NavLinkCss>
                  <div>{reviewNickName}</div>
                </FirstDiv>
                <NavLinkCss to="/review/:restaurant/:review_id">
                  <SecondDiv>
                    <div>{textLengthOverCut(reviewTitle)}</div>
                    <div>{textLengthOverCut(commentBody)}</div>
                  </SecondDiv>
                </NavLinkCss>
                <NavLinkCss to="/review/:restaurant/:review_id">
                  <div>
                    <RestaurantImg
                      src={
                        reviewPhotoUrl === null ? defaultFood : reviewPhotoUrl
                      }
                      alt="음식 사진"
                    />
                  </div>
                </NavLinkCss>
              </ContainerLi>
            ),
          )}
        </UsersContent>
      );
    }
  }
  return (
    <>
      <Loading />
    </>
  );
};

const UsersContent = styled.section`
  flex: 1;
  display: flex;
  flex-direction: column;
  padding: 2rem;
`;
const ContainerLi = styled.li`
  list-style: none;
  display: flex;
  justify-content: space-between;
  align-content: center;
  align-items: center;
  padding: 32px 32px;
  height: 100px;
  border: 4px solid rgb(247, 247, 247);
  margin-bottom: 1rem;
  gap: 1rem;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    min-width: 10rem;
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

export default UserContent;

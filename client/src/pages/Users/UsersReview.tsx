import axios from "axios";
import { useUsers } from "Hooks/Api/Users";
import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import styled from "styled-components";

import { MockupComments } from "./MockupData";
import UsersNav from "./UserNav";

export interface AxiosResponse<T = any> {
  data: T;
  status: number;
  statusText: string;
  headers: any;
  // config: AxiosRequestConfig;
  request?: any;
}

const UsersReview = () => {
  const { getUsers } = useUsers();
  // const [fetchedData, setFetchedData] = useState<AxiosResponse | null | void>(
  //   null,
  // );
  // useEffect(() => {
  //   const getData = async () => {
  //     try {
  //       const res = await axios.get("https://foodreco.tk/v1/review/mypage/10");
  //       setFetchedData(res.data);
  //     } catch (err) {
  //       console.log("Error", err);
  //     }
  //   };
  //   getData();
  // }, []);

  // console.log(fetchedData);
  console.log(getUsers());

  // console.log(process.env.REACT_APP_API_URL);
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
export default UsersReview;

const test = [
  // 이미지 url, 레스토랑 이름,
  {
    reviewId: 1,
    reviewTitle: "리뷰",
    reviewBody: "리뷰 본ㅇ문",
    view: 0,
    thumbUp: 1,
    createdAt: "2022-09-28",
    updatedAt: "2022-09-28",
    restaurantId: 2,
    foodTypeId: 0,
    locationId: 0,
    userId: 10,
  },
];

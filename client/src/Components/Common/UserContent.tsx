import { MockupComments } from "pages/Users/MockupData";
import { NavLink } from "react-router-dom";
import styled from "styled-components";

const UserContent = () => {
  function textLengthOverCut(txt: string) {
    const len = 15;
    const lastTxt = "...";

    if (txt.length > len) {
      txt = txt.substring(0, len) + lastTxt;
    }
    return txt;
  }

  return (
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
                <div>{textLengthOverCut(title)}</div>
                <div>{textLengthOverCut(replyToUser.comment)}</div>
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

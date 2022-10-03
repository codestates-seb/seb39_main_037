import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import CloseIcon from "@mui/icons-material/Close";
import LoginIcon from "@mui/icons-material/Login";
import MenuIcon from "@mui/icons-material/Menu";
import TopModal from "Components/Common/Modal/TopModal";
import useCurrentLocation from "Hooks/useCurrentLocation";
import useCurrentUser from "Hooks/useCurrentUser";
import { ReactComponent as LogoIcon } from "Media/Image/logo/foodrecoLogo.svg";
import { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

const Header = () => {
  const [modal, setModal] = useState<boolean>(false);
  const { currentLocation } = useCurrentLocation();
  const { currentUser } = useCurrentUser();
  return (
    <NavContainer>
      <LogoIconDiv>
        <LinkCss to="/">
          <LogoIcon />
          {/* <img src={logo} alt="" width="100px" height="30px" /> */}
        </LinkCss>
      </LogoIconDiv>
      <UlContainer>
        <li>
          <LinkCss to="/location">
            {currentLocation.locationId ? (
              <div>
                {currentLocation.stateName}/{currentLocation.cityName}
              </div>
            ) : (
              <div>위치등록</div>
            )}
          </LinkCss>
        </li>
        <li>
          <LinkCss to="/random-recommend">
            <div>랜덤추천</div>
          </LinkCss>
        </li>
        <li>
          <LinkCss to="/review">
            <div>리뷰</div>
          </LinkCss>
        </li>
        <li>
          <MypageIconDiv>
            {currentUser.userId !== 0 ? (
              <LinkCss to="/users">
                <AccountCircleIcon />
              </LinkCss>
            ) : (
              <LinkCss to="/login">
                <LoginIcon />
              </LinkCss>
            )}
          </MypageIconDiv>
        </li>
      </UlContainer>
      <MenuIconContainer>
        {modal === false ? (
          <MenuIcon onClick={() => setModal(!modal)} />
        ) : (
          <CloseIcon onClick={() => setModal(false)} />
        )}
        {modal === true ? <TopModal setModal={setModal} /> : null}
      </MenuIconContainer>
    </NavContainer>
  );
};

const NavContainer = styled.header`
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #ffffff;
  height: 60px;
  width: 100%;
  z-index: 5000;
  border-top: 3px solid var(--main-yellow);
  box-shadow: 0px 2px 2px lightgray;
`;

const LogoIconDiv = styled.div`
  padding-left: 2rem;

  > a {
    & :hover {
      border-radius: 20px;
      opacity: 0.5;
    }
    svg {
      width: 100px;
      height: 60px;
    }
  }
`;

const UlContainer = styled.ul`
  display: flex;
  align-items: center;
  flex-grow: 1;
  justify-content: flex-end;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: none;
  }
  > li {
    display: flex;
    font-size: 18px;
    justify-content: center;
    min-width: 160px;
    padding-left: 4rem;
  }
`;

const MypageIconDiv = styled.div`
  padding-right: 2rem;
  > a {
    svg {
      width: 50px;
      height: 50px;
    }
  }
`;

const LinkCss = styled(Link)`
  text-decoration-line: none;
  color: var(--font--color);
`;

const MenuIconContainer = styled.div`
  padding-right: 2rem;
  @media screen and (min-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: none;
  }
`;

export default Header;

import useCurrentLocation from "Hooks/useCurrentLocation";
import useCurrentUser from "Hooks/useCurrentUser";
import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

interface propsType {
  setModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const TopModal = ({ setModal }: propsType) => {
  const { currentLocation, setCurrentLocation } = useCurrentLocation();
  const { currentUser, setCurrentUser } = useCurrentUser();
  const handleClick = () => {
    setModal(false);
  };
  const handleLogout = () => {
    if (window.confirm("로그아웃하시겠습니까?")) {
      setCurrentUser({
        userId: 0,
        email: "",
        nickName: "",
        profileImgUrl: "",
        userName: "",
      });
      setCurrentLocation({
        locationId: 0,
        stateName: "",
        cityName: "",
      });
      localStorage.removeItem("user-token");
    }
  };
  return (
    <UlContainer>
      <li>
        <LinkCss to="/location" onClick={handleClick}>
          {currentLocation.locationId ? (
            <div>
              {currentLocation.stateName}/{currentLocation.cityName}
            </div>
          ) : (
            <div>위치를 등록해주세요</div>
          )}
        </LinkCss>
      </li>
      <li>
        <LinkCss to="/random-recommend" onClick={handleClick}>
          <div>랜덤추천</div>
        </LinkCss>
      </li>
      <li>
        <LinkCss to="/review" onClick={handleClick}>
          <div>리뷰</div>
        </LinkCss>
      </li>

      {currentUser.userId !== 0 ? (
        <>
          <li>
            <LinkCss to="/users" onClick={handleClick}>
              <div>{currentUser.nickName}&apos;s Page</div>
            </LinkCss>
          </li>
          <li>
            <LinkCss to="/" onClick={handleLogout}>
              <div>로그아웃</div>
            </LinkCss>
          </li>
        </>
      ) : (
        <li>
          <LinkCss to="/login" onClick={handleClick}>
            <div>Login</div>
          </LinkCss>
        </li>
      )}
    </UlContainer>
  );
};

const UlContainer = styled.ul`
  display: flex;
  flex-direction: column;
  position: fixed;
  top: 60px;
  left: 0;
  background-color: white;
  height: 100%;
  width: 100%;
  > li {
    display: flex;
    align-items: center;
    padding: 32px 32px;
    height: 100px;
    border-bottom: 4px solid rgb(247, 247, 247);
  }
`;

const LinkCss = styled(Link)`
  text-decoration-line: none;
  color: black;
`;

export default TopModal;

import useCurrentLocation from "Hooks/useCurrentLocation";
import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

interface propsType {
  setModal: React.Dispatch<React.SetStateAction<boolean>>;
}

const TopModal = ({ setModal }: propsType) => {
  const { currentLocation } = useCurrentLocation();
  const handleClick = () => {
    setModal(false);
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
      <li>
        <LinkCss to="/users" onClick={handleClick}>
          <div>MyPage</div>
        </LinkCss>
      </li>
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

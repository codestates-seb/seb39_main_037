/* eslint-disable no-unused-expressions */
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import useCurrentLocation from "Hooks/useCurrentLocation";
import useCurrentUser from "Hooks/useCurrentUser";
import { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import styled, { css } from "styled-components";

const IconDropDown = () => {
  const { currentUser, setCurrentUser } = useCurrentUser();
  const { setCurrentLocation } = useCurrentLocation();
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const ref = useRef<HTMLDivElement>(null);
  const iconHandle = () => {
    setIsOpen(!isOpen);
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
  useEffect(() => {
    const onClick = (e: any) => {
      if (ref.current !== null && !ref?.current?.contains(e.target)) {
        setIsOpen(!isOpen);
      }
    };

    if (isOpen) {
      window.addEventListener("click", onClick);
    }

    return () => {
      window.removeEventListener("click", onClick);
    };
  }, [isOpen]);

  return (
    <DropDownContainer>
      <DropDownButton onClick={iconHandle} ref={ref}>
        <AccountCircleIcon />
      </DropDownButton>
      <DropDown isDropped={isOpen}>
        <LinkCss to="/users">
          <div>{currentUser.nickName}&apos;s 페이지 가기</div>
        </LinkCss>
        <LinkCss to="/" onClick={handleLogout}>
          <div>로그아웃하기</div>
        </LinkCss>
      </DropDown>
    </DropDownContainer>
  );
};
export default IconDropDown;

const DropDownContainer = styled.div`
  position: relative;
  text-align: center;
`;
const DropDownButton = styled.div`
  cursor: pointer;
`;
const DropDown = styled.div<{ isDropped: boolean | any }>`
  padding: 5px;
  font-size: 15px;
  background: #f0f0f0;
  position: absolute;
  top: 60px;
  width: 150px;
  text-align: center;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  border-radius: 3px;
  opacity: 0;
  visibility: hidden;
  transform: translate(-50%, -20px);
  transition: opacity 0.4s ease, transform 0.4s ease, visibility 0.4s;
  z-index: 9;

  ${(props) =>
    props.isDropped &&
    css`
      opacity: 1;
      visibility: visible;
      transform: translate(-50%, 0);
      left: 0%;
    `};
`;

const LinkCss = styled(Link)`
  text-decoration-line: none;
  color: var(--font--color);
  > div {
    margin: 5px;
    :hover {
      opacity: 0.5;
    }
  }
`;

import { NavLink, useLocation } from "react-router-dom";
import styled from "styled-components";

interface IUserNavDataprops {
  link: string;
  text: string;
}

const UserNavItem = ({ link, text }: IUserNavDataprops) => {
  const location = useLocation();
  return link === "/users" ? (
    <NavLinkCss to={link}>
      <TextList
        className={location.pathname === `${link}` ? "active" : undefined}
      >
        <div>{text}</div>
      </TextList>
    </NavLinkCss>
  ) : (
    <NavLinkCss to={link}>
      <TextList
        className={
          location.pathname.indexOf(link) !== -1 ? "active" : undefined
        }
      >
        <div>{text}</div>
      </TextList>
    </NavLinkCss>
  );
};

const NavLinkCss = styled(NavLink)`
  text-decoration: none;
  color: var(--font-color);
  width: 200px;

  .active {
    font-weight: bold;
    background: var(--main-yellow);
  }
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    width: 150px;
  }
`;

const TextList = styled.li`
  list-style: none;
  border-radius: 10px;
  padding-left: 10px;
  background-color: var(--main-yellow-30);
  &:hover {
    font-weight: bold;
  }
  > div {
    padding: 10px 0;
  }
`;

export default UserNavItem;

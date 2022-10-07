import { ReactComponent as LogoIcon } from "Media/Image/logo/foodrecoLogo.svg";
import { Link } from "react-router-dom";
import styled from "styled-components";

const Footer = () => {
  return (
    <FooterContainer>
      <UlContainer>
        <li>
          <LogoIconDiv>
            <LinkCss to="/">
              <LogoIcon />
            </LinkCss>
          </LogoIconDiv>
        </li>
        <li>
          <div>
            <p>FE: 남진혁, 박지영</p>
            <p>BE: 김지나, 임찬일</p>
          </div>
        </li>
        <li>
          <CopyrightContainer>
            <p>Copyright @ 2022 Food-Reco</p>
          </CopyrightContainer>
        </li>
      </UlContainer>
    </FooterContainer>
  );
};

const FooterContainer = styled.footer`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  background-color: var(--footer-background);
  height: 300px;
  color: var(--footer-color);
  font-size: 23px;
  z-index: 1000;
  top: 60px;
  width: 100%;
`;

const UlContainer = styled.ul`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
`;

const CopyrightContainer = styled.div``;

const LogoIconDiv = styled.div`
  > a {
    & :hover {
      opacity: 0.5;
    }
    svg {
      width: 100px;
      height: 60px;
    }
  }
`;

const LinkCss = styled(Link)`
  text-decoration-line: none;
  color: var(--main-yellow);
`;

export default Footer;

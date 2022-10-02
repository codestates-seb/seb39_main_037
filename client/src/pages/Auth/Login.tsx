import { Facebook, GitHub, Google } from "@mui/icons-material";
import AccountForm from "Components/Common/AccountForm";
import { SquareButtonForm as ButtonForm } from "Components/Common/SquareButtonForm";
import { Link, useLocation } from "react-router-dom";
import styled from "styled-components";

const Login = () => {
  const location = useLocation();
  const { email } = location.state || "";
  return (
    <LoginForm>
      <ButtonForm
        widthStyle="30vw"
        buttonColor="var(--google-background)"
        color="black"
        title=" Login with Google"
        icon={<Google />}
      />
      <ButtonForm
        widthStyle="30vw"
        buttonColor="var(--naver-background)"
        color="white"
        title=" Login with Naver"
        icon={
          <img
            src="https://ssl.pstatic.net/sstatic/search/favicon/favicon_191118_pc.ico"
            alt=""
            width="60%"
            height="60%"
          />
        }
      />
      <ButtonForm
        widthStyle="30vw"
        buttonColor="var( --facebook-background)"
        color="white"
        title=" Login with FaceBook"
        icon={<Facebook />}
      />
      <AccountForm title="로그인" email={email} />
      <LinkToLogin>
        <>
          <LinkStyle to="/account-recovery">비밀번호 찾기</LinkStyle>
          <LinkStyle to="/signup">회원가입 하러가기</LinkStyle>
        </>
      </LinkToLogin>
    </LoginForm>
  );
};

export default Login;

const LoginForm = styled.div`
  width: 100vw;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
`;
const LinkToLogin = styled.div`
  width: 30%;
  padding: 1vw 0;
  display: flex;
  justify-content: space-between;
  text-align: center;
  font-size: 10pt;
`;
const LinkStyle = styled(Link)`
  text-decoration: none;
  color: var(--green-color);
  > a {
    text-decoration: none;
  }
`;

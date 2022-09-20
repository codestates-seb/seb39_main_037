import { Facebook, GitHub, Google } from "@mui/icons-material";
import AccountForm from "Components/Common/AccountForm";
import { SquareButtonForm as ButtonForm } from "Components/Common/SquareButtonForm";
import { Link } from "react-router-dom";
import styled from "styled-components";

const Login = () => {
  return (
    <LoginForm>
      <div>로고</div>
      <ButtonForm
        widthStyle="30vw"
        buttonColor="white"
        color="black"
        title=" Login with Google"
        icon={<Google />}
      />
      <ButtonForm
        widthStyle="30vw"
        buttonColor="black"
        color="white"
        title=" Login with GitHub"
        icon={<GitHub />}
      />
      <ButtonForm
        widthStyle="30vw"
        buttonColor="#314A86"
        color="white"
        title=" Login with FaceBook"
        icon={<Facebook />}
      />
      <AccountForm title="로그인" />
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

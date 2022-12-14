import { Facebook, GitHub, Google } from "@mui/icons-material";
import AccountForm from "Components/Common/AccountForm";
import { SquareButtonForm as ButtonForm } from "Components/Common/Button/SquareButtonForm";
import { Link } from "react-router-dom";
import styled from "styled-components";

const Signup = () => {
  return (
    <SignupForm>
      <ButtonForm
        widthStyle="30vw"
        buttonColor="var(--google-background)"
        color="black"
        title=" SignUp with Google"
        icon={<Google />}
      />
      <ButtonForm
        widthStyle="30vw"
        buttonColor="var(--naver-background)"
        color="white"
        title=" Signup with Naver"
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
        buttonColor="var(--facebook-background)"
        color="white"
        title=" SignUp with FaceBook"
        icon={<Facebook />}
      />
      <AccountForm title="회원가입" />
      <LinkToLogin>
        계정이 있나요?&nbsp;
        <LinkStyle to="/login"> 로그인</LinkStyle>
      </LinkToLogin>
    </SignupForm>
  );
};

export default Signup;

const SignupForm = styled.div`
  width: 100vw;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
`;
const LinkToLogin = styled.div`
  padding: 1vw 0;
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

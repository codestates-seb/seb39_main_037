import RecoveryForm from "Components/Common/RecoveryForm";
import styled from "styled-components";

const EmailVerify = () => {
  return (
    <EmailVerifyWrapper>
      <RecoveryForm title="인증확인" />
    </EmailVerifyWrapper>
  );
};

export default EmailVerify;

const EmailVerifyWrapper = styled.div`
  width: 100vw;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
`;

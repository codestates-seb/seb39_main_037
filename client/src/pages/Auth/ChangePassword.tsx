import RecoveryForm from "Components/Common/RecoveryForm";
import styled from "styled-components";

const ChangePassword = () => {
  return (
    <ChangePasswordWrapper>
      <RecoveryForm title="비밀번호변경" />
    </ChangePasswordWrapper>
  );
};

export default ChangePassword;
const ChangePasswordWrapper = styled.div`
  width: 100vw;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
`;

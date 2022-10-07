import RecoveryForm from "Components/Common/RecoveryForm";
import styled from "styled-components";

const AccountRecovery = () => {
  return (
    <AccountRecoveryWrapper>
      <RecoveryForm title="이메일복구" />
    </AccountRecoveryWrapper>
  );
};

export default AccountRecovery;

const AccountRecoveryWrapper = styled.div`
  width: 100vw;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
`;

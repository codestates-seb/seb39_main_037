import UserContent from "Components/Common/UserContent";
import styled from "styled-components";

import UsersNav from "./UserNav";

const UsersComments = () => {
  return (
    <Container>
      <UsersNav />
      <UserContent />
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  width: 100%;
  min-height: 100vh;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: flex;
    flex-direction: column;
  }
`;

export default UsersComments;

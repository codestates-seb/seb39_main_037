import UserContent from "Components/Common/UserContent";
import { useUsers } from "Hooks/Api/Users";
import { useEffect, useState } from "react";
import styled from "styled-components";

import UsersNav from "./UserNav";

const UsersReview = () => {
  const [menu, setMenu] = useState<string[]>([]);
  const { getUsersReview } = useUsers();
  // const [user, setUser] = useState<IUserInfo>();
  // async function getData() {
  //   await getUsersReview(sdf).then((res) => {
  //     console.log(sdf);
  //     // setUser(res);
  //   });
  // }

  // useEffect(() => {
  //   getData();
  // }, []);

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

export default UsersReview;

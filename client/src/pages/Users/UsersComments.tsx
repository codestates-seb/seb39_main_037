import UserContent from "Components/Common/UserContent";
import { useUsers } from "Hooks/Api/Users";
import { useEffect, useState } from "react";
import styled from "styled-components";
import { IPageObj } from "Types";

import UsersNav from "./UserNav";

const UsersComments = () => {
  const { getUsersComments } = useUsers();

  const [data, setData] = useState<object[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [pageInfo, setPageInfo] = useState<IPageObj>();

  async function getData() {
    await getUsersComments({
      page: currentPage,
    }).then((r: any) => {
      // console.log(r);
      setPageInfo(r.pageInfo);
      setData(r.data);
    });
  }
  useEffect(() => {
    getData();
  }, []);
  console.log(data);
  return (
    <Container>
      <UsersNav />
      <UserContent data={data} />
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

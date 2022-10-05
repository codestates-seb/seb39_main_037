import { ReactComponent as BasicUserImg } from "Asset/BasicUserImg.svg";
import { CancelFinishButton } from "Components/Common/Button/CancelFinishButton";
import UserButton from "Components/Common/Button/UserButton";
import Loading from "Components/Common/Loading";
import { useUsers } from "Hooks/Api/Users";
import React, { useEffect, useState } from "react";
import styled from "styled-components";

import { MockupData } from "./MockupData";
import UsersNav from "./UserNav";

interface IUserInfo {
  email: string;
  nickName: string;
  profile: string | null;
  userId: number;
  userName: string;
}

const Users = () => {
  const [idOpen, setidOpen] = useState<boolean>(false);
  const [passwordOpen, setpasswordOpen] = useState<boolean>(false);
  const [nicknameOpen, setnicknameOpen] = useState<boolean>(false);
  const { getUsers } = useUsers();
  const [user, setUser] = useState<IUserInfo>();

  async function getData() {
    await getUsers().then((res) => {
      setUser(res);
    });
  }

  useEffect(() => {
    getData();
  }, []);

  const basicProfileImg = (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation();
  };

  const clickButton = (e: React.MouseEvent<HTMLElement>) => {
    const target = e.target as HTMLInputElement;

    if (target.innerText === "사진 변경") {
      e.stopPropagation();
      setidOpen(!idOpen);
    }
    if (target.innerText === "비밀번호 변경") {
      e.stopPropagation();
      setpasswordOpen(!passwordOpen);
    }
    if (target.innerText === "닉네임 변경") {
      e.stopPropagation();
      setnicknameOpen(!nicknameOpen);
    }
  };

  const closeButton = (e: React.MouseEvent<HTMLElement>) => {
    const target = e.target as HTMLInputElement;

    if (target.id === "id") {
      e.stopPropagation();
      setidOpen(!idOpen);
    }
    if (target.id === "password") {
      e.stopPropagation();
      setpasswordOpen(!passwordOpen);
    }
    if (target.id === "nickname") {
      e.stopPropagation();
      setnicknameOpen(!nicknameOpen);
    }
  };

  if (!user) {
    return <Loading />;
  }

  return (
    <Container>
      <UsersNav />
      <UsersContent>
        <li>
          <ContentFirst>사진</ContentFirst>
          <ContentSecond>
            {user.profile === null ? (
              <BasicUserImgStyled />
            ) : (
              <UserImg src={MockupData.user_img} alt="프로필 사진" />
            )}
            <div>사진을 등록해주세요</div>
            {/* <div>{user.profile}</div> */}
            <div>
              {idOpen === true && (
                <>
                  <ButtonStyle type="button">사진 선택</ButtonStyle>
                  <ButtonStyle type="button">기본 이미지</ButtonStyle>
                </>
              )}
            </div>
          </ContentSecond>
          <div>
            {idOpen === true ? (
              <div>
                <CancelFinishButton onClick={closeButton} id="id" />
              </div>
            ) : (
              <UserButton text="사진" onClick={clickButton} />
            )}
          </div>
        </li>

        <li>
          <ContentFirst>비밀번호</ContentFirst>
          {passwordOpen === true ? (
            <ContentSecond>
              <div>
                <div>현재 비밀번호</div>
                <input />
              </div>
              <div>
                <div>신규 비밀번호</div>
                <input />
              </div>
              <div>
                <div>신규 비밀번호 재입력</div>
                <input />
              </div>
            </ContentSecond>
          ) : (
            <ContentSecond>********</ContentSecond>
          )}
          <div>
            {passwordOpen === true ? (
              <CancelFinishButton onClick={closeButton} id="password" />
            ) : (
              <UserButton text="비밀번호" onClick={clickButton} />
            )}
          </div>
        </li>
        <li>
          <ContentFirst>닉네임</ContentFirst>
          {nicknameOpen === true ? (
            <ContentSecond>
              <ul>
                <li>중복되는 닉네임은 사용 불가합니다.</li>
                <li>최대 길이는 15자 입니다.</li>
                <li>특수문자는 사용 불가합니다.</li>
              </ul>
              <div>
                <input placeholder="닉네임 입력(최대 15자)" />
              </div>
            </ContentSecond>
          ) : (
            <ContentSecond>{user.nickName}</ContentSecond>
          )}
          <div>
            {nicknameOpen === true ? (
              <CancelFinishButton onClick={closeButton} id="nickname" />
            ) : (
              <UserButton text="닉네임" onClick={clickButton} />
            )}
          </div>
        </li>
        <li>
          <ContentFirst>이메일</ContentFirst>
          <ContentSecond>{user.email}</ContentSecond>
        </li>
      </UsersContent>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  width: 100%;
  min-height: 100vh;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    flex-direction: column;
  }
`;

const UsersContent = styled.section`
  flex: 1;
  padding: 2rem;
  max-width: 1000px;

  > li {
    list-style: none;
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    padding: 2rem 2rem;

    border-bottom: 4px solid rgb(247, 247, 247);
    > div:nth-child(2) {
      flex: 1;
    }

    @media screen and (max-width: ${({ theme }) => theme.breakPoints.mobile}) {
      /* background-color: red; */
      flex-direction: column;
      gap: 1rem;
    }
  }
`;

const ContentFirst = styled.div`
  min-width: 150px;
`;

const ContentSecond = styled.div`
  width: 300px;
`;

const ButtonStyle = styled.button`
  border: 1px solid #e5e5e5;
  background-color: #ffffff;
  min-width: 100px;
  border-radius: 3px;
`;

const UserImg = styled.img`
  width: 80px;
  height: 80px;
`;

const BasicUserImgStyled = styled(BasicUserImg)`
  width: 80px;
  height: 80px;
`;

export default Users;

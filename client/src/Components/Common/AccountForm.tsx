import { HelpRounded } from "@mui/icons-material";
import { Checkbox, OutlinedInput } from "@mui/material";
import { Modal } from "Components/Common/Modal/Modal";
import { ModalContent } from "Components/Common/Modal/ModalContent";
import { SquareButtonForm } from "Components/Common/SquareButtonForm";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";

interface IAccountProps {
  title: string;
  id?: number;
  nickName?: string;
  email?: string;
  password?: string;
}

const AccountForm = ({
  title,
  id,
  nickName = "",
  email = "",
  password = "",
}: IAccountProps) => {
  const navigate = useNavigate();
  const isSignup = title === "회원가입"; // title이 회원가입 IsLogin 이 true
  const isLogin = title === "로그인"; // title이 로그인이면 IsLogin 이 true

  // 개인정보 수집 및 이용 동의 여부
  const [checked, setChecked] = useState<boolean>(false);
  const [isOpenAgreeModal, setIsOpenAgreeModal] = useState<boolean>(false);
  const [form, setForm] = useState({
    idForm: id,
    nickNameForm: nickName,
    emailForm: email,
    passwordForm: password,
  }); // form 을 저장할 state들
  const { idForm, nickNameForm, emailForm, passwordForm } = form; // 구조분해할당
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  }; // Input 내용 변경시

  const handleSubmit = () => {
    if (isSignup) {
      if (window.confirm("회원가입하시겠습니까?")) {
        navigate("/login");
      }
    } else if (isLogin) {
      if (window.confirm("로그인하시겠습니까?")) {
        navigate("/");
      }
    }
  };

  return (
    <AccountFormWrapper>
      {" "}
      {isSignup && (
        <>
          <InputLabel>
            <span>아이디를 입력하세요</span>
            <InputField
              type="text"
              name="idFrom"
              value={idForm}
              onChange={onChange}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
          <InputLabel>
            <span>닉네임을 입력하세요</span>
            <InputField
              type="text"
              name="nickNameForm"
              value={nickNameForm}
              onChange={onChange}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
          <InputLabel>
            <span>이메일을 입력하세요</span>
            <InputField
              type="email"
              name="emailForm"
              value={emailForm}
              onChange={onChange}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
          <InputLabel>
            <span> 비밀번호를 입력하세요</span>
            <InputField
              type="password"
              name="passwordForm"
              value={passwordForm}
              onChange={onChange}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
        </>
      )}
      {isLogin && (
        <>
          <InputLabel>
            <span>아이디를 입력하세요</span>
            <InputField
              type="text"
              name="idFrom"
              value={idForm}
              onChange={onChange}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
          <InputLabel>
            <span>비밀번호를 입력하세요</span>
            <InputField
              type="password"
              name="passwordForm"
              value={passwordForm}
              onChange={onChange}
              size="small"
              inputProps={{
                style: { fontSize: 15, verticalAlign: "middle" },
              }}
              required
            />
          </InputLabel>
        </>
      )}
      {isSignup && (
        <InputLabel>
          <Checkbox
            checked={checked}
            onChange={(e) => setChecked(e.target.checked)}
            inputProps={{ "aria-label": "controlled" }}
          />
          <span>
            개인정보 수집 및 이용 동의
            {!isOpenAgreeModal ? (
              <HelpRounded
                onClick={() => {
                  setIsOpenAgreeModal((prev) => !prev);
                }}
                fontSize="small"
              />
            ) : (
              <Modal
                show={isOpenAgreeModal}
                onClose={() => setIsOpenAgreeModal((prev) => !prev)}
              >
                <ModalContent
                  onClose={() => setIsOpenAgreeModal((prev) => !prev)}
                >
                  <ul>
                    <li>
                      개인정보 수집 및 이용 목적: 본인 확인과 서비스 제공을
                      위함.
                    </li>
                    <li>개인정보 보유 및 이용기간: 회원 탈퇴시 까지</li>
                  </ul>
                </ModalContent>
              </Modal>
            )}
          </span>
        </InputLabel>
      )}
      <SquareButtonForm
        title={title}
        onClick={handleSubmit}
        widthStyle="100%"
      />
    </AccountFormWrapper>
  );
};

export default AccountForm;

const AccountFormWrapper = styled.div`
  position: relative;
  width: 30vw;
  background-color: white;
  padding: 10px;
  border-radius: 5px;
`;
const InputLabel = styled.div`
  width: 100%;
  padding: 0.5vw 0;
  font-size: 10pt;
  margin-bottom: 1vw;
`;
const InputField = styled(OutlinedInput)`
  width: 100%;
  font-size: 10pt;
`;

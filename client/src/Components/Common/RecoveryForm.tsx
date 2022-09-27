import { LockReset } from "@mui/icons-material";
import { OutlinedInput } from "@mui/material";
import PinInput from "Components/Common/PinInput";
import { SquareButtonForm } from "Components/Common/SquareButtonForm";
import React, { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const PIN_LENGTH = 6;
interface IRecoveryProps {
  title: string;
  email?: string;
  password?: string;
  passwordConfirm?: string;
}
const RecoveryForm = ({
  title,
  email = "",
  password = "",
  passwordConfirm = "",
}: IRecoveryProps) => {
  const navigate = useNavigate();
  const isRecovery = title === "이메일복구";
  const isVertify = title === "인증확인";
  const isChange = title === "비밀번호변경";

  const [form, setForm] = useState({
    emailForm: email,
    passwordForm: password,
    passwordConfirmForm: passwordConfirm,
  }); // input form
  const [pin, setPin] = useState<Array<number | undefined>>(
    new Array(PIN_LENGTH),
  );
  const [validationResult, setValidationResult] = useState<boolean | undefined>(
    undefined,
  );
  const [validationMessage, setValidationMessage] = useState<
    string | undefined
  >(undefined);
  const [isValidating, setIsValidating] = useState(false);
  const { emailForm, passwordForm, passwordConfirmForm } = form; // 구조분해할당

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  }; // Input 내용 변경시
  const handleSubmit = () => {
    if (isRecovery) {
      if (window.confirm("메일을 보냈습니다")) {
        navigate("/email-verify");
      }
    } else if (isVertify) {
      if (window.confirm("복구 pin 입력")) {
        alert(pin);
        navigate("/change-password");
      }
    } else if (isChange) {
      if (window.confirm("비밀번호 변경")) {
        navigate("/");
      }
    }
  };
  const onPinChanged = (pinEntry: number | undefined, index: number) => {
    const newPin = [...pin];
    newPin[index] = pinEntry;
    setPin(newPin);
  };

  return (
    <RecoveryWrapper>
      <LockReset />
      <Description />
      {isRecovery && (
        <>
          <div className="title">비밀번호를 잊어버리셨나요?</div>
          <div>
            아레에 이메일 주소를 입력해주시면 복구 이메일을 보내드립니다.
          </div>
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
        </>
      )}
      {isVertify && (
        <>
          <div className="title">이메일인증</div>
          <div>이메일로 받은 복구 번호를 입력해주세요</div>
          <PinInput
            isValidating={isValidating}
            validationMessage={validationMessage}
            validationResult={validationResult}
            onPinChanged={onPinChanged}
            pin={pin}
            pinLength={PIN_LENGTH}
          />
        </>
      )}
      {isChange && (
        <>
          <div className="title">새 비밀번호를 입력해주세요</div>
          <div>아래에 새로운 비밀번호를 입력해주세요</div>
          <>
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
            <InputLabel>
              <span>비밀번호를 한번 더 입력하세요</span>
              <InputField
                type="password"
                name="passwordConfirmForm"
                value={passwordConfirmForm}
                onChange={onChange}
                size="small"
                inputProps={{
                  style: { fontSize: 15, verticalAlign: "middle" },
                }}
                required
              />
            </InputLabel>
          </>
        </>
      )}
      <SquareButtonForm
        title={title}
        onClick={handleSubmit}
        widthStyle="100%"
      />
    </RecoveryWrapper>
  );
};

export default RecoveryForm;

const RecoveryWrapper = styled.div``;
const Description = styled.div``;

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

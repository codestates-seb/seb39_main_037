import React, { useEffect, useRef } from "react";
import styled from "styled-components";

interface IValidationResultParagraphProps {
  // 결과확인
  isCorrect?: boolean;
}

interface IPinInputProps {
  pin: Array<number | undefined>;
  onPinChanged: (pinEntry: number | undefined, index: number) => void;
  pinLength: number;
  validationMessage: string | undefined;
  validationResult: boolean | undefined;
  isValidating: boolean;
}

const PIN_MIN_VALUE = 0;
const PIN_MAX_VALUE = 9;
const BACKSPACE_KEY = "Backspace";

const PinInput: React.FC<IPinInputProps> = ({
  pinLength,
  pin,
  onPinChanged,
  validationMessage,
  validationResult,
  isValidating,
}) => {
  const inputRefs = useRef<HTMLInputElement[]>([]);

  const removeValuesFromArray = (valuesArray: string[], value: string) => {
    const valueIndex = valuesArray.findIndex((entry) => entry === value);
    if (valueIndex === -1) {
      return;
    }
    valuesArray.splice(valueIndex, 1);
  };

  const changePinFocus = (pinIndex: number) => {
    const ref = inputRefs.current[pinIndex];
    if (ref) {
      ref.focus();
    }
  };

  useEffect(() => {
    changePinFocus(0);
  }, [isValidating]);

  const onChange = (
    event: React.ChangeEvent<HTMLInputElement>,
    index: number,
  ) => {
    const previousValue = event.target.defaultValue;
    const valuesArray = event.target.value.split("");
    removeValuesFromArray(valuesArray, previousValue);
    const value = valuesArray.pop();
    if (!value) {
      return;
    }
    const pinNumber = Number(value.trim());
    if (Number.isNaN(pinNumber) || value.length === 0) {
      return;
    }

    if (pinNumber >= PIN_MIN_VALUE && pinNumber <= PIN_MAX_VALUE) {
      onPinChanged(pinNumber, index);
      if (index < pinLength - 1) {
        changePinFocus(index + 1);
      }
    }
  };

  const onKeyDown = (
    event: React.KeyboardEvent<HTMLInputElement>,
    index: number,
  ) => {
    const keyboardKeyCode = event.nativeEvent.code;
    if (keyboardKeyCode !== BACKSPACE_KEY) {
      return;
    }

    if (pin[index] === undefined) {
      changePinFocus(index - 1);
    } else {
      onPinChanged(undefined, index);
    }
  };

  return (
    <>
      <div>
        {Array.from({ length: pinLength }, (_, index) => (
          <StyledPinInput
            disabled={isValidating}
            isCorrect={validationResult}
            onKeyDown={(event) => onKeyDown(event, index)}
            key={index}
            ref={(el) => {
              if (el) {
                inputRefs.current[index] = el;
              }
            }}
            onChange={(event) => onChange(event, index)}
            value={pin[index] || ""}
          />
        ))}
      </div>
      <ValidationResultParagraph isCorrect={validationResult}>
        {validationMessage}
      </ValidationResultParagraph>
    </>
  );
};

export default PinInput;

const StyledPinInput = styled.input<IValidationResultParagraphProps>`
  width: 35px;
  height: 35px;
  border: 2px solid;
  font-size: 12px;
  text-align: center;
  margin: 8px;
  font-family: "dotsfont";
  border-color: ${(props) => {
    switch (props.isCorrect) {
      case true:
        return "green";
      case false:
        return "red";
      default:
        return "#c3c3c3";
    }
  }};
`;

const ValidationResultParagraph = styled.p<IValidationResultParagraphProps>`
  color: ${(props) => (props.isCorrect ? "green" : "red")};
`;

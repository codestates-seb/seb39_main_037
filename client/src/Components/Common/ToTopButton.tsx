import { ArrowDropUp } from "@mui/icons-material";
import { useEffect, useState } from "react";
import styled from "styled-components";

const ToTopButton = () => {
  const [showButton, setShowButton] = useState<boolean>(false);

  useEffect(() => {
    window.addEventListener("scroll", () => {
      if (window.pageYOffset > 300) {
        // 스크롤이 되면 버튼 보이기
        setShowButton(true);
      } else {
        setShowButton(false);
      }
    });
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <>
      {showButton && (
        <Button onClick={scrollToTop}>
          <ArrowDropUp fontSize="large" />
          <ButtonText>TOP</ButtonText>
        </Button>
      )}
    </>
  );
};

export default ToTopButton;
const Button = styled.div`
  position: fixed;
  bottom: 3vw;
  right: 3.5vw;
  color: black;
  opacity: 0.7;
  cursor: pointer;
`;

const ButtonText = styled.div`
  text-align: center;
  font-size: 11pt;
`;

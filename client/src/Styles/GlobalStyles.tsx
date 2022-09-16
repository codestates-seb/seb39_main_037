import { createGlobalStyle } from "styled-components";
import { reset } from "styled-reset";

const GlobalStyle = createGlobalStyle`
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

${reset}

html {
  scroll-behavior: smooth;
}

body {
    font-family: 'Do Hyeon', sans-serif;
};

* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
};

`;

export default GlobalStyle;

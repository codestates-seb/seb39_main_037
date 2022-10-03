import { createGlobalStyle } from "styled-components";
import { reset } from "styled-reset";

import variables from "./GlobalVariables";

const GlobalStyle = createGlobalStyle`
${reset}

:root {
  ${variables}
  font-family: 'Nanum Gothic', sans-serif;
}

html {
  scroll-behavior: smooth;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
};

`;

export default GlobalStyle;

import App from "App";
import ReactDOM from "react-dom/client";
import { RecoilRoot } from "recoil";
import GlobalStyle from "Styles/GlobalStyles";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <RecoilRoot>
    <GlobalStyle />
    <App />
  </RecoilRoot>,
);

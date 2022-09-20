import Footer from "Components/Layout/Footer/Footer";
import Header from "Components/Layout/Header/Header";
import Admin from "pages/Admin/Admin";
import AccountRecovery from "pages/Auth/AccountRecovery";
import ChangePassword from "pages/Auth/ChangePassword";
import EmailVerify from "pages/Auth/EmailVerify";
import Login from "pages/Auth/Login";
import Signup from "pages/Auth/Signup";
import Main from "pages/Main/Main";
import Random from "pages/Random/Random";
import RandomMap from "pages/Random/RandomMap";
import LocationRegister from "pages/Register/LocationRegister";
import ReviewRegister from "pages/Register/ReviewRegister";
import Review from "pages/Review/Review";
import ReviewDetail from "pages/Review/ReviewDetail";
import StoreReview from "pages/Review/StoreReview";
import Users from "pages/Users/Users";
import UsersComments from "pages/Users/UsersComments";
import UsersLike from "pages/Users/UsersLike";
import UsersReview from "pages/Users/UsersReview";
import NotFound from "pages/Utils/NotFound";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import styled from "styled-components";

const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <Container>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/account-recovery" element={<AccountRecovery />} />
          <Route path="/change-password" element={<ChangePassword />} />
          <Route path="/email-verify" element={<EmailVerify />} />
          <Route path="/random-recommend" element={<Random />} />
          <Route
            path="/random-recommend/map/:food_id"
            element={<RandomMap />}
          />
          <Route path="/location" element={<LocationRegister />} />
          <Route path="/register" element={<ReviewRegister />} />
          <Route path="/review" element={<Review />} />
          <Route path="/review/:restaurant" element={<StoreReview />} />
          <Route
            path="/review/:restaurant/:review_id"
            element={<ReviewDetail />}
          />
          <Route path="/users" element={<Users />} />
          <Route path="/users/comment" element={<UsersComments />} />
          <Route path="/users/like" element={<UsersLike />} />
          <Route path="/users/review" element={<UsersReview />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/*" element={<NotFound />} />
        </Routes>
      </Container>
      <Footer />
    </BrowserRouter>
  );
};

const Container = styled.div`
  width: 100%;
  background: none;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  position: relative;
  top: 60px;
`;

export default App;

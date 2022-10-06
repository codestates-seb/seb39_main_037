import TeamCard from "Components/TeamCard";
import Lim from "Media/Image/ci.png";
import Nam from "Media/Image/jh.png";
import Kim from "Media/Image/jn.png";
import Park from "Media/Image/jy.png";
import foodReco from "Media/Image/logo/foodrecoImg.png";
import mapReco from "Media/Image/mapReco.png";
import randomImg from "Media/Image/random.png";
import reviewImg from "Media/Image/review.png";
import React from "react";
import styled from "styled-components";

const Main = () => {
  const lazyLoad = (target: Element) => {
    const io = new IntersectionObserver((entries, observer) => {
      entries.forEach((entry) => {
        // 관찰 대상이 viewport 안에 들어온 경우 image 로드
        if (entry.isIntersecting) {
          const img = entry.target; // DOM
          // data-lazy attribute 가져와서
          const src = img.getAttribute("data-lazy") || "";
          // Dom에 src 추가
          img.setAttribute("src", src);
          observer.disconnect();
        }
      });
    });
    io.observe(target);
  };

  React.useEffect(() => {
    const imgs = document.querySelectorAll("img");
    imgs.forEach(lazyLoad);
  }, []);

  return (
    <MainWrapper>
      <OddWrapper>
        <Description>
          <div>내가 뭘 먹고 싶은지 모를 때! 🤔</div>
          <div>아무거나 먹기는 싫을 때! 🤨</div>
          <div>선택지가 너무 많아 고르기 힘들 때! 🤕</div>
          <div>
            <span>푸드레코</span>가 도와줄게요! 🍱
          </div>
        </Description>
        <ImgWrapper>
          <img data-lazy={foodReco} alt="" />
        </ImgWrapper>
      </OddWrapper>
      <EvenWrapper>
        <CenterWrapper>
          <TitleWrapper>
            <span>랜덤추천</span>
          </TitleWrapper>
          <Info>
            <div>오늘 뭐먹지?</div>
            <div>매일 무엇을 먹을까 고민하는 우리들을 위해</div>
            <div>메뉴를 추천해줘요 🥐</div>
          </Info>
        </CenterWrapper>
        <ImgWrapper>
          <img data-lazy={randomImg} alt="" />
        </ImgWrapper>
      </EvenWrapper>
      <OddWrapper>
        <CenterWrapper>
          <TitleWrapper>
            <span>음식점추천</span>
          </TitleWrapper>
          <Info>
            <div>추천 시스템을 통해 메뉴를 선택했다면 </div>
            <div>내 위치에 따른 음식점을 보여줘요 🍛</div>
          </Info>
        </CenterWrapper>
        <ImgWrapper>
          <img data-lazy={mapReco} alt="" />
        </ImgWrapper>
      </OddWrapper>
      <EvenWrapper>
        <CenterWrapper>
          <TitleWrapper>
            <span>리뷰</span>
          </TitleWrapper>
          <Info>
            <div>나와 같은 지역의 다른 누군가에게</div>
            <div> 맛집을 추천해줄 수 있는 리뷰도 작성해요 🍙</div>
          </Info>
        </CenterWrapper>
        <ImgWrapper>
          <img data-lazy={reviewImg} alt="" />
        </ImgWrapper>
      </EvenWrapper>
      <OddWrapper>
        <CenterWrapper>
          <TitleWrapper>
            <span>foodReco 팀 소개</span>
          </TitleWrapper>
          <TeamCard
            img={Park}
            name="박지영"
            featureTag={["auth", "main", "review", "locationStore", "marker"]}
            intro="무엇을 먹을지 모르겠을 땐? 같이 푸드레코해요!"
            github="https://github.com/JIYEONGSTAR"
            blog="https://jiyeongstar.tistory.com"
            email="jiyeongstar@gmail.com"
          />
          <TeamCard
            img={Nam}
            name="남진혁"
            featureTag={["random", "mypage", "반응형"]}
            intro="아무거나 먹기 싫을 땐? 푸드레코가 도와줄게요!"
            github="https://github.com/jin92926"
            blog="https://jin92926.tistory.com"
            email="jin92926@gmail.com"
          />
          <TeamCard
            img={Kim}
            name="김지나"
            featureTag={["review", "comment", "Q&A", "restaurant"]}
            intro="맛집 찾기는 푸드레코!"
            github="https://github.com/SEBBE39JINAKIM"
            blog="https://sebbe39jinakim.github.io"
            email="petitjina1999@gmail.com"
          />
          <TeamCard
            img={Lim}
            name="임찬일"
            featureTag={["Spring Security", "food", "배포"]}
            intro="언제나 함께 하고 싶은 개발자가 되겠습니다!"
            github="https://github.com/Akkuchan"
            blog="https://github.com/Akkuchan"
            email="pohd8494@gmail.com"
          />
        </CenterWrapper>
      </OddWrapper>
    </MainWrapper>
  );
};

const MainWrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const OddWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
`;
const EvenWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  min-height: 100vh;
  background-color: var(--yellow-background);
`;

const Description = styled.div`
  width: 100%;
  font-size: 24px;
  margin-top: 50px;
  & div {
    width: 100%;
    display: flex;
    justify-content: center;
    margin: 1.2rem 0;
  }
  & span {
    color: var(--main-yellow);
  }
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    font-size: 19px;
  }
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.mobile}) {
    font-size: 16px;
  }
`;

const ImgWrapper = styled.div`
  display: flex;
  justify-content: center;
  margin: 50px 0 100px 0;
  max-width: 100%;
  & img {
    width: 31.25rem;
    height: 28.875rem;
    @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
      width: 25rem;
      height: 23.125rem;
    }
    @media screen and (max-width: ${({ theme }) => theme.breakPoints.mobile}) {
      width: 12.5rem;
      height: 11.563rem;
    }
  }
`;

const CenterWrapper = styled.div`
  width: 900px;
  margin: 0 auto;
  flex: 1;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    width: 100%;
  }
`;

const TitleWrapper = styled.div`
  width: 100%;
  margin-top: 70px;
  & span {
    font-size: 1.875rem;
    color: var(--main-yellow);
    @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
      font-size: 1.75rem;
      margin-left: 1.25rem;
    }

    @media screen and (max-width: ${({ theme }) => theme.breakPoints.mobile}) {
      font-size: 1.5rem;
      margin-left: 1.25rem;
    }
  }
`;

const Info = styled.div`
  font-size: 1.25rem;
  margin: 2rem 0 3rem 0;
  & div {
    margin: 1.2rem 0;
  }
  & span {
    color: var(--main-yellow);
  }
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    font-size: 1rem;
    margin-left: 1.25rem;
  }
`;

export default Main;

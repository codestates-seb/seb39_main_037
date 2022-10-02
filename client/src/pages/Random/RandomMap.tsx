import KakaoMap from "Components/KakaoMap";
import styled from "styled-components";

const demo = [
  {
    restaurantId: 10,
    restaurantName: "우찌노<b>카레</b>",
    category: "일식>카레",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 강남구 신사동 658 상가동 1층 113호",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 315128,
    mapy: 547582,
    locationId: 2,
    foodTypeName: "일식",
  },
  {
    restaurantId: 7,
    restaurantName: "몽글몽글<b>카레</b>집",
    category: "일식>카레",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 강남구 신사동 535-23 1층",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 313800,
    mapy: 546931,
    locationId: 2,
    foodTypeName: "일식",
  },
  {
    restaurantId: 5,
    restaurantName: "우찌노<b>카레</b>",
    category: "일식>카레",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 강남구 신사동 658 상가동 1층 113호",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 315128,
    mapy: 547582,
    locationId: 2,
    foodTypeName: "일식",
  },
  {
    restaurantId: 2,
    restaurantName: "몽글몽글<b>카레</b>집",
    category: "일식>카레",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 강남구 신사동 535-23 1층",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 313800,
    mapy: 546931,
    locationId: 2,
    foodTypeName: "일식",
  },
];
const RandomMap = () => {
  return (
    <RandomMapWrapper>
      <KakaoMapWrapper className="kakaomap">
        <KakaoMap restaurants={demo} />
      </KakaoMapWrapper>
      <RestaurantWrapper />
    </RandomMapWrapper>
  );
};

export default RandomMap;

const RandomMapWrapper = styled.div`
  display: flex;
  width: 100%;
  min-height: 100vh;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    flex-direction: column;
  }
`;
const KakaoMapWrapper = styled.div`
  flex: 1;
`;
const RestaurantWrapper = styled.div`
  flex: 1;
`;

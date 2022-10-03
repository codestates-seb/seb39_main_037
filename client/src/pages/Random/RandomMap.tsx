import KakaoMap from "Components/KakaoMap";
import RestaurantList from "Components/RestaurantList";
import styled from "styled-components";

const demo = [
  {
    restaurantId: 47,
    restaurantName: "신도림 이도<b>식당</b>",
    category: "한식>닭갈비",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 구로구 신도림동 337 신도림1차푸르지오 2층",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 126.88773232152055,
    mapy: 37.50921749823045,
    locationId: 0,
    foodTypeName: null,
  },
  {
    restaurantId: 46,
    restaurantName: "무한자금성",
    category: "중식>중식당",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 구로구 구로동 212-30",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 126.89412118008596,
    mapy: 37.483993646896366,
    locationId: 0,
    foodTypeName: null,
  },
  {
    restaurantId: 45,
    restaurantName: "문어부인삼교비",
    category: "한식>육류,고기요리>돼지고기구이>문어부인삼교비",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 구로구 신도림동 337",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 126.88753952487176,
    mapy: 37.50925172832515,
    locationId: 0,
    foodTypeName: null,
  },
  {
    restaurantId: 44,
    restaurantName: "신림춘천집 <b>구로</b>디지털 직영점",
    category: "음식점>한식>육류,고기요리>닭요리",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 구로구 구로동 1124-69",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 126.89985309605856,
    mapy: 37.4840560680429,
    locationId: 0,
    foodTypeName: null,
  },
  {
    restaurantId: 43,
    restaurantName: "새벽집 양곱창",
    category: "한식>곱창,막창,양",
    description: null,
    restaurantPhone: null,
    address: "서울특별시 구로구 신도림동 694",
    aveTaste: 0,
    aveFacility: 0,
    avePrice: 0,
    mapx: 126.88404353369323,
    mapy: 37.50778613297942,
    locationId: 0,
    foodTypeName: null,
  },
];
const RandomMap = () => {
  return (
    <RandomMapWrapper>
      <KakaoMapWrapper className="kakaomap">
        <KakaoMap restaurants={demo} />
      </KakaoMapWrapper>
      <RestaurantWrapper>
        <RestaurantList restaurants={demo} />
      </RestaurantWrapper>
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

import Loading from "Components/Common/Loading";
import PaginationForm from "Components/Common/Pagination/PaginationForm";
import KakaoMap from "Components/KakaoMap";
import RestaurantList from "Components/RestaurantList";
import { useRestaurant } from "Hooks/Api/Restaurant/index";
import useCurrentLocation from "Hooks/useCurrentLocation";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { IPageObj, IRestaurant } from "Types";

const RandomMap = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const [pageInfo, setPageInfo] = useState<IPageObj>();
  const url = window.location.href;
  const decodeurl = decodeURI(url);
  const selectedId = decodeurl.slice(decodeurl.lastIndexOf("/") + 1);
  const menuName = decodeurl.split("/")[5];

  const { currentLocation } = useCurrentLocation();
  const { getRestaruantByTitle } = useRestaurant();

  const [data, setData] = useState<object[]>([]);
  const navigate = useNavigate();
  if (currentLocation.locationId === 0) {
    // 로케이션이 저장되어있지 않으면?
    navigate("/location");
  }

  async function getData() {
    const res = await getRestaruantByTitle({
      foodid: selectedId,
      locationId: currentLocation.locationId,
      page: currentPage,
    });
    try {
      setData(res.data);
      setPageInfo(res.pageInfo);
    } catch (err) {
      alert(err);
    }
  }
  useEffect(() => {
    getData();
  }, []);

  if (data.length === 0 || !pageInfo) return <Loading />;
  return (
    <RandomMapWrapper>
      <KakaoMapWrapper className="kakaomap">
        <KakaoMap restaurants={data} />
        <SelectedMenuWrapper>{menuName}</SelectedMenuWrapper>
      </KakaoMapWrapper>
      <InfoWrapper>
        <RestaurantWrapper>
          <RestaurantList restaurants={data} />
        </RestaurantWrapper>
        {pageInfo.totalPages > 1 && (
          <PaginationForm
            activePage={currentPage}
            totalItemsCount={pageInfo.totalElements}
            onChange={(e: any) => {
              setCurrentPage(e);
            }}
          />
        )}
      </InfoWrapper>
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
    justify-content: center;
  }
`;
const KakaoMapWrapper = styled.div`
  flex: 1;
  padding: 2rem;
  /* min-width: 400px; */
  min-height: 500px;
  max-height: 700px;
  display: flex;
  flex-direction: column;
  align-items: center;
  align-content: center;
  position: sticky;
  /* top: 150px; */
  left: 2rem;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    max-height: 30vh;
    position: static;
  }
`;

const SelectedMenuWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 2rem;
  width: 150px;
  height: 50px;
  border-radius: 15px;
  font-weight: 600;
  background-color: #fce205;
`;
const InfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 500px;
  max-height: 700px;
  overflow-y: auto;
`;
const RestaurantWrapper = styled.div`
  flex: 1;
  padding: 2rem;
  margin: 2rem;
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  gap: 3rem;
  min-width: calc(100%-32px);
  flex-direction: column;
  @media screen and (max-width: ${({ theme }) => theme.breakPoints.tablet}) {
    display: none;
  }
`;

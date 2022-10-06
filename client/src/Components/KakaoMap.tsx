import useCurrentRestaurant from "Hooks/useCurrentRestaurant";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { IRestaurant } from "Types";

declare global {
  interface Window {
    kakao: any;
  }
}

const KakaoMap = ({ restaurants }: Array<IRestaurant> | any) => {
  const navigate = useNavigate();
  const { setCurrentRestaurant } = useCurrentRestaurant();
  const [mapLoad, setMapLoad] = useState<boolean>(false);
  useEffect(() => {
    setMapLoad(false);
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.REACT_APP_KAKAOMAP_APPKEY}&autoload=false`;
    script.addEventListener("load", () => setMapLoad(true));
    document.head.appendChild(script);
  }, [restaurants]);
  // 경도x
  const mainLongitude =
    restaurants.reduce((acc: number, cur: IRestaurant) => {
      return acc + cur.mapx;
    }, 0) / restaurants.length;
  // 위도y
  const mainLatitude =
    restaurants.reduce((acc: number, cur: IRestaurant) => {
      return acc + cur.mapy;
    }, 0) / restaurants.length;

  useEffect(() => {
    if (!mapLoad) return;
    console.log("window.kakao", window.kakao);
    window.kakao.maps.load(() => {
      const mapContainer = document.getElementById("map");
      const mapOption = {
        // 지도 중앙 설정
        center: new window.kakao.maps.LatLng(mainLatitude, mainLongitude),
        level: 6,
      };

      // 지도 생성
      const map = new window.kakao.maps.Map(mapContainer, mapOption);

      const restaurantPositions = restaurants.map((r: any) => {
        return {
          title: r.restaurantName, // 가게이름
          latlng: new window.kakao.maps.LatLng(r.mapy, r.mapx), // 위도경도 순서
          restaurantId: r.restaurantId, // 레스토랑아이디
        };
      });
      console.log(restaurantPositions);
      // 마커생성
      restaurantPositions.forEach((r: any) => {
        const marker = new window.kakao.maps.Marker({
          map, // 마커를 표시할 지도
          position: r.latlng, // 마커를 표시할 위치
          title: r.title,
        });

        // 마커를 지도에 표시합니다.
        marker.setMap(map);
        // 마우스오버
        // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
        const iwContent = `<div style="padding:5px;">${r.title}</div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        // 인포윈도우를 생성합니다
        const infowindow = new window.kakao.maps.InfoWindow({
          content: iwContent,
        });

        // 마커에 마우스오버 이벤트를 등록합니다
        window.kakao.maps.event.addListener(marker, "mouseover", () => {
          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
          infowindow.open(map, marker);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        window.kakao.maps.event.addListener(marker, "mouseout", () => {
          // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
          infowindow.close();
        });

        // 마커에 클릭이벤트를 등록합니다
        window.kakao.maps.event.addListener(marker, "click", () => {
          // 클릭시 해당 가게 리뷰 페이지로 이동
          infowindow.open(map, marker);
          setCurrentRestaurant(r); // recoil 설정
          navigate(`/review/${r.restaurantId}`);
        });
      });
    });
  }, [mapLoad]);
  return (
    <Wrapper>
      <div id="map" />
    </Wrapper>
  );
};
export default KakaoMap;

const Wrapper = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
  #map {
    background-color: "red";
    position: absolute;
    width: 100%;
    height: 100%;
  }
`;

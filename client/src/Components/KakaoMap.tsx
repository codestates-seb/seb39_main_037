import React, { useEffect, useState } from "react";
import styled from "styled-components";

interface mapListobj {
  restaurantId: number;
  restaurantName: string;
  category: string;
  description: null | string;
  restaurantPhone: null | string;
  address: string;
  aveTaste: number;
  aveFacility: number;
  avePrice: number;
  mapx: number;
  mapy: number;
  locationId: number;
  foodTypeName: string;
}

declare global {
  interface Window {
    kakao: any;
  }
}

const KakaoMap = ({ restaurants }: any) => {
  const [mapLoad, setMapLoad] = useState<boolean>(false);
  useEffect(() => {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.REACT_APP_KAKAOMAP_APPKEY}&autoload=false`;
    script.addEventListener("load", () => setMapLoad(true));
    document.head.appendChild(script);
  }, []);
  // 경도x
  const mainLongitude =
    restaurants.reduce((acc: number, cur: mapListobj) => {
      return acc + cur.mapx;
    }, 0) / restaurants.length;
  // 위도y
  const mainLatitude =
    restaurants.reduce((acc: number, cur: mapListobj) => {
      return acc + cur.mapy;
    }, 0) / restaurants.length;

  useEffect(() => {
    if (!mapLoad) return;
    console.log("window.kakao", window.kakao);
    window.kakao.maps.load(() => {
      console.log("로드유즈이펙트");
      const mapContainer = document.getElementById("map");
      const mapOption = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 4,
      };
      const map = new window.kakao.maps.Map(mapContainer, mapOption);

      // const restaurantPositions = restaurants.map((r: any) => {
      //   return {
      //     title: r.restaurantName,
      //     latlng: new window.kakao.maps.LatLng(r.mapx, r.mapy),
      //   };
      // });
      // 위치 더미데이터
      const restaurantPositions = [
        {
          title: "카카오",
          latlng: new window.kakao.maps.LatLng(33.450705, 126.570677),
        },
        {
          title: "생태연못",
          latlng: new window.kakao.maps.LatLng(33.450936, 126.569477),
        },
        {
          title: "텃밭",
          latlng: new window.kakao.maps.LatLng(33.450879, 126.56994),
        },
        {
          title: "근린공원",
          latlng: new window.kakao.maps.LatLng(33.451393, 126.570738),
        },
      ];
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
        window.kakao.maps.event.addListener(marker, "mouseover", function () {
          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
          infowindow.open(map, marker);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        window.kakao.maps.event.addListener(marker, "mouseout", function () {
          // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
          infowindow.close();
        });
      });
    });
  }, [mapLoad]);
  return (
    <Wrapper>
      카카오맵
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

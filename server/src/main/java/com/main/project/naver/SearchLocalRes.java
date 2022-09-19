package com.main.project.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRes {

    // 지역 검색 출력 결과를 변수화

    private String lastBuildDate; // 검색 결과를 생성한 시간.
    private int total; //  검색 결과 문서의 총 개수.
    private int start; // 검색 결과 문서 중, 문서의 시작점.
    private int display; // 검색된 검색 결과의 개수.
    private List<SearchLocalItem> items; // JSON 포멧에서는 items 태그를 속성으로 표현된다. 개별 검색 결과이며 title, link, description, address, mapx, mapy를 포함한다.

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem{
        private String RestaurantName;
//        private String homePageLink;  // 식당의 상세 정보가 제공되는 네이버 페이지의 하이퍼텍스트 link.
        private String category; //foodType
        private String RestaurantDescription;  //식당에 대한 설명.
        private String restaurantPhone;  // 빈 문자열 반환. 과거에 제공되던 항목이라 하위 호환성을 위해 존재한다.
        private String address;  // 검색 결과 업체, 기관명의 주소를 제공한다.
        private String roadAddress;  // 검색 결과 업체, 기관명의 도로명 주소를 제공한다.
        private int mapx;  // 식당 위치 정보의 x좌표를 제공한다. 제공값-카텍 좌표계 값. 지도 API와 연동 가능.
        private int mapy;  // 식당 위치 정보의 y좌표를 제공한다. 제공값-카텍 좌표계 값. 지도 API와 연동 가능.
    }
}

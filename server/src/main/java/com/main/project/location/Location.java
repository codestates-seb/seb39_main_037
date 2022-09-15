package com.main.project.location;

import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.Review;
import com.main.project.user.entity.WebUser;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationdId;

    private Enum<Location.state> locationState ;

    private String city;




    @OneToMany(mappedBy ="foodType", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();

//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<WebUser> usersOfLocation = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();





    public enum state{
        seoul("서울특별시"),
        busan("부산광역시"),
        daegu("대구광역시"),
        inchen("인천광역시"),
        gwangju("광주광역시"),
        daejeon("대전광역시"),
        ulsan("울산광역시"),
        sejong("세종특별자치시"),
        gyunggi("경기도"),
        gangwon("강원도"),
        chungcheongbukdo("충청북도"),
        chungcheongnamdo("충청남도"),
        jeollabukdo("전라북도"),
        jeollanamdo("전라남도"),
        gyeongsangbukdo("경상북도"),
        gyeongsangnamdo("경상남도"),
        jeju("제주특별자치도");

        private String stateName;

        state( String stateName) {

            this.stateName = stateName;


        }
    }



}

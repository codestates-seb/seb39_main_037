package com.main.project.location;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class City {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long cityID;

        @Column
        String cityName;

}

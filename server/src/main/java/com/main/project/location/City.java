package com.main.project.location;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Getter
@Setter
@Table(name = "city")
@NoArgsConstructor
public class City {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long cityId;

        @Column
        String cityName;


        @OneToMany(mappedBy = "cities", cascade = CascadeType.ALL)
        private List<Location> locations = new ArrayList<>();
}

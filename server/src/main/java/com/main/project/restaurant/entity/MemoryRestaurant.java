package com.main.project.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MemoryRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;
}

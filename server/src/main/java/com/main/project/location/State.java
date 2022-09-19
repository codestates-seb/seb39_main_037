package com.main.project.location;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "state")
public class State{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int stateId;

    @Column
    String stateName;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<Location> locations = new ArrayList<>();

    public State(int stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

}

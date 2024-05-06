package com.skyline.sdc_project.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatusSaveonAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int coins;
    private int gems;

    @ElementCollection
    @CollectionTable(name = "player_trees")
    @Column(name = "tree")
    private ArrayList<String> trees;

    @ElementCollection
    @CollectionTable(name = "player_houses")
    @Column(name = "house")
    private ArrayList<String> houses;

    @ElementCollection
    @CollectionTable(name = "player_solar_pannels")
    @Column(name = "solar_pannel")
    private ArrayList<String> solarPannels;

    @ElementCollection
    @CollectionTable(name = "player_colors_of_environment")
    @Column(name = "color_of_environment")
    private ArrayList<String> colorsOfEnvironment;
}

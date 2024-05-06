package com.skyline.sdc_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String trees;
    private String houses;

    private String solarPannels;  // Using camelCase naming convention
    private String colorsOfEnvironment;  // Using camelCase naming convention
}

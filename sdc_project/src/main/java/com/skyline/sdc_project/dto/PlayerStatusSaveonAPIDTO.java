package com.skyline.sdc_project.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PlayerStatusSaveonAPIDTO {
    private int id;
    private int coins;
    private int gems;

    private ArrayList<String> trees;
    private ArrayList<String> houses;

    private ArrayList<String> solarPannels;
    private ArrayList<String> colorsOfEnvironment;
}

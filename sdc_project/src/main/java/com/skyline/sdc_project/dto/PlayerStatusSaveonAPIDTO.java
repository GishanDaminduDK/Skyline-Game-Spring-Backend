package com.skyline.sdc_project.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet; // For initialization

@Data
public class PlayerStatusSaveonAPIDTO {
    private int id;
    private int coins;
    private int gems;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private Set<String> resources = new HashSet<>();
}

// PlayerAnswersDTO.java
package com.skyline.sdc_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerAnswersDTO {
    private int id;
    private String answersArray;
    private int totalCoins;
    private PlayerStatusSaveonAPIDTO playerStatus;  // Use DTO here

    // Getters and setters are managed by Lombok through the @Data annotation
}

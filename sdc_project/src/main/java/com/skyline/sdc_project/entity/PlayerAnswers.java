package com.skyline.sdc_project.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerAnswers {
    @Id

    private int id;

    private String answersArray;  // Assuming this is a JSON or serialized string.
    private int totalCoins;  // Stores total coins.

    @OneToOne(mappedBy = "playerAnswers", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private PlayerStatusSaveonAPI playerStatus;  // Bidirectional one-to-one relationship with PlayerStatus.

    // The Getters and Setters are provided by @Data annotation from Lombok.
}

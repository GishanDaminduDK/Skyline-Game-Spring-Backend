package com.skyline.sdc_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatusSaveonAPI {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int coins;
    private int gems;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;


    @ElementCollection
    @CollectionTable(name = "player_resources")
    @Column(name = "resource")
    private Set<String> resources = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        enterTime = LocalDateTime.now();
        exitTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        exitTime = LocalDateTime.now();
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_answers_id", referencedColumnName = "id")
    private PlayerAnswers playerAnswers;
}
//package com.skyline.sdc_project.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class PlayerStatusSaveonAPI {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private int coins;
//    private int gems;
//    private LocalDateTime enterTime;
//    private LocalDateTime exitTime;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "player_answers_id", referencedColumnName = "id")
//    private PlayerAnswers playerAnswers;
//
//    @PrePersist
//    @PreUpdate
//    private void synchronizeIds() {
//        if (playerAnswers == null) {
//            playerAnswers = new PlayerAnswers();
//        }
//        playerAnswers.setId(this.id);
//    }
//}
//

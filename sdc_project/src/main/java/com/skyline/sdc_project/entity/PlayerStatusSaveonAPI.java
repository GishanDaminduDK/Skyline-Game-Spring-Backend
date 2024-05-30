package com.skyline.sdc_project.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}

package com.skyline.sdc_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Player")
public class Player {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;
    private String playerUsername;
    private String ans_1;
    private String ans_2;
    private String ans_3;
    private String ans_4;
    private String ans_5;
    private String ans_6;
    private String ans_7;
    private String ans_8;
    private String ans_9;
    private String ans_10;

}
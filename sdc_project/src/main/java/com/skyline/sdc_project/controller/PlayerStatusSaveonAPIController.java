package com.skyline.sdc_project.controller;

import com.skyline.sdc_project.dto.PlayerStatusSaveonAPIDTO;
import com.skyline.sdc_project.service.PlayerStatusSaveonAPIService;
import com.skyline.sdc_project.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/playerstatus")
public class PlayerStatusSaveonAPIController {

    @Autowired
    private PlayerStatusSaveonAPIService service;

    @PostMapping("/savestatus")
    public ResponseEntity<String> createPlayerStatus(@RequestBody PlayerStatusSaveonAPIDTO playerStatusDTO) {
        try {
            String response = service.savePlayerStatus(playerStatusDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getStatus/{id}")
    public ResponseEntity<?> getPlayerStatus(@PathVariable Integer id) {
        try {
            Optional<PlayerStatusSaveonAPIDTO> playerStatusDTO = service.getPlayerStatus(id);
            return playerStatusDTO
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<String> updatePlayerStatus(@PathVariable Integer id, @RequestBody PlayerStatusSaveonAPIDTO playerStatusDTO) {
        try {
            String updateStatusFeedback = service.updatePlayerStatus(id, playerStatusDTO);
            return ResponseEntity.ok(updateStatusFeedback);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}

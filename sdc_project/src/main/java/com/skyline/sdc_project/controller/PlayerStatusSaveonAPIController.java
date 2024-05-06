package com.skyline.sdc_project.controller;

import com.skyline.sdc_project.dto.PlayerStatusSaveonAPIDTO;
import com.skyline.sdc_project.service.PlayerStatusSaveonAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playerstatus")
public class PlayerStatusSaveonAPIController {

    @Autowired
    private PlayerStatusSaveonAPIService service;

    // POST endpoint for creating a new player status
    @PostMapping
    public ResponseEntity<PlayerStatusSaveonAPIDTO> createPlayerStatus(@RequestBody PlayerStatusSaveonAPIDTO playerStatusDTO) {
        PlayerStatusSaveonAPIDTO savedPlayerStatus = service.savePlayerStatus(playerStatusDTO);
        return ResponseEntity.ok(savedPlayerStatus);
    }

    // GET endpoint for retrieving a player status by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlayerStatusSaveonAPIDTO> getPlayerStatusById(@PathVariable int id) {
        PlayerStatusSaveonAPIDTO playerStatusDTO = service.getPlayerStatusById(id);
        return ResponseEntity.ok(playerStatusDTO);
    }

    // GET endpoint for retrieving all player statuses
    @GetMapping
    public ResponseEntity<List<PlayerStatusSaveonAPIDTO>> getAllPlayerStatuses() {
        List<PlayerStatusSaveonAPIDTO> playerStatuses = service.getAllPlayerStatuses();
        return ResponseEntity.ok(playerStatuses);
    }

    // PUT endpoint for updating an existing player status
    @PutMapping("/{id}")
    public ResponseEntity<PlayerStatusSaveonAPIDTO> updatePlayerStatus(@PathVariable int id, @RequestBody PlayerStatusSaveonAPIDTO playerStatusDTO) {
        playerStatusDTO.setId(id); // Ensure the ID is set to the path variable ID
        PlayerStatusSaveonAPIDTO updatedPlayerStatus = service.savePlayerStatus(playerStatusDTO);
        return ResponseEntity.ok(updatedPlayerStatus);
    }

    // DELETE endpoint for deleting a player status by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerStatus(@PathVariable int id) {
        service.deletePlayerStatus(id);
        return ResponseEntity.ok().build();
    }
}


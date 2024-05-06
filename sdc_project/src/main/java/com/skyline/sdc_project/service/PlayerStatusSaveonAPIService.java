package com.skyline.sdc_project.service;

import com.skyline.sdc_project.dto.PlayerStatusSaveonAPIDTO;
import com.skyline.sdc_project.entity.PlayerStatusSaveonAPI;
import com.skyline.sdc_project.repository.PlayerStatusSaveonAPIRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerStatusSaveonAPIService {

    @Autowired
    private PlayerStatusSaveonAPIRepo repository;

    // Create or update player status
    @Transactional
    public PlayerStatusSaveonAPIDTO savePlayerStatus(PlayerStatusSaveonAPIDTO dto) {
        PlayerStatusSaveonAPI playerStatus = mapToEntity(dto);
        playerStatus = repository.save(playerStatus);
        return mapToDTO(playerStatus);
    }

    // Retrieve a player status by ID
    public PlayerStatusSaveonAPIDTO getPlayerStatusById(int id) {
        PlayerStatusSaveonAPI playerStatus = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        return mapToDTO(playerStatus);
    }

    // List all player statuses
    public List<PlayerStatusSaveonAPIDTO> getAllPlayerStatuses() {
        List<PlayerStatusSaveonAPI> playerStatuses = repository.findAll();
        return playerStatuses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Delete a player status by ID
    @Transactional
    public void deletePlayerStatus(int id) {
        repository.deleteById(id);
    }

    // Utility method to map Entity to DTO
    private PlayerStatusSaveonAPIDTO mapToDTO(PlayerStatusSaveonAPI playerStatus) {
        PlayerStatusSaveonAPIDTO dto = new PlayerStatusSaveonAPIDTO();
        dto.setId(playerStatus.getId());
        dto.setCoins(playerStatus.getCoins());
        dto.setGems(playerStatus.getGems());
        dto.setTrees(new ArrayList<>(playerStatus.getTrees()));
        dto.setHouses(new ArrayList<>(playerStatus.getHouses()));
        dto.setSolarPannels(new ArrayList<>(playerStatus.getSolarPannels()));
        dto.setColorsOfEnvironment(new ArrayList<>(playerStatus.getColorsOfEnvironment()));
        return dto;
    }

    // Utility method to map DTO to Entity
    private PlayerStatusSaveonAPI mapToEntity(PlayerStatusSaveonAPIDTO dto) {
        PlayerStatusSaveonAPI playerStatus = new PlayerStatusSaveonAPI();
        playerStatus.setId(dto.getId());
        playerStatus.setCoins(dto.getCoins());
        playerStatus.setGems(dto.getGems());
        playerStatus.setTrees(new ArrayList<>(dto.getTrees()));
        playerStatus.setHouses(new ArrayList<>(dto.getHouses()));
        playerStatus.setSolarPannels(new ArrayList<>(dto.getSolarPannels()));
        playerStatus.setColorsOfEnvironment(new ArrayList<>(dto.getColorsOfEnvironment()));
        return playerStatus;
    }
}

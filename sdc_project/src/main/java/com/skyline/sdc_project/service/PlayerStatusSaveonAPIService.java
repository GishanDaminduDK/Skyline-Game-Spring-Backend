package com.skyline.sdc_project.service;

import com.skyline.sdc_project.dto.PlayerStatusSaveonAPIDTO;
import com.skyline.sdc_project.entity.PlayerStatusSaveonAPI;
import com.skyline.sdc_project.repository.PlayerStatusSaveonAPIRepo;
import com.skyline.sdc_project.util.VarList;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlayerStatusSaveonAPIService {

    @Autowired
    private PlayerStatusSaveonAPIRepo playerStatusSaveonAPIRepo;

    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void init() {
        modelMapper.typeMap(PlayerStatusSaveonAPIDTO.class, PlayerStatusSaveonAPI.class).addMappings(mapper -> {
            mapper.skip(PlayerStatusSaveonAPI::setId); // Prevent the ID from being mapped
        });
    }

    public String savePlayerStatus(PlayerStatusSaveonAPIDTO playerStatusDTO) {
        if (playerStatusSaveonAPIRepo.existsById(playerStatusDTO.getId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            playerStatusSaveonAPIRepo.save(modelMapper.map(playerStatusDTO, PlayerStatusSaveonAPI.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public Optional<PlayerStatusSaveonAPIDTO> getPlayerStatus(Integer id) {
        Optional<PlayerStatusSaveonAPI> playerStatus = playerStatusSaveonAPIRepo.findById(id);
        return playerStatus.map(status -> modelMapper.map(status, PlayerStatusSaveonAPIDTO.class));
    }

    @Transactional
    public String updatePlayerStatus(Integer id, PlayerStatusSaveonAPIDTO playerStatusDTO) {
        return playerStatusSaveonAPIRepo.findById(id)
                .map(playerStatus -> {
                    modelMapper.map(playerStatusDTO, playerStatus);
                    playerStatusSaveonAPIRepo.save(playerStatus);
                    return VarList.RSP_SUCCESS;
                }).orElse(VarList.RSP_FAIL);
    }
}

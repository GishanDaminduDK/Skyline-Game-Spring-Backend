package com.skyline.sdc_project.service;

import com.skyline.sdc_project.dto.PlayerStatusSaveonAPIDTO;
import com.skyline.sdc_project.entity.PlayerAnswers;
import com.skyline.sdc_project.entity.PlayerStatusSaveonAPI;
import com.skyline.sdc_project.repository.PlayerAnswersRepo;
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
    private PlayerAnswersRepo playerAnswersRepo;

    @Autowired
    private ModelMapper modelMapper;
    @PostConstruct
    public void init() {
        modelMapper.typeMap(PlayerStatusSaveonAPIDTO.class, PlayerStatusSaveonAPI.class).addMappings(mapper -> {
            mapper.skip(PlayerStatusSaveonAPI::setId); // Prevent the ID from being mapped
        });
    }


    @Transactional
    public String savePlayerStatus(PlayerStatusSaveonAPIDTO playerStatusDTO) {
        PlayerStatusSaveonAPI playerStatus = modelMapper.map(playerStatusDTO, PlayerStatusSaveonAPI.class);

        if (playerStatusDTO.getId() != 0) {
            playerStatus.setId(playerStatusDTO.getId()); // Explicitly set the ID if provided
            System.out.println("PlayerStatus ID set to: " + playerStatus.getId());
        }

        // Set the PlayerAnswers association without updating PlayerAnswers
        PlayerAnswers playerAnswers = playerAnswersRepo.findById(playerStatus.getId())
                .orElse(new PlayerAnswers());
        playerAnswers.setId(playerStatus.getId()); // Set ID but do not save PlayerAnswers here
        playerStatus.setPlayerAnswers(playerAnswers);

        playerStatusSaveonAPIRepo.save(playerStatus); // Save PlayerStatus without cascading to PlayerAnswers
        return VarList.RSP_SUCCESS;
    }




    public Optional<PlayerStatusSaveonAPIDTO> getPlayerStatus(Integer id) {
        Optional<PlayerStatusSaveonAPI> playerStatus = playerStatusSaveonAPIRepo.findById(id);
        return playerStatus.map(status -> modelMapper.map(status, PlayerStatusSaveonAPIDTO.class));
    }

    @Transactional
    public String updatePlayerStatus(Integer id, PlayerStatusSaveonAPIDTO playerStatusDTO) {
        return playerStatusSaveonAPIRepo.findById(id)
                .map(playerStatus -> {
                    // Map the changes from DTO to the existing playerStatus object
                    modelMapper.map(playerStatusDTO, playerStatus);

                    // Assuming player_answers_id is not to be updated, ensure it is not changed.
                    // Fetch and set the current PlayerAnswers without changing it.
                    if (playerStatus.getPlayerAnswers() != null) {
                        PlayerAnswers existingPlayerAnswers = playerAnswersRepo.findById(playerStatus.getPlayerAnswers().getId()).orElse(null);
                        if (existingPlayerAnswers != null) {
                            playerStatus.setPlayerAnswers(existingPlayerAnswers);
                        }
                    }

                    // Save PlayerStatus without cascading changes to PlayerAnswers
                    playerStatusSaveonAPIRepo.save(playerStatus);
                    return VarList.RSP_SUCCESS;
                }).orElse(VarList.RSP_FAIL);
    }

}

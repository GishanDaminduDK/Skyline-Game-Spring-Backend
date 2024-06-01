// PlayerAnswersService.java
package com.skyline.sdc_project.service;

import com.skyline.sdc_project.dto.PlayerAnswersDTO;
import com.skyline.sdc_project.dto.PlayerStatusSaveonAPIDTO;
import com.skyline.sdc_project.entity.PlayerAnswers;
import com.skyline.sdc_project.entity.PlayerStatusSaveonAPI;
import com.skyline.sdc_project.repository.PlayerAnswersRepo;
import com.skyline.sdc_project.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PlayerAnswersService {
    @Autowired
    private PlayerAnswersRepo playerAnswersRepo;

    @Autowired
    private ModelMapper modelMapper;

    public PlayerAnswersDTO getPlayerAnswers(Integer playerId) {
        Optional<PlayerAnswers> playerAnswersOptional = playerAnswersRepo.findById(playerId);
        if (playerAnswersOptional.isPresent()) {
            PlayerAnswers playerAnswers = playerAnswersOptional.get();
            PlayerAnswersDTO playerAnswersDTO = modelMapper.map(playerAnswers, PlayerAnswersDTO.class);
            if (playerAnswers.getPlayerStatus() != null) {
                PlayerStatusSaveonAPIDTO statusDTO = modelMapper.map(playerAnswers.getPlayerStatus(), PlayerStatusSaveonAPIDTO.class);
                playerAnswersDTO.setPlayerStatus(statusDTO);  // Correctly set the status
            }
            return playerAnswersDTO;
        } else {
            return null;  // Or throw an exception, depending on your error handling strategy
        }
    }


    public String savePlayerAnswers(PlayerAnswersDTO playerAnswersDTO) {
        PlayerAnswers playerAnswers = modelMapper.map(playerAnswersDTO, PlayerAnswers.class);
        playerAnswersRepo.save(playerAnswers);  // This will handle both new and existing records.
        return VarList.RSP_SUCCESS;  // Assume RSP_SUCCESS is a constant for successful operation.
    }



}

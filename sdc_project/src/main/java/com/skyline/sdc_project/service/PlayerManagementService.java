package com.skyline.sdc_project.service;
import com.skyline.sdc_project.dto.PlayerAnswersDTO;
import com.skyline.sdc_project.dto.PlayerDTO;
import com.skyline.sdc_project.entity.Player;
import com.skyline.sdc_project.entity.PlayerAnswers;
import com.skyline.sdc_project.repository.PlayerAnswersRepo;
import com.skyline.sdc_project.repository.PlayerManagementRepo;
import com.skyline.sdc_project.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerManagementService {

    @Autowired
    private PlayerManagementRepo playerRepo;


    @Autowired
    private ModelMapper modelMapper;

    public String savePlayer(PlayerDTO playerDTO){
        if (playerRepo.existsById(playerDTO.getId())){
            return VarList.RSP_DUPLICATED;
        } else {
            playerRepo.save(modelMapper.map(playerDTO, Player.class));
            return VarList.RSP_SUCCESS;
        }
    }


    public String updatePlayer(PlayerDTO playerDTO){
        if (playerRepo.existsById(playerDTO.getId())){
            playerRepo.save(modelMapper.map(playerDTO, Player.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<PlayerDTO> getAllPlayer(){
        List<Player> playerList = playerRepo.findAll();
        return modelMapper.map(playerList, new TypeToken<ArrayList<PlayerDTO>>(){}.getType());
    }

    public PlayerDTO searchPlayer(int Player_id){
        if (playerRepo.existsById(Player_id)){
            Player player = playerRepo.findById(Player_id).orElse(null);
            return modelMapper.map(player, PlayerDTO.class);
        } else {
            return null;
        }
    }

    public String deletePlayer(int playerID){
        if (playerRepo.existsById(playerID)){
            playerRepo.deleteById(playerID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
//    public Player findPlayerByIdAndUsername(Long playerId, String playerUsername) {
//        // Implement this method to find a player by ID and username in your PlayerService
//        return playerRepo.findByPlayerIdAndPlayerUsername(playerId, playerUsername);
//    }
}

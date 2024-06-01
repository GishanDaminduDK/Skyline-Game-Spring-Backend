package com.skyline.sdc_project.controller;

import com.skyline.sdc_project.dto.PlayerAnswersDTO;
import com.skyline.sdc_project.dto.ResponseDTO;
import com.skyline.sdc_project.service.PlayerAnswersService;
import com.skyline.sdc_project.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/player")  // Standardize the base URL
@CrossOrigin(origins = "http://localhost:5173")  // Use only needed origins
public class PlayerAnswersController {

    @Autowired
    private PlayerAnswersService playerAnswersService;

    @PostMapping("/saveAnswers")
    public ResponseEntity<ResponseDTO> savePlayerAnswers(@RequestBody PlayerAnswersDTO playerAnswersDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            String result = playerAnswersService.savePlayerAnswers(playerAnswersDTO);
            if (VarList.RSP_SUCCESS.equals(result)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Answer saved successfully");
                responseDTO.setContent(playerAnswersDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Failed to save the answer");
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Internal Server Error: " + ex.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<ResponseDTO> getPlayerAnswers(@PathVariable("id") Integer playerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            PlayerAnswersDTO playerAnswersDTO = playerAnswersService.getPlayerAnswers(playerId);
            if (playerAnswersDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(playerAnswersDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Player Answers not found");
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Internal Server Error: " + ex.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

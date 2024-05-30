package com.skyline.sdc_project.controller;

import com.skyline.sdc_project.entity.QueAnsBank;
import com.skyline.sdc_project.service.QueAnsBankService;
import com.skyline.sdc_project.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/questions")
public class QueAnsBankController {

    @Autowired
    private QueAnsBankService queAnsBankService;

    @PostMapping("/addQuestions")
    public ResponseEntity<String> saveQueAnsFeed(@RequestBody QueAnsBank queAnsBank) {
        String response = queAnsBankService.saveQueAnsFeed(queAnsBank);
        if (response.equals(VarList.RSP_DUPLICATED)) {
            return ResponseEntity.status(409).body("Duplicate entry");
        } else if (response.equals(VarList.RSP_SUCCESS)) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(500).body("Failed to save");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueAnsBank> getQueAnsFeed(@PathVariable Integer id) {
        QueAnsBank queAnsBank = queAnsBankService.getQueAnsFeed(id);
        if (queAnsBank != null) {
            return ResponseEntity.ok(queAnsBank);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}

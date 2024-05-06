package com.skyline.sdc_project.controller;

import com.skyline.sdc_project.entity.QueAnsBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/questions")
public class QueAnsBankController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get a specific question by ID
    @GetMapping("/{id}")
    public ResponseEntity<QueAnsBank> getQuestionById(@PathVariable Long id) {
        String sql = "SELECT * FROM que_ans_bank WHERE id = ?";
        try {
            QueAnsBank question = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<QueAnsBank>() {
                @Override
                public QueAnsBank mapRow(ResultSet rs, int rowNum) throws SQLException {
                    QueAnsBank queAnsBank = new QueAnsBank();
                    queAnsBank.setId(rs.getLong("id"));
                    queAnsBank.setQue(rs.getString("que"));
                    return queAnsBank;
                }
            });
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestion(@RequestBody String questionData) {
        String sql = "INSERT INTO que_ans_bank (que) VALUES (?)";
        try {
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, questionData);
                }
            });
            return ResponseEntity.ok("Question added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add question: " + e.getMessage());
        }
    }

}

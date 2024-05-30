package com.skyline.sdc_project.service;

import com.skyline.sdc_project.entity.QueAnsBank;
import com.skyline.sdc_project.repository.QueAnsBankRepo;
import com.skyline.sdc_project.util.VarList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class QueAnsBankService {

    @Autowired
    private QueAnsBankRepo queAnsBankRepo;

    public String saveQueAnsFeed(QueAnsBank queAnsBank) {
        try {
            queAnsBankRepo.save(queAnsBank); // No need to check for duplicates
            return VarList.RSP_SUCCESS;
        } catch (DataIntegrityViolationException ex) {
            // This exception is thrown when a unique constraint is violated (e.g., duplicate ID)
            return VarList.RSP_DUPLICATED;
        } catch (Exception ex) {
            // Handle other potential errors
            return VarList.RSP_FAIL; // Or another appropriate error code
        }
    }

    public QueAnsBank getQueAnsFeed(Integer queAnsBankId) {
        try {
            Optional<QueAnsBank> queAnsBankOptional = queAnsBankRepo.findById(queAnsBankId);
            if (queAnsBankOptional.isPresent()) {
                return queAnsBankOptional.get();
            } else {
                // Handle case when the entity is not found
                return null; // Or throw a custom exception if needed
            }
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            e.printStackTrace();
            return null; // Or throw a custom exception if needed
        }
    }
}

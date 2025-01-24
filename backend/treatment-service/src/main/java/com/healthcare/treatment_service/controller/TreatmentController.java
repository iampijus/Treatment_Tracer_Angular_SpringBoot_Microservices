package com.healthcare.treatment_service.controller;

import com.healthcare.treatment_service.model.Treatment;
import com.healthcare.treatment_service.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/healthcare/v1")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    @GetMapping("/treatments")
    public ResponseEntity<List<Treatment>> getTreatments() {
        try {
            List<Treatment> treatments = treatmentService.getTreatments();

            if (treatments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.ok(treatments);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error calling external API");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


}

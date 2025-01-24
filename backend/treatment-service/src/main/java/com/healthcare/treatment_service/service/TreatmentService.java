package com.healthcare.treatment_service.service;

import com.healthcare.treatment_service.model.Treatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentService {
    @Autowired
    private TreatmentClient treatmentClient;

    public List<Treatment> getTreatments(){
        return treatmentClient.getTreatments();
    }



}

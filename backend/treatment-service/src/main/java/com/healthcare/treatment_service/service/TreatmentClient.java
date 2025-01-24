package com.healthcare.treatment_service.service;

import com.healthcare.treatment_service.model.Treatment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "treatment-client", url = "http://localhost:3232")
public interface TreatmentClient {

    @GetMapping("/treatments")
    List<Treatment> getTreatments();
}

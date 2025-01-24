package com.healthcare.authentication_service.service;

import com.healthcare.authentication_service.model.LoginDto;

public interface AuthenticationService {
    public boolean authenticateUser(LoginDto loginDto);
    //Kafka consumer
    public void userCredentialConsumer(String event);
}

package com.healthcare.authentication_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.authentication_service.event.UserCredentialEvent;
import com.healthcare.authentication_service.model.LoginDto;
import com.healthcare.authentication_service.model.UserCredential;
import com.healthcare.authentication_service.model.UserCredentialDto;
import com.healthcare.authentication_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    public AuthenticationServiceImpl(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }


    @Override
    public boolean authenticateUser(LoginDto loginDto) {
        UserCredential userCredential = userCredentialRepository.findByEmail(loginDto.getEmail());
        if (userCredential != null && userCredential.getPassword().equals(loginDto.getPassword())) {
            return true;
        }
        return false;
    }

    //Kafka consumer
    @Override
    @KafkaListener(topics = "auth_credentials", groupId = "auth_consumers")
    public void userCredentialConsumer(String event) {
        System.out.println("Getting event"+event);
        UserCredentialEvent userCredentialEvent=null;
        try{
            userCredentialEvent=new ObjectMapper().readValue(event,UserCredentialEvent.class);
            System.out.println(userCredentialEvent);
            UserCredentialDto userCredentialDto=userCredentialEvent.getUserCredentialDto();

            UserCredential userCredential = new UserCredential();
            userCredential.setEmail(userCredentialDto.getEmail());
            userCredential.setPassword(userCredentialDto.getPassword());
            userCredentialRepository.save(userCredential);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}

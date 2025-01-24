package com.healthcare.authentication_service.event;

import com.healthcare.authentication_service.model.UserCredentialDto;

public class UserCredentialEvent {
    private String type;
    private UserCredentialDto userCredentialDto;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserCredentialDto getUserCredentialDto() {
        return userCredentialDto;
    }

    public void setUserCredentialDto(UserCredentialDto userCredentialDto) {
        this.userCredentialDto = userCredentialDto;
    }

    @Override
    public String toString() {
        return "UserCredentialEvent{" +
                "type='" + type + '\'' +
                ", userCredentialDto=" + userCredentialDto +
                '}';
    }
}

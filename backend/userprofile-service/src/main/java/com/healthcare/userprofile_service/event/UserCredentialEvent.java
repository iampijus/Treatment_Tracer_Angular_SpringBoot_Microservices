package com.healthcare.userprofile_service.event;

import com.healthcare.userprofile_service.model.UserCredentialDto;

public class UserCredentialEvent {
    private String type;
    private UserCredentialDto userCredentialDto;

    public UserCredentialEvent(String type, UserCredentialDto userCredentialDto){
        this.type=type;
        this.userCredentialDto=userCredentialDto;
    }

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
}

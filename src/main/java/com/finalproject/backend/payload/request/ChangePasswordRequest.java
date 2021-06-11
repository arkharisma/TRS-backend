package com.finalproject.backend.payload.request;

import javax.validation.constraints.NotNull;

public class ChangePasswordRequest {
    
    @NotNull
    private String password;

    public ChangePasswordRequest() {
    }

    public ChangePasswordRequest(@NotNull String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

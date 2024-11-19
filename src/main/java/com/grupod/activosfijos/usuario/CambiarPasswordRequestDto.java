package com.grupod.activosfijos.usuario;

import java.io.Serializable;

public class CambiarPasswordRequestDto implements Serializable {
    private String currentPassword;
    private String newPassword;

    public CambiarPasswordRequestDto() {
    }

    public CambiarPasswordRequestDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

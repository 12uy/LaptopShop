package com.example.laptopshop.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class PasswordDTO {

    @NotEmpty(message = "Phải nhập mật khẩu cũ")
    private String oldPassword;

    @NotEmpty(message = "Phải nhập mật khẩu mới")
    @Length(min = 8, max = 32, message = "Mật khẩu phải có độ dài từ 8 đến 32 ký tự")
    private String newPassword;

    @NotEmpty(message = "Phải nhập lại mật khẩu mới")
    private String confirmNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}

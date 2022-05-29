package com.example.laptopshop.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class TaiKhoanDTO {
    private String id;

    @NotEmpty(message = "Phải nhập địa chỉ email")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Length(min = 8, max = 32, message = "Mật khẩu phải có độ dài từ 8 đến 32 ký tự")
    private String password;

    private String confirmPassword;

    @NotEmpty(message = "Địa chỉ không được trống")
    private String diaChi;


    @NotEmpty(message="Họ tên không được trống")
    private String hoTen;

    @NotEmpty(message="Số điện thoại không được trống")
    private String sdt;
    private String tenVaiTro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
}

package com.example.laptopshop.dto;

public class SearchDonHangObject {
    private String trangThaiDonHang;
    private String tuNgay;
    private String denNgay;

    public SearchDonHangObject() {
        trangThaiDonHang = "";
        tuNgay = "";
        denNgay = "";
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }
}


package com.example.laptopshop.dto;

public class SearchSanPhamObject {
    private String danhMucId;
    private String hangSXId;
    private String donGia;

    //sap xep theo gia
    private String sapXepTheoGia;
    private String[] keyword;
    private String sort;

    //sap xep theo danh muc va hang sx
    private String brand;
    private String manufactor;

    //sap xep theo ram, os, pin
    private String ram;
    private String os;
    private String pin;

    public SearchSanPhamObject() {
        danhMucId = "";
        hangSXId = "";
        donGia = "donGia";
        sapXepTheoGia = "ASC";
    }

    public String getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(String danhMucId) {
        this.danhMucId = danhMucId;
    }

    public String getHangSXId() {
        return hangSXId;
    }

    public void setHangSXId(String hangSXId) {
        this.hangSXId = hangSXId;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getSapXepTheoGia() {
        return sapXepTheoGia;
    }

    public void setSapXepTheoGia(String sapXepTheoGia) {
        this.sapXepTheoGia = sapXepTheoGia;
    }

    public String[] getKeyword() {
        return keyword;
    }

    public void setKeyword(String[] keyword) {
        this.keyword = keyword;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "SearchSanPhamObject{" +
                "danhMucId='" + danhMucId + '\'' +
                ", hangSXId='" + hangSXId + '\'' +
                ", donGia='" + donGia + '\'' +
                '}';
    }
}

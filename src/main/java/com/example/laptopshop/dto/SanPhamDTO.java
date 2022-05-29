package com.example.laptopshop.dto;

import org.springframework.web.multipart.MultipartFile;

public class SanPhamDTO {
    private String id;
    private String tenSanPham;
    private String donGia;

    public String getDonViBan() {
        return donViBan;
    }

    public void setDonViBan(String donViBan) {
        this.donViBan = donViBan;
    }

    private String donViBan;
    private String donViKho;
    private String thongTinBaoHanh;
    private String thongTinChung;
    private String manHinh;
    private String heDieuHanh;
    private String cpu;
    private String ram;
    private String thietKe;
    private String dungLuongPin;

    private long danhMucId;
    private long nhaSXId;
    private MultipartFile hinhAnh;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getDonViKho() {
        return donViKho;
    }

    public void setDonViKho(String donViKho) {
        this.donViKho = donViKho;
    }

    public String getThongTinBaoHanh() {
        return thongTinBaoHanh;
    }

    public void setThongTinBaoHanh(String thongTinBaoHanh) {
        this.thongTinBaoHanh = thongTinBaoHanh;
    }

    public String getThongTinChung() {
        return thongTinChung;
    }

    public void setThongTinChung(String thongTinChung) {
        this.thongTinChung = thongTinChung;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getThietKe() {
        return thietKe;
    }

    public void setThietKe(String thietKe) {
        this.thietKe = thietKe;
    }

    public String getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(String dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public long getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(long danhMucId) {
        this.danhMucId = danhMucId;
    }

    public long getNhaSXId() {
        return nhaSXId;
    }

    public void setNhaSXId(long nhaSXId) {
        this.nhaSXId = nhaSXId;
    }

    public MultipartFile getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(MultipartFile hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}

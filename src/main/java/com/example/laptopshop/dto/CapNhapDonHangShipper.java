package com.example.laptopshop.dto;

import java.util.List;

public class CapNhapDonHangShipper {
    private long idDonHang;
    private String ghiChuShipper;
    private List<CapNhatChiTietDonHang> danhSachCapNhatChiTietDonHang;

    public static class CapNhatChiTietDonHang {
        private long idChiTietDonHang;
        private int soLuongNhanHang;

        public long getIdChiTietDonHang() {
            return idChiTietDonHang;
        }

        public void setIdChiTietDonHang(long idChiTietDonHang) {
            this.idChiTietDonHang = idChiTietDonHang;
        }

        public int getSoLuongNhanHang() {
            return soLuongNhanHang;
        }

        public void setSoLuongNhanHang(int soLuongNhanHang) {
            this.soLuongNhanHang = soLuongNhanHang;
        }
    }

    public long getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(long idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getGhiChuShipper() {
        return ghiChuShipper;
    }

    public void setGhiChuShipper(String ghiChuShipper) {
        this.ghiChuShipper = ghiChuShipper;
    }

    public List<CapNhatChiTietDonHang> getDanhSachCapNhatChiTietDonHang() {
        return danhSachCapNhatChiTietDonHang;
    }

    public void setDanhSachCapNhatChiTietDonHang(List<CapNhatChiTietDonHang> danhSachCapNhatChiTietDonHang) {
        this.danhSachCapNhatChiTietDonHang = danhSachCapNhatChiTietDonHang;
    }
}

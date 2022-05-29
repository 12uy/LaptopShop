package com.example.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "donHang")
    private List<ChiTietDonHang> danhSachChiTiet;

    private String diaChiNhanHang;
    private String sdtNhanHang;
    private String hoTenNhanHang;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+7")
    private Date ngayDatHang;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+7")
    private Date ngayGiaoHang;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+7")
    private Date ngayNhanHang;

    private String trangThaiDonHang;
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name ="ma_nguoi_dat")
    private NguoiDung nguoiDat;

    @ManyToOne
    @JoinColumn(name ="ma_shipper")
    private NguoiDung shipper;

    private long tongGiaTri;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ChiTietDonHang> getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public void setDanhSachChiTiet(List<ChiTietDonHang> danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
    }

    public String getDiaChiNhanHang() {
        return diaChiNhanHang;
    }

    public void setDiaChiNhanHang(String diaChiNhanHang) {
        this.diaChiNhanHang = diaChiNhanHang;
    }

    public String getSdtNhanHang() {
        return sdtNhanHang;
    }

    public void setSdtNhanHang(String sdtNhanHang) {
        this.sdtNhanHang = sdtNhanHang;
    }

    public String getHoTenNhanHang() {
        return hoTenNhanHang;
    }

    public void setHoTenNhanHang(String hoTenNhanHang) {
        this.hoTenNhanHang = hoTenNhanHang;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public Date getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(Date ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }

    public Date getNgayNhanHang() {
        return ngayNhanHang;
    }

    public void setNgayNhanHang(Date ngayNhanHang) {
        this.ngayNhanHang = ngayNhanHang;
    }

    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NguoiDung getNguoiDat() {
        return nguoiDat;
    }

    public void setNguoiDat(NguoiDung nguoiDat) {
        this.nguoiDat = nguoiDat;
    }

    public NguoiDung getShipper() {
        return shipper;
    }

    public void setShipper(NguoiDung shipper) {
        this.shipper = shipper;
    }

    public long getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(long tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
}

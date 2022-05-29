package com.example.laptopshop.service;

import com.example.laptopshop.entities.ChiMucGioHang;
import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.SanPham;

import java.util.List;

public interface ChiMucGioHangService {
    List<ChiMucGioHang> getChiMucGioHangByGioHang(GioHang gioHang);
    ChiMucGioHang getChiMucGioHangByGioHangAndSanPham(GioHang gioHang, SanPham sanPham);
    ChiMucGioHang saveChiMucGioHang(ChiMucGioHang chiMucGioHang);
    void deleteChiMucGioHang(ChiMucGioHang chiMucGioHang);
    void deleteAllChiMucGioHang(List<ChiMucGioHang> listChiMucGioHang);

}

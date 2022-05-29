package com.example.laptopshop.service;

import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.NguoiDung;

public interface GioHangService {
    GioHang getGioHangByNguoiDung(NguoiDung nguoiDung);
    GioHang save(GioHang gioHang);
}

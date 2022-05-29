package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.repository.GioHangRepository;
import com.example.laptopshop.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public GioHang getGioHangByNguoiDung(NguoiDung nguoiDung) {
        return gioHangRepository.findByNguoiDung(nguoiDung);
    }

    @Override
    public GioHang save(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }
}

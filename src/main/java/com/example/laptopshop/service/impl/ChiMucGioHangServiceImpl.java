package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.ChiMucGioHang;
import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.SanPham;
import com.example.laptopshop.repository.ChiMucGioHangRepository;
import com.example.laptopshop.service.ChiMucGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChiMucGioHangServiceImpl implements ChiMucGioHangService {

    @Autowired
    private ChiMucGioHangRepository chiMucGioHangRepository;

    @Override
    public List<ChiMucGioHang> getChiMucGioHangByGioHang(GioHang gioHang) {
        return chiMucGioHangRepository.findByGioHang(gioHang);
    }

    @Override
    public ChiMucGioHang getChiMucGioHangByGioHangAndSanPham(GioHang gioHang, SanPham sanPham) {
        return chiMucGioHangRepository.findBySanPhamAndGioHang(sanPham, gioHang);
    }

    @Override
    public ChiMucGioHang saveChiMucGioHang(ChiMucGioHang chiMucGioHang) {
        return chiMucGioHangRepository.save(chiMucGioHang);
    }

    @Override
    public void deleteChiMucGioHang(ChiMucGioHang chiMucGioHang) {
        chiMucGioHangRepository.delete(chiMucGioHang);
    }

    @Override
    public void deleteAllChiMucGioHang(List<ChiMucGioHang> listChiMucGioHang) {
        chiMucGioHangRepository.deleteAll(listChiMucGioHang);
    }
}

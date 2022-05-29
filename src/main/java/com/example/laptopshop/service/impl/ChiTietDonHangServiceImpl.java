package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.ChiTietDonHang;
import com.example.laptopshop.repository.ChiTietDonHangRepository;
import com.example.laptopshop.service.ChiTietDonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService {

    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    @Override
    public List<ChiTietDonHang> save(List<ChiTietDonHang> listChiTietDonHang) {
        return chiTietDonHangRepository.saveAll(listChiTietDonHang);
    }
}

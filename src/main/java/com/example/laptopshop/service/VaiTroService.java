package com.example.laptopshop.service;

import com.example.laptopshop.entities.VaiTro;

import java.util.List;

public interface VaiTroService {
    VaiTro finhByTenVaiTro(String tenVaiTro);

    List<VaiTro> findAll();
}

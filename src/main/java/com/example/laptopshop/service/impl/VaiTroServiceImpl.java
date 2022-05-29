package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.VaiTro;
import com.example.laptopshop.repository.VaiTroRepository;
import com.example.laptopshop.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VaiTroServiceImpl implements VaiTroService {

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public VaiTro finhByTenVaiTro(String tenVaiTro) {
        return vaiTroRepository.findByTenVaiTro(tenVaiTro);
    }

    @Override
    public List<VaiTro> findAll() {
        return vaiTroRepository.findAll();
    }
}

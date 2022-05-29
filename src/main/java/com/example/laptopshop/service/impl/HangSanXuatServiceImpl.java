package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.HangSanXuat;
import com.example.laptopshop.repository.HangSanXuatRepository;
import com.example.laptopshop.service.HangSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HangSanXuatServiceImpl implements HangSanXuatService {

    @Autowired
    private HangSanXuatRepository hangSanXuatRepository;

    @Override
    public List<HangSanXuat> getAllHangSanXuat() {
        return hangSanXuatRepository.findAll();
    }

    @Override
    public Page<HangSanXuat> getAllHangSanXuatForPageable(int page, int size) {
        return hangSanXuatRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public HangSanXuat save(HangSanXuat hangSanXuat) {
        return hangSanXuatRepository.save(hangSanXuat);
    }

    @Override
    public HangSanXuat getHangSanXuatById(long id) {
        return hangSanXuatRepository.findById(id).get();
    }

    @Override
    public HangSanXuat update(HangSanXuat hangSanXuat) {
        return hangSanXuatRepository.save(hangSanXuat);
    }

    @Override
    public void delete(long id) {
        hangSanXuatRepository.deleteById(id);
    }
}

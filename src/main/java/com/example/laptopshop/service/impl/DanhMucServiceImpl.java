package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.DanhMuc;
import com.example.laptopshop.repository.DanhMucRepository;
import com.example.laptopshop.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DanhMucServiceImpl implements DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Override
    public Page<DanhMuc> getAllDanhMucForPageable(int page, int size) {
        return danhMucRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc getDanhMucById(long id) {
        return danhMucRepository.findById(id).get();
    }

    @Override
    public DanhMuc save(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc update(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public void delete(long id) {
        danhMucRepository.deleteById(id);
    }
}

package com.example.laptopshop.service;

import com.example.laptopshop.entities.DanhMuc;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DanhMucService {
    Page<DanhMuc> getAllDanhMucForPageable(int page, int size);
    List<DanhMuc> getAllDanhMuc();
    DanhMuc getDanhMucById(long id);
    DanhMuc save(DanhMuc danhMuc);
    DanhMuc update(DanhMuc danhMuc);
    void delete(long id);
}

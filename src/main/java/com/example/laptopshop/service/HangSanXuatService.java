package com.example.laptopshop.service;

import com.example.laptopshop.entities.HangSanXuat;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HangSanXuatService {
    List<HangSanXuat> getAllHangSanXuat();
    Page<HangSanXuat> getAllHangSanXuatForPageable(int page, int size);
    HangSanXuat save(HangSanXuat hangSanXuat);
    HangSanXuat getHangSanXuatById(long id);
    HangSanXuat update(HangSanXuat hangSanXuat);
    void delete(long id);

}

package com.example.laptopshop.service;

import com.example.laptopshop.dto.SanPhamDTO;
import com.example.laptopshop.dto.SearchSanPhamObject;
import com.example.laptopshop.entities.SanPham;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface SanPhamService {
    SanPham save(SanPhamDTO sanPhamDTO);

    SanPham update(SanPhamDTO sanPhamDTO);

    void deleteById(long id);

    Page<SanPham> getAllSanPhamByFilter(SearchSanPhamObject searchSanPhamObject, int page, int size);

    SanPham getSanPhamById(long id);

    List<SanPham> getLastestSanPham();

    Iterable<SanPham> getSanPhamByTenSanPhamWithoutPaginate(SearchSanPhamObject searchSanPhamObject);

    Page<SanPham> getSanPhamByTenSanPham(SearchSanPhamObject searchSanPhamObject, int page, int size);

    List<SanPham> getAllSanPhamByList(Set<Long> listId);

    Page<SanPham> getSanPhamForAdmin(String tenSanPam, int page, int size);

    Iterable<SanPham> getSanPhamByDanhMuc(String brand);

    Page<SanPham> getSanPhamByBrand(SearchSanPhamObject searchSanPhamObject, int page, int size);
}

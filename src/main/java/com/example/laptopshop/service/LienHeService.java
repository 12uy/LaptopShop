package com.example.laptopshop.service;

import com.example.laptopshop.dto.SearchLienHeObject;
import com.example.laptopshop.entities.LienHe;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface LienHeService {
    Page<LienHe> getLienHeByFilter(SearchLienHeObject searchLienHeObject, int page) throws ParseException;

    LienHe findById(long id);

    LienHe saveLienHe(LienHe lienHe);

    int countByTrangThaiLienHe(String trangThaiLienHe);
}

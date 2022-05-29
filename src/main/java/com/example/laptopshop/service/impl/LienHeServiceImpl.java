package com.example.laptopshop.service.impl;

import com.example.laptopshop.dto.SearchLienHeObject;
import com.example.laptopshop.entities.LienHe;
import com.example.laptopshop.entities.QLienHe;
import com.example.laptopshop.repository.LienHeRepository;
import com.example.laptopshop.service.LienHeService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@Service
public class LienHeServiceImpl implements LienHeService {

    @Autowired
    private LienHeRepository lienHeRepository;

    @Override
    public Page<LienHe> getLienHeByFilter(SearchLienHeObject searchLienHeObject, int page) throws ParseException {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String trangThaiLienHe = searchLienHeObject.getTrangThaiLienHe();
        String tuNgay = searchLienHeObject.getTuNgay();
        String denNgay = searchLienHeObject.getDenNgay();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (!trangThaiLienHe.equals("")) {
            booleanBuilder.and(QLienHe.lienHe.trangThai.eq(trangThaiLienHe));
        }
        if (!tuNgay.equals("") && tuNgay != null) {
                booleanBuilder.and(QLienHe.lienHe.ngayLienHe.goe(sdf.parse(tuNgay)));
        }
        if (!denNgay.equals("") && denNgay != null) {
            booleanBuilder.and(QLienHe.lienHe.ngayLienHe.loe(sdf.parse(denNgay)));
        }
        return lienHeRepository.findAll(booleanBuilder, PageRequest.of(page-1, 3));
    }

    @Override
    public LienHe findById(long id) {
        return lienHeRepository.findById(id).get();
    }

    @Override
    public LienHe saveLienHe(LienHe lienHe) {
        return lienHeRepository.save(lienHe);
    }

    @Override
    public int countByTrangThaiLienHe(String trangThaiLienHe) {
        return lienHeRepository.countByTrangThai(trangThaiLienHe);
    }
}

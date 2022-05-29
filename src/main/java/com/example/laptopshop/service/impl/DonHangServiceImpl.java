package com.example.laptopshop.service.impl;

import com.example.laptopshop.dto.SearchDonHangObject;
import com.example.laptopshop.entities.DonHang;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.QDonHang;
import com.example.laptopshop.repository.DonHangRepository;
import com.example.laptopshop.service.DonHangService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Override
    public Page<DonHang> getAllDonHangByFilter(SearchDonHangObject searchDonHangObject, int page) throws ParseException {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String trangThaiDonHang = searchDonHangObject.getTrangThaiDonHang();
        String tuNgay = searchDonHangObject.getTuNgay();
        String denNgay = searchDonHangObject.getDenNgay();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(!trangThaiDonHang.equals("")){
            booleanBuilder.and(QDonHang.donHang.trangThaiDonHang.eq(trangThaiDonHang));
        }
        if(!tuNgay.equals("") && tuNgay != null){
            if (trangThaiDonHang.equals("") || trangThaiDonHang.equals("Chưa xử lý") || trangThaiDonHang.equals("Đã hủy")) {
                booleanBuilder.and(QDonHang.donHang.ngayDatHang.goe(sdf.parse(tuNgay)));
            } else if (trangThaiDonHang.equals("Đang giao hàng")) {
                booleanBuilder.and(QDonHang.donHang.ngayGiaoHang.goe(sdf.parse(tuNgay)));
            } else {
                booleanBuilder.and(QDonHang.donHang.ngayNhanHang.goe(sdf.parse(tuNgay)));
            }
        }

        if (!denNgay.equals("") && denNgay != null) {
            if (trangThaiDonHang.equals("") || trangThaiDonHang.equals("Chưa xử lý") || trangThaiDonHang.equals("Đã hủy")) {
                booleanBuilder.and(QDonHang.donHang.ngayDatHang.loe(sdf.parse(denNgay)));
            } else if (trangThaiDonHang.equals("Đang giao hàng")) {
                booleanBuilder.and(QDonHang.donHang.ngayGiaoHang.loe(sdf.parse(denNgay)));
            } else {
                booleanBuilder.and(QDonHang.donHang.ngayNhanHang.loe(sdf.parse(denNgay)));
            }
        }
        return donHangRepository.findAll(booleanBuilder, PageRequest.of(page, 10));
    }

    @Override
    public DonHang updateDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Override
    public DonHang getDonHangById(long id) {
        return donHangRepository.findById(id).get();
    }

    @Override
    public Page<DonHang> getDonHangByShipper(SearchDonHangObject searchDonHangObject, int page, int size, NguoiDung shipper) throws ParseException {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String trangThaiDonHang = searchDonHangObject.getTrangThaiDonHang();
        String tuNgay = searchDonHangObject.getTuNgay();
        String denNgay = searchDonHangObject.getDenNgay();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        booleanBuilder.and(QDonHang.donHang.shipper.eq(shipper));

        if(!trangThaiDonHang.equals("")){
            booleanBuilder.and(QDonHang.donHang.trangThaiDonHang.eq(trangThaiDonHang));
        }

        if (!tuNgay.equals("") && tuNgay != null) {
            if (trangThaiDonHang.equals("Đang giao hàng")) {
                booleanBuilder.and(QDonHang.donHang.ngayGiaoHang.goe(sdf.parse(tuNgay)));
            } else {
                booleanBuilder.and(QDonHang.donHang.ngayNhanHang.goe(sdf.parse(tuNgay)));
            }
        }

        if (!denNgay.equals("") && denNgay != null) {
            if (trangThaiDonHang.equals("Đang giao hàng")) {
                booleanBuilder.and(QDonHang.donHang.ngayGiaoHang.loe(sdf.parse(denNgay)));
            } else {
                booleanBuilder.and(QDonHang.donHang.ngayNhanHang.loe(sdf.parse(denNgay)));
            }
        }

        return donHangRepository.findAll(booleanBuilder, PageRequest.of(page-1, size));
    }

    @Override
    public DonHang saveDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Override
    public List<Object> getDonHangTheoThangVaNam() {
        return donHangRepository.layDonHangTheoThangVaNam();
    }

    @Override
    public List<DonHang> findByTrangThaiDonHangAndShipper(String trangThaiDonHang, NguoiDung shipper) {
        return donHangRepository.findByTrangThaiDonHangAndShipper(trangThaiDonHang, shipper);
    }

    @Override
    public List<DonHang> getDonHangByNguoiDung(NguoiDung nguoiDung) {
        return donHangRepository.findByNguoiDat(nguoiDung);
    }

    @Override
    public int countByTrangThaiDonHang(String trangThaiDonHang) {
        return donHangRepository.countByTrangThaiDonHang(trangThaiDonHang);
    }
}

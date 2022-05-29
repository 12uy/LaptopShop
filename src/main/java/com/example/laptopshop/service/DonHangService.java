package com.example.laptopshop.service;

import com.example.laptopshop.dto.SearchDonHangObject;
import com.example.laptopshop.entities.DonHang;
import com.example.laptopshop.entities.NguoiDung;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface DonHangService {
    Page<DonHang> getAllDonHangByFilter(SearchDonHangObject searchDonHangObject, int page) throws ParseException;
    //update don hang
    DonHang updateDonHang(DonHang donHang);
    //tim don hang theo id
    DonHang getDonHangById(long id);
    //tim don hang theo shipper
    Page<DonHang> getDonHangByShipper(SearchDonHangObject searchDonHangObject, int page, int size, NguoiDung shipper) throws ParseException;

    //save don hang
    DonHang saveDonHang(DonHang donHang);
    //lay don hang theo thang va nam
    List<Object> getDonHangTheoThangVaNam();

    List<DonHang> findByTrangThaiDonHangAndShipper(String trangThaiDonHang, NguoiDung shipper);

    List<DonHang> getDonHangByNguoiDung(NguoiDung nguoiDung);

    int countByTrangThaiDonHang(String trangThaiDonHang);
}

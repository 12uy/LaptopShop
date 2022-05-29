package com.example.laptopshop.repository;

import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    GioHang findByNguoiDung(NguoiDung nguoiDung);
}

package com.example.laptopshop.repository;

import com.example.laptopshop.entities.ChiMucGioHang;
import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChiMucGioHangRepository extends JpaRepository<ChiMucGioHang, Long> {
    ChiMucGioHang findBySanPhamAndGioHang(SanPham sanPham, GioHang gioHang);
    List<ChiMucGioHang> findByGioHang(GioHang gioHang);
}


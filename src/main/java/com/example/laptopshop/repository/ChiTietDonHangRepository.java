package com.example.laptopshop.repository;

import com.example.laptopshop.entities.ChiTietDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
}

package com.example.laptopshop.repository;

import com.example.laptopshop.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository

public interface SanPhamRepository extends JpaRepository<SanPham, Long>, QuerydslPredicateExecutor<SanPham> {
    List<SanPham> findFirst12ByDanhMucTenDanhMucContainingIgnoreCaseOrderByIdDesc(String tenDanhMuc);
    List<SanPham> findByIdIs(Set<Long> idList);
}


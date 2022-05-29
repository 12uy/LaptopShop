package com.example.laptopshop.repository;

import com.example.laptopshop.entities.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {


}

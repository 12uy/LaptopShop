package com.example.laptopshop.repository;

import com.example.laptopshop.entities.HangSanXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface HangSanXuatRepository extends JpaRepository<HangSanXuat, Long> {

}

package com.example.laptopshop.repository;

import com.example.laptopshop.entities.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {
    VaiTro findByTenVaiTro(String tenVaiTro);

}

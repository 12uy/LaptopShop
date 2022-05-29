package com.example.laptopshop.repository;

import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.VaiTro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

    NguoiDung findByEmail(String email);

    Page<NguoiDung> findByVaiTro (Set<VaiTro> vaiTro, Pageable of);
    List<NguoiDung> findByVaiTro (Set<VaiTro> vaiTro);

}

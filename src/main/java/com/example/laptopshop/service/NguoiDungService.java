package com.example.laptopshop.service;

import com.example.laptopshop.dto.TaiKhoanDTO;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.VaiTro;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface NguoiDungService {
    NguoiDung findByEmail(String email);

    NguoiDung findByConfirmToken(String confirmToken);

    NguoiDung save(NguoiDung nguoiDung);

    NguoiDung update(NguoiDung nguoiDung);

    NguoiDung findById(long id);

    void changePassword(NguoiDung nguoiDung, String newPassword);

    Page<NguoiDung> getNguoiDungByVaiTro(Set<VaiTro> vaiTro, int page);

    List<NguoiDung> getNguoiDungByVaiTro(Set<VaiTro> vaiTro);

    NguoiDung saveNguoiDungByAdmin(TaiKhoanDTO taiKhoanDTO);

    void deleteById(long id);
}

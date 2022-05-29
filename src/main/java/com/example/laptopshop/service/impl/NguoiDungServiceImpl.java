package com.example.laptopshop.service.impl;

import com.example.laptopshop.dto.TaiKhoanDTO;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.VaiTro;
import com.example.laptopshop.repository.NguoiDungRepository;
import com.example.laptopshop.repository.VaiTroRepository;
import com.example.laptopshop.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public NguoiDung findByEmail(String email) {
        return nguoiDungRepository.findByEmail(email);
    }

    @Override
    public NguoiDung findByConfirmToken(String confirmToken) {
        return null;
    }

    @Override
    public NguoiDung save(NguoiDung nguoiDung) {
        nguoiDung.setPassword(bCryptPasswordEncoder.encode(nguoiDung.getPassword()));
        Set<VaiTro> setVaiTro = new HashSet<>();
        setVaiTro.add(vaiTroRepository.findByTenVaiTro("ROLE_MEMBER"));
        nguoiDung.setVaiTro(setVaiTro);
        return nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public NguoiDung update(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public NguoiDung findById(long id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id).get();
        return nguoiDung;
    }

    @Override
    public void changePassword(NguoiDung nguoiDung, String newPassword) {
        nguoiDung.setPassword(bCryptPasswordEncoder.encode(newPassword));
        nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public Page<NguoiDung> getNguoiDungByVaiTro(Set<VaiTro> vaiTro, int page) {
        return nguoiDungRepository.findByVaiTro(vaiTro, PageRequest.of(page, 10));
    }

    @Override
    public List<NguoiDung> getNguoiDungByVaiTro(Set<VaiTro> vaiTro) {
        return nguoiDungRepository.findByVaiTro(vaiTro);
    }

    @Override
    public NguoiDung saveNguoiDungByAdmin(TaiKhoanDTO taiKhoanDTO) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setEmail(taiKhoanDTO.getEmail());
        nguoiDung.setPassword(bCryptPasswordEncoder.encode(taiKhoanDTO.getPassword()));
        nguoiDung.setHoTen(taiKhoanDTO.getHoTen());
        nguoiDung.setDiaChi(taiKhoanDTO.getDiaChi());
        nguoiDung.setSoDienThoai(taiKhoanDTO.getSdt());

        Set<VaiTro> setVaiTro = new HashSet<>();
        setVaiTro.add(vaiTroRepository.findByTenVaiTro(taiKhoanDTO.getTenVaiTro()));
        nguoiDung.setVaiTro(setVaiTro);

        return nguoiDungRepository.save(nguoiDung);

    }

    @Override
    public void deleteById(long id) {
        nguoiDungRepository.deleteById(id);
    }
}

package com.example.laptopshop.config;

import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.VaiTro;
import com.example.laptopshop.repository.NguoiDungRepository;
import com.example.laptopshop.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Vai tro
        if (vaiTroRepository.findByTenVaiTro("ROLE_ADMIN") == null) {
            vaiTroRepository.save(new VaiTro("ROLE_ADMIN"));
        }
        if (vaiTroRepository.findByTenVaiTro("ROLE_MEMBER") == null) {
            vaiTroRepository.save(new VaiTro("ROLE_MEMBER"));
        }
        if (vaiTroRepository.findByTenVaiTro("ROLE_SHIPPER") == null) {
            vaiTroRepository.save(new VaiTro("ROLE_SHIPPER"));
        }

        //Tai khoan admin
        if (nguoiDungRepository.findByEmail("admin@gmail.com") == null) {
            NguoiDung admin = new NguoiDung();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setHoTen("Đỗ Văn Duy");
            admin.setSoDienThoai("0353262068");
            HashSet<VaiTro> setVaiTro = new HashSet<>();
            setVaiTro.add(vaiTroRepository.findByTenVaiTro("ROLE_ADMIN"));
            setVaiTro.add(vaiTroRepository.findByTenVaiTro("ROLE_MEMBER"));
            admin.setVaiTro(setVaiTro);
            nguoiDungRepository.save(admin);
        }

        //Tai khoan member
        if (nguoiDungRepository.findByEmail("member@gmail.com") == null) {
            NguoiDung member = new NguoiDung();
            member.setEmail("member@gmail.com");
            member.setPassword(passwordEncoder.encode("123456"));
            member.setHoTen("Nguyễn Văn A");
            member.setSoDienThoai("1234567");
            HashSet<VaiTro> setVaiTro = new HashSet<>();
            setVaiTro.add(vaiTroRepository.findByTenVaiTro("ROLE_MEMBER"));
            member.setVaiTro(setVaiTro);
            nguoiDungRepository.save(member);
        }
        //Tài khoản shipper
        if (nguoiDungRepository.findByEmail("shipper@gmail.com") == null) {
            NguoiDung shipper = new NguoiDung();
            shipper.setEmail("shipper@gmail.com");
            shipper.setPassword(passwordEncoder.encode("123456"));
            shipper.setHoTen("Nguyễn Văn B");
            shipper.setSoDienThoai("1234567");
            HashSet<VaiTro> setVaiTro = new HashSet<>();
            setVaiTro.add(vaiTroRepository.findByTenVaiTro("ROLE_SHIPPER"));
            shipper.setVaiTro(setVaiTro);
            nguoiDungRepository.save(shipper);
        }
    }
}

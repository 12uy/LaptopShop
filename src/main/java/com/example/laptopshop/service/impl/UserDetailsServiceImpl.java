package com.example.laptopshop.service.impl;

import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.VaiTro;
import com.example.laptopshop.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(username);
        if (nguoiDung == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<VaiTro> vaiTros = nguoiDung.getVaiTro();
        for (VaiTro vaiTro : vaiTros) {
            grantedAuthorities.add(new SimpleGrantedAuthority(vaiTro.getTenVaiTro()));
        }
        return new User(username, nguoiDung.getPassword(), grantedAuthorities);
    }
}

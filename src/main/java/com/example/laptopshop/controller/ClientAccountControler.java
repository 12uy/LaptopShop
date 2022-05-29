package com.example.laptopshop.controller;

import com.example.laptopshop.dto.PasswordDTO;
import com.example.laptopshop.entities.DonHang;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.ResponseObject;
import com.example.laptopshop.service.DonHangService;
import com.example.laptopshop.service.NguoiDungService;
import com.example.laptopshop.service.SanPhamService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("LoggedInUser")
@RequestMapping("/")
public class ClientAccountControler {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private PasswordEncoder BCryptPasswordEncoder;

    @ModelAttribute("LoggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return nguoiDungService.findByEmail(auth.getName());
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("LoggedInUser");
    }

    @GetMapping("/account")
    public String accountPage(HttpServletRequest res, Model model) {
        NguoiDung currentUser = getSessionUser(res);
        model.addAttribute("user", currentUser);
        List<DonHang> list = (donHangService.getDonHangByNguoiDung(currentUser));
        model.addAttribute("list", list);
        return "client/account";
    }

    @GetMapping("/changeInformation")
    public String changeInformationPage(HttpServletRequest res, Model model) {
        NguoiDung currentUser = getSessionUser(res);
        model.addAttribute("user", currentUser);
        return "client/changeInformation";
    }

    @GetMapping("/changePassword")
    public String clientChangePasswordPage() {
        return "client/changePassword";
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public ResponseObject commitChange(@RequestBody NguoiDung user, HttpServletRequest res) {
        NguoiDung currentUser = getSessionUser(res);
        currentUser.setHoTen(user.getHoTen());
        currentUser.setDiaChi(user.getDiaChi());
        currentUser.setSoDienThoai(user.getSoDienThoai());
        nguoiDungService.update(currentUser);
        return new ResponseObject();
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseObject commitChangePassword(@RequestBody PasswordDTO dto, HttpServletRequest res) {
        NguoiDung currentUser = getSessionUser(res);
        if (BCryptPasswordEncoder.matches(dto.getOldPassword(), currentUser.getPassword())) {
            ResponseObject response = new ResponseObject();
            response.setStatus("old");
            return response;
        }
        nguoiDungService.changePassword(currentUser, dto.getNewPassword());
        return new ResponseObject();
    }
}

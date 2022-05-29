package com.example.laptopshop.controller;

import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/shipper")
@SessionAttributes("loggedInUser")
public class ShipperController {
    @Autowired
    private NguoiDungService nguoiDungService;


    @ModelAttribute("loggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return nguoiDungService.findByEmail(auth.getName());
    }


    @GetMapping(value= {"", "/don-hang"})
    public String shipperPage(Model model) {
        return "shipper/quanLyDonHang";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpServletRequest request) {
        model.addAttribute("user", getSessionUser(request));
        System.out.println(getSessionUser(request).toString());
        return "shipper/profile";
    }

    @PostMapping("/profile/update")
    public String updateNguoiDung(@ModelAttribute NguoiDung nd, HttpServletRequest request) {
        NguoiDung currentUser = getSessionUser(request);
        currentUser.setDiaChi(nd.getDiaChi());
        currentUser.setHoTen(nd.getHoTen());
        currentUser.setSoDienThoai(nd.getSoDienThoai());
        nguoiDungService.update(currentUser);
        return "redirect:/shipper/profile";
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }
}

package com.example.laptopshop.controller;

import com.example.laptopshop.entities.ChiMucGioHang;
import com.example.laptopshop.entities.GioHang;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.SanPham;
import com.example.laptopshop.service.ChiMucGioHangService;
import com.example.laptopshop.service.GioHangService;
import com.example.laptopshop.service.NguoiDungService;
import com.example.laptopshop.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SessionAttributes("LogginedUser")
public class CartController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private ChiMucGioHangService chiMucGioHangService;

    @ModelAttribute("loggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return nguoiDungService.findByEmail(auth.getName());
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/cart")
    public String cartPage(HttpServletRequest res, Model model) {
        NguoiDung currentUser = getSessionUser(res);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<Long, String> quanity = new HashMap<Long, String>();
        List<SanPham> listsp = new ArrayList<SanPham>();
        if (auth == null || auth.getPrincipal() == "anonymousUser")     //Lay tu cookie
        {
            Cookie cl[] = res.getCookies();
            Set<Long> idList = new HashSet<Long>();
            for (int i = 0; i < cl.length; i++) {
                if (cl[i].getName().matches("[0-9]+")) {
                    idList.add(Long.parseLong(cl[i].getName()));
                    quanity.put(Long.parseLong(cl[i].getName()), cl[i].getValue());
                }

            }
            listsp = sanPhamService.getAllSanPhamByList(idList);
        } else     //Lay tu database
        {
            GioHang g = gioHangService.getGioHangByNguoiDung(currentUser);
            if (g != null) {
                List<ChiMucGioHang> listchimuc = chiMucGioHangService.getChiMucGioHangByGioHang(g);
                for (ChiMucGioHang c : listchimuc) {
                    listsp.add(c.getSanPham());
                    quanity.put(c.getSanPham().getId(), Integer.toString(c.getSo_luong()));
                }
            }
        }
        model.addAttribute("checkEmpty", listsp.size());
        model.addAttribute("cart", listsp);
        model.addAttribute("quanity", quanity);


        return "client/cart";
    }

}

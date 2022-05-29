package com.example.laptopshop.controller;

import com.example.laptopshop.dto.ListCongViecDTO;
import com.example.laptopshop.entities.NguoiDung;
import com.example.laptopshop.entities.VaiTro;
import com.example.laptopshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {
    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private HangSanXuatService hangSXService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private VaiTroService vaiTroService;

    @Autowired
    private LienHeService lienHeService;

    @Autowired
    private DonHangService donHangService;

    @ModelAttribute("loggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return nguoiDungService.findByEmail(auth.getName());
    }

    @GetMapping
    public String adminPage(Model model) {
        ListCongViecDTO listCongViec = new ListCongViecDTO();
        listCongViec.setSoDonHangMoi(donHangService.countByTrangThaiDonHang("Đang chờ giao"));
        listCongViec.setSoDonhangChoDuyet(donHangService.countByTrangThaiDonHang("Chờ duyệt"));
        listCongViec.setSoLienHeMoi(lienHeService.countByTrangThaiLienHe("Đang chờ trả lời"));

        model.addAttribute("listCongViec", listCongViec);
        return "admin/trangAdmin";
    }

    @GetMapping("/danh-muc")
    public String quanLyDanhMucPage() {
        return "admin/quanLyDanhMuc";
    }

    @GetMapping("/nhan-hieu")
    public String quanLyNhanHieuPage() {
        return "admin/quanLyNhanHieu";
    }

    @GetMapping("/lien-he")
    public String quanLyLienHePage() {
        return "admin/quanLyLienHe";
    }

    @GetMapping("/san-pham")
    public String quanLySanPhamPage(Model model) {
        model.addAttribute("listNhanHieu", hangSXService.getAllHangSanXuat());
        model.addAttribute("listDanhMuc", danhMucService.getAllDanhMuc());
        return "admin/quanLySanPham";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpServletRequest request) {
        model.addAttribute("user", getSessionUser(request));
        return "admin/profile";
    }

    @PostMapping("/profile/update")
    public String updateNguoiDung(@ModelAttribute NguoiDung nd, HttpServletRequest request) {
        NguoiDung currentUser = getSessionUser(request);
        currentUser.setDiaChi(nd.getDiaChi());
        currentUser.setHoTen(nd.getHoTen());
        currentUser.setSoDienThoai(nd.getSoDienThoai());
        nguoiDungService.update(currentUser);
        return "redirect:/admin/profile";
    }

    @GetMapping("/don-hang")
    public String quanLyDonHangPage(Model model) {
        Set<VaiTro> vaiTro = new HashSet<>();
        // lấy danh sách shipper
        vaiTro.add(vaiTroService.finhByTenVaiTro("ROLE_SHIPPER"));
        List<NguoiDung> shippers = nguoiDungService.getNguoiDungByVaiTro(vaiTro);
        for (NguoiDung shipper : shippers) {
            shipper.setListDonHang(donHangService.findByTrangThaiDonHangAndShipper("Đang giao", shipper));
        }
        model.addAttribute("allShipper", shippers);
        return "admin/quanLyDonHang";
    }

    @GetMapping("/tai-khoan")
    public String quanLyTaiKhoanPage(Model model) {
        model.addAttribute("listVaiTro", vaiTroService.findAll());
        return "admin/quanLyTaiKhoan";
    }

    @GetMapping("/thong-ke")
    public String thongKePage(Model model) {
        return "admin/thongKe";
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }


}

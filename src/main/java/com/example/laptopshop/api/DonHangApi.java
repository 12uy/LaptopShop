package com.example.laptopshop.api;

import com.example.laptopshop.dto.SearchDonHangObject;
import com.example.laptopshop.entities.ChiTietDonHang;
import com.example.laptopshop.entities.DonHang;
import com.example.laptopshop.entities.SanPham;
import com.example.laptopshop.service.DonHangService;
import com.example.laptopshop.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/don-hang")
public class DonHangApi {

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // lấy danh sách đơn hàng theo search object
    @GetMapping("/all")
    public Page<DonHang> getDonHangByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String trangThai,
                                            @RequestParam String tuNgay, @RequestParam String denNgay) throws ParseException {

        SearchDonHangObject object = new SearchDonHangObject();
        object.setDenNgay(denNgay);
        object.setTrangThaiDonHang(trangThai);
        object.setTuNgay(tuNgay);
        Page<DonHang> listDonHang = donHangService.getAllDonHangByFilter(object, page);
        return listDonHang;
    }

    @GetMapping("/{id}")
    public DonHang getDonHangById(@PathVariable long id) {
        return donHangService.getDonHangById(id);
    }

    // phân công đơn hàng
    @PostMapping("/assign")
    public void phanCongDonHang(@RequestParam("shipper") String emailShipper,
                                @RequestParam("donHangId") long donHangId) {
        DonHang dh = donHangService.getDonHangById(donHangId);
        dh.setTrangThaiDonHang("Đang giao");
        dh.setShipper(nguoiDungService.findByEmail(emailShipper));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            String dateStr = format.format(new Date());
            Date date = format.parse(dateStr);
            dh.setNgayGiaoHang(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        donHangService.saveDonHang(dh);
    }

    // xác nhận hoàn thành đơn hàng
    @PostMapping("/update")
    public void xacNhanHoanThanhDon(@RequestParam("donHangId") long donHangId,
                                    @RequestParam("ghiChu") String ghiChuAdmin) {
        DonHang dh = donHangService.getDonHangById(donHangId);

        for(ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
            SanPham sp = ct.getSanPham();
            sp.setDonViBan(sp.getDonViBan() + ct.getSoLuongNhanHang());
            sp.setDonViKho(sp.getDonViKho() - ct.getSoLuongNhanHang() );
        }
        dh.setTrangThaiDonHang("Hoàn thành");
        String ghiChu = dh.getGhiChu();
        if (!ghiChuAdmin.equals("")) {
            ghiChu += "<br> Ghi chú admin:\n" + ghiChuAdmin;
        }
        dh.setGhiChu(ghiChu);
        donHangService.saveDonHang(dh);
    }

    // xác nhận hoàn thành đơn hàng
    @PostMapping("/cancel")
    public void huyDonHangAdmin(@RequestParam("donHangId") long donHangId) {
        DonHang dh = donHangService.getDonHangById(donHangId);
        dh.setTrangThaiDonHang("Đã bị hủy");
        donHangService.saveDonHang(dh);
    }

    // lấy dữ liệu làm báo cáo thống kê
    @GetMapping("/report")
    public List<Object> test() {
        return donHangService.getDonHangTheoThangVaNam();
    }
}

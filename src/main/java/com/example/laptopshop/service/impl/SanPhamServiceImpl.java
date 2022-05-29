package com.example.laptopshop.service.impl;

import com.example.laptopshop.dto.SanPhamDTO;
import com.example.laptopshop.dto.SearchSanPhamObject;
import com.example.laptopshop.entities.QSanPham;
import com.example.laptopshop.entities.SanPham;
import com.example.laptopshop.repository.DanhMucRepository;
import com.example.laptopshop.repository.HangSanXuatRepository;
import com.example.laptopshop.repository.SanPhamRepository;
import com.example.laptopshop.service.SanPhamService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private HangSanXuatRepository hangSanXuatRepository;

    //đổi từ SanPhamDTO sang SanPham để lưu vào database
    public SanPham convertSanPhamDTOtoSanPham(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = new SanPham();

        if (sanPhamDTO.getId() != null) {
            sanPham.setId(Long.parseLong(sanPhamDTO.getId()));
        }
        sanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
        sanPham.setCpu(sanPhamDTO.getCpu());
        sanPham.setRam(sanPhamDTO.getRam());
        sanPham.setDanhMuc(danhMucRepository.findById(sanPhamDTO.getDanhMucId()).get());
        sanPham.setHangSanXuat(hangSanXuatRepository.findById(sanPhamDTO.getNhaSXId()).get());
        sanPham.setDonGia(Long.parseLong(sanPhamDTO.getDonGia()));
        sanPham.setThietKe(sanPhamDTO.getThietKe());
        sanPham.setThongTinBaoHanh(sanPhamDTO.getThongTinBaoHanh());
        sanPham.setThongTinChung(sanPhamDTO.getThongTinChung());
        sanPham.setManHinh(sanPhamDTO.getManHinh());
        sanPham.setHeDieuHanh(sanPhamDTO.getHeDieuHanh());
        sanPham.setDungLuongPin(sanPhamDTO.getDungLuongPin());
        sanPham.setDonViKho(Integer.parseInt(sanPhamDTO.getDonViKho()));

        return sanPham;
    }

    @Override
    public SanPham save(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = convertSanPhamDTOtoSanPham(sanPhamDTO);
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = convertSanPhamDTOtoSanPham(sanPhamDTO);
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void deleteById(long id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public Page<SanPham> getAllSanPhamByFilter(SearchSanPhamObject searchSanPhamObject, int page, int size) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String price = searchSanPhamObject.getDonGia();

        //sắp xếp theo giá
        Sort sort = Sort.by(Sort.Direction.ASC, "donGia"); //Mặc định sắp xếp theo giá tăng dần
        if (searchSanPhamObject.getSort().equals("desc")) { //Nếu người dùng click vào sắp xếp giảm dần
            sort = Sort.by(Sort.Direction.DESC, "donGia");
        }

        if (!searchSanPhamObject.equals("") && searchSanPhamObject.getDanhMucId() !=null){
            booleanBuilder.and(QSanPham.sanPham.danhMuc.id.eq(danhMucRepository.findById(Long.valueOf(searchSanPhamObject.getDanhMucId())).get().getId()));
        }

        if (!searchSanPhamObject.equals("") && searchSanPhamObject.getHangSXId() !=null){
            booleanBuilder.and(QSanPham.sanPham.hangSanXuat.id.eq(hangSanXuatRepository.findById(Long.valueOf(searchSanPhamObject.getHangSXId())).get().getId()));
        }

        //tìm theo đơn giá
        switch (price) {
            //tìm theo giá dưới 2 triệu
            case "duoi-2-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;
            //tìm theo giá từ 2 triệu đến 4 triệu
            case "2-trieu-den-4-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;
                //tìm theo giá từ 4 triệu đến 6 triệu
            case "4-trieu-den-6-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;
                //tìm theo giá từ 6 triệu đến 8 triệu
            case "6-trieu-den-8-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(6000000, 8000000));
                break;
                //tìm theo giá từ 8 triệu đến 10 triệu
            case "8-trieu-den-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(8000000, 10000000));
                break;
                //tìm theo giá trên 10 triệu
            case "tren-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;
            default:
                break;

        }
        return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page, size, sort));
    }

    @Override
    public SanPham getSanPhamById(long id) {
        return sanPhamRepository.findById(id).get();
    }

    @Override
    public List<SanPham> getLastestSanPham() {
        return sanPhamRepository.findFirst12ByDanhMucTenDanhMucContainingIgnoreCaseOrderByIdDesc("Laptop");
    }

    @Override
    public Iterable<SanPham> getSanPhamByTenSanPhamWithoutPaginate(SearchSanPhamObject searchSanPhamObject) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        int resultPerPage = 12;
        String[] keyword = searchSanPhamObject.getKeyword();
        String sort = searchSanPhamObject.getSort();
        String price = searchSanPhamObject.getDonGia();
        //Keyword
        booleanBuilder.and(QSanPham.sanPham.tenSanPham.like("%" + keyword[0] + "%"));
        if (keyword.length > 1) {
            for (int i = 1; i < keyword.length; i++) {
                booleanBuilder.and(QSanPham.sanPham.tenSanPham.like("%" + keyword[i] + "%"));
            }
        }
        //mục giá
        switch (price){
            case "duoi-2-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;
            case "2-trieu-den-4-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;
            case "4-trieu-den-6-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;
            case "6-trieu-den-8-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(6000000, 8000000));
                break;
            case "8-trieu-den-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(8000000, 10000000));
                break;
            case "tren-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;
            default:
                break;
        }
        return sanPhamRepository.findAll(booleanBuilder);
    }

    @Override
    public Page<SanPham> getSanPhamByTenSanPham(SearchSanPhamObject searchSanPhamObject, int page, int size) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        int resultPerPage = 12;
        String[] keyword = searchSanPhamObject.getKeyword();
        String sort = searchSanPhamObject.getSort();
        String price = searchSanPhamObject.getDonGia();
        String brand = searchSanPhamObject.getHangSXId();
        String manufacturer = searchSanPhamObject.getDanhMucId();

        //Keyword
        booleanBuilder.and(QSanPham.sanPham.tenSanPham.like("%" + keyword[0] + "%"));
        if (keyword.length > 1) {
            for (int i = 1; i < keyword.length; i++) {
                booleanBuilder.and(QSanPham.sanPham.tenSanPham.like("%" + keyword[i] + "%"));
            }
        }
        //mục giá
switch (price){
            case "duoi-2-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;
            case "2-trieu-den-4-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;
            case "4-trieu-den-6-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;
            case "6-trieu-den-8-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(6000000, 8000000));
                break;
            case "8-trieu-den-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(8000000, 10000000));
                break;
            case "tren-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;
            default:
                break;
        }

        //danh mục và nhà sản xuat
        if(brand.length() > 1){
            booleanBuilder.and(QSanPham.sanPham.danhMuc.tenDanhMuc.eq(brand));
        }
        if(manufacturer.length() > 1){
            booleanBuilder.and(QSanPham.sanPham.hangSanXuat.tenHangSanXuat.eq(manufacturer));
        }


        //sắp xếp
        if (sort.equals("newest")){
            return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page -1,size, Sort.Direction.DESC,"id"));
        } else if (sort.equals("priceAsc")){
            return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page -1,size, Sort.Direction.ASC,"donGia"));
        } else if (sort.equals("priceDesc")){
            return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page -1,size, Sort.Direction.DESC,"donGia"));
        }
        return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page -1,size));
    }

    @Override
    public List<SanPham> getAllSanPhamByList(Set<Long> listId) {
        return sanPhamRepository.findAllById(listId);
    }

    @Override
    public Page<SanPham> getSanPhamForAdmin(String tenSanPam, int page, int size) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QSanPham.sanPham.tenSanPham.like("%" + tenSanPam + "%"));
        return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page -1,size));
    }

    @Override
    public Iterable<SanPham> getSanPhamByDanhMuc(String brand) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QSanPham.sanPham.danhMuc.tenDanhMuc.eq(brand));
        return sanPhamRepository.findAll(booleanBuilder);
    }

    @Override
    public Page<SanPham> getSanPhamByBrand(SearchSanPhamObject searchSanPhamObject, int page, int size) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String price = searchSanPhamObject.getDonGia();
        String brand = searchSanPhamObject.getBrand();
        String manufacturer = searchSanPhamObject.getManufactor();
        String os = searchSanPhamObject.getOs();
        String ram = searchSanPhamObject.getRam();
        String pin = searchSanPhamObject.getPin();

        //mục giá
        switch (price) {
            case "duoi-2-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;
            case "2-trieu-den-4-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;
            case "4-trieu-den-6-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;
            case "6-trieu-den-8-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(6000000, 8000000));
                break;
            case "8-trieu-den-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.between(8000000, 10000000));
                break;
            case "tren-10-trieu":
                booleanBuilder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;
            default:
                break;
        }

        //Danh mục và nhà sản xuất
        if (brand.length() > 1) {
            booleanBuilder.and(QSanPham.sanPham.hangSanXuat.tenHangSanXuat.eq(brand));
        }
        if (manufacturer.length() > 1) {
            booleanBuilder.and(QSanPham.sanPham.hangSanXuat.tenHangSanXuat.eq(manufacturer));
        }
        if (os.length() > 1) {
            booleanBuilder.and(QSanPham.sanPham.heDieuHanh.like("%" + os + "%"));
        }
        if (ram.length() > 1) {
            booleanBuilder.and(QSanPham.sanPham.ram.like("%" + ram + "%"));
        }
        if (pin.length() > 1) {
            booleanBuilder.and(QSanPham.sanPham.dungLuongPin.eq(pin));
        }
        return sanPhamRepository.findAll(booleanBuilder, PageRequest.of(page -1,size));
    }
}

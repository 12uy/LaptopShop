package com.example.laptopshop.api;

import com.example.laptopshop.dto.LienHeDTO;
import com.example.laptopshop.dto.SearchLienHeObject;
import com.example.laptopshop.entities.LienHe;
import com.example.laptopshop.entities.ResponseObject;
import com.example.laptopshop.service.LienHeService;
import com.example.laptopshop.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lien-he")
public class LienHeApi {

    @Autowired
    private LienHeService lienHeService;


    @Autowired
    private EmailUtil emailUtil;

    @GetMapping("/all")
    public Page<LienHe> getLienHeByFilter(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam String trangThaiLienHe, @RequestParam String tuNgay, @RequestParam String denNgay)
            throws ParseException {

        SearchLienHeObject object = new SearchLienHeObject();
        object.setDenNgay(denNgay);
        object.setTrangThaiLienHe(trangThaiLienHe);
        object.setTuNgay(tuNgay);

        Page<LienHe> listLienHe = lienHeService.getLienHeByFilter(object, page);
        return listLienHe;
    }

    @GetMapping("/{id}")
    public LienHe getLienHeById(@PathVariable long id) {
        return lienHeService.findById(id);
    }

    @PostMapping("/reply")
    public ResponseObject tradLoiLienHeProcess(@RequestBody @Valid LienHeDTO dto, BindingResult result) {

        ResponseObject ro = new ResponseObject();

        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);

//			List<String> keys = new ArrayList<String>(errors.keySet());
//			for (String key : keys) {
//				System.out.println(key + ": " + errors.get(key));
//			}

            ro.setStatus("fail");
        } else {
            System.out.println(dto.toString());

            emailUtil.sendEmail(dto.getDiaChiDen(), dto.getTieuDe(), dto.getNoiDungTraLoi());

            LienHe lienHe = lienHeService.findById(dto.getId());
            lienHe.setTrangThai("Đã trả lời");
            lienHe.setNgayTraLoi(new Date());
            lienHe.setNoiDungTraLoi(dto.getNoiDungTraLoi());

            lienHeService.saveLienHe(lienHe);
            ro.setStatus("success");
        }
        return ro;

    }
}

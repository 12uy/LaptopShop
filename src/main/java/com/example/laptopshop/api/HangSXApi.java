package com.example.laptopshop.api;

import com.example.laptopshop.entities.HangSanXuat;
import com.example.laptopshop.entities.ResponseObject;
import com.example.laptopshop.service.HangSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhan-hieu")
public class HangSXApi {

    @Autowired
    private HangSanXuatService service;

    @GetMapping("/all")
    public Page<HangSanXuat> getAllHangSanXuat(@RequestParam(defaultValue = "1") int page) {
        return service.getAllHangSanXuatForPageable(page-1,6);
    }

    @GetMapping("/{id}")
    public HangSanXuat getHangSanXuatById(@PathVariable long id) {
        return service.getHangSanXuatById(id);
    }

    @PostMapping(value = "/save")
    public ResponseObject addHangSanXuat(@RequestBody @Valid HangSanXuat newHangSanXuat, BindingResult result) {

        ResponseObject ro = new ResponseObject();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);
            ro.setStatus("fail");
        } else {
            service.save(newHangSanXuat);
            ro.setData(newHangSanXuat);
            ro.setStatus("success");
        }
        return ro;
    }

    @PutMapping(value = "/update")
    public ResponseObject updateHangSanXuat(@RequestBody @Valid HangSanXuat editHangSanXuat, BindingResult result) {

        ResponseObject ro = new ResponseObject();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);
            ro.setStatus("fail");
            errors = null;
        } else {
            service.update(editHangSanXuat);
            ro.setData(editHangSanXuat);
            ro.setStatus("success");
        }

        return ro;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHangSanXuat(@PathVariable long id) {
        service.delete(id);
        return "OK !";
    }
}
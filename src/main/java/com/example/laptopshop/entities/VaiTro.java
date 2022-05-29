package com.example.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tenVaiTro;
    @JsonIgnore
    @ManyToMany(mappedBy = "vaiTro")
    private Set<NguoiDung> nguoiDung;

    public VaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public VaiTro() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public Set<NguoiDung> getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(Set<NguoiDung> nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

}

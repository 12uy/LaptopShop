package com.example.laptopshop.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class LienHeDTO {
    private long id;

    @NotEmpty(message="Nội dung trả lời không được trống")
    private String noiDungTraLoi;

    private String tieuDe;
    private String diaChiDen;

    private Date ngayTraLoi;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoiDungTraLoi() {
        return noiDungTraLoi;
    }

    public void setNoiDungTraLoi(String noiDungTraLoi) {
        this.noiDungTraLoi = noiDungTraLoi;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getDiaChiDen() {
        return diaChiDen;
    }

    public void setDiaChiDen(String diaChiDen) {
        this.diaChiDen = diaChiDen;
    }

    public Date getNgayTraLoi() {
        return ngayTraLoi;
    }

    public void setNgayTraLoi(Date ngayTraLoi) {
        this.ngayTraLoi = ngayTraLoi;
    }

    @Override
    public String toString() {
        return "LienHeDTO{" +
                "id=" + id +
                ", noiDungTraLoi='" + noiDungTraLoi + '\'' +
                ", tieuDe='" + tieuDe + '\'' +
                ", diaChiDen='" + diaChiDen + '\'' +
                ", ngayTraLoi=" + ngayTraLoi +
                '}';
    }
}

package com.example.datnsum24sd01.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class NhanVienRequest {
    private Long id;
    private String ma;
    private String ten;
    private boolean gioiTinh;
    private String sdt;
    private String email;
    private String diachi;
    private String canCuocCongDan;
//    private String matKhau;
    private String cccd;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private int trangThai;
}

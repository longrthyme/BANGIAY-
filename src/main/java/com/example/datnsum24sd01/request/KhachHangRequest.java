package com.example.datnsum24sd01.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangRequest {
    private Long id;
    private String email;
    private String gioiTinh;
    private String ma;
    private String matKhau;
    private Date ngaySua;
    private Date ngayTao;
    private String sdt;
    private String ten;
    private Byte trangThai;
    private BigDecimal tichDiem;
    private Date ngaySinh;
}

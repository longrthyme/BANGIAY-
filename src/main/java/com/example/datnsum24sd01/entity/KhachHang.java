package com.example.datnsum24sd01.entity;

import com.example.datnsum24sd01.enumation.GioiTinh;
import com.example.datnsum24sd01.enumation.TrangThai;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.EnumType.ORDINAL;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "gioi_tinh")
    @Enumerated(ORDINAL)
    private GioiTinh gioiTinh;

    @Column(name = "ma", nullable = true, unique = true)
    private String ma;

    @Column(name = "mat_khau", nullable = true)
    private String matKhau;
    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "sdt", nullable = true, unique = true)
    private String sdt;

    @Column(name = "ten", nullable = true)
    private String ten;

    @Column(name = "trang_thai", nullable = true)
    private Byte trangThai;

    @Column(name = "tich_diem", precision = 65, scale = 2, nullable = true)
    private BigDecimal tichDiem;

    @Column(name = "ngay_sinh", nullable = true)
    private Date ngaySinh;


    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<DiaChi> listDiaChi;

    public String getMainDiaChi(){

        return this.listDiaChi.get(0).getDiaChi();
    }


}
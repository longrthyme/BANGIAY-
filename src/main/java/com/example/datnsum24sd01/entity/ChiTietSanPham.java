package com.example.datnsum24sd01.entity;

import com.example.datnsum24sd01.enumation.TrangThai;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.ORDINAL;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham", referencedColumnName = "id")
    @JsonIgnore
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinColumn(name = "id_de_giay", referencedColumnName = "id")
    @JsonManagedReference
    private DeGiay deGiay;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_mau_sac", referencedColumnName = "id")
    @JsonManagedReference
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_kich_thuoc", referencedColumnName = "id")
    @JsonManagedReference
    private KichThuoc kichThuoc;
    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "gia_mac_dinh", precision = 19, scale = 2)
    private BigDecimal giaMacDinh;

    @Column(name = "gia_ban", precision = 19, scale = 2)
    private BigDecimal giaBan;


    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;
    @Column(name = "anh_chinh")
    private String anhChinh;

    @Column(name = "qr")
    private String qr;
    @Column(name = "trang_thai")
    @Enumerated(EnumType.ORDINAL)
    private TrangThai trangThai;



}
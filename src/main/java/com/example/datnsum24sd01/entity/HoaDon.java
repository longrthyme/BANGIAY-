package com.example.datnsum24sd01.entity;

import com.example.datnsum24sd01.enumation.LoaiHoaDon;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
//
//import static jakarta.persistence.EnumType.ORDINAL;
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "hoa_don")
//public class HoaDon {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_nhan_vien", referencedColumnName = "id")
//    private NhanVien nhanVien;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
//    private KhachHang khachHang;
//
//    @Column(name = "ma", unique = true)
//    private String ma;
//
//    @Column(name = "ngay_tao")
//    private LocalDate ngayTao;
//
//    @Column(name = "ngay_thanh_toan")
//    private LocalDate ngayThanhToan;
//
//    @Column(name = "ngay_sua")
//    private LocalDate ngaySua;
//
//    @Column(name = "ngay_giao")
//    private LocalDate ngayGiao;
//
//    @Column(name = "dia_chi")
//    private String diaChi;
//
//    @Column(name = "phi_van_chuyen")
//    private BigDecimal phiVanChuyen;
//
//    @Column(name = "tong_tien", precision = 19, scale = 2)
//    private BigDecimal tongTien;
//
//    @Column(name = "thanh_toan", precision = 19, scale = 2)
//    private BigDecimal thanhToan;
//
//    @Column(name = "tien_giam_gia", precision = 19, scale = 2)
//    private BigDecimal tienGiamGia;
//
//    @Column(name = "xu", precision = 19, scale = 2)
//    private BigDecimal xu;
//
//    @Column(name = "ten_khach_hang")
//    private String tenKhachHang;
//
//    @Column(name = "sdt")
//    private String sdt;
//
//    @Column(name = "ghi_chu")
//    private String ghiChu;
//
//    @Column(name = "trang_thai")
//    @Enumerated(ORDINAL)
//    private TrangThaiDonHang trangThai;
//
//    @Column(name = "loai_hoa_don")
//    @Enumerated(ORDINAL)
//    private LoaiHoaDon loaiHoaDon;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "id_phieu_giam_gia", referencedColumnName = "id", nullable = true)
////    private PhieuGiamGia maGiamGia;
//
//}
import static jakarta.persistence.EnumType.ORDINAL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien", referencedColumnName = "id")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    @Column(name = "ma", unique = true)
    private String ma;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "ngay_giao")
    private LocalDate ngayGiao;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "phi_van_chuyen")
    private BigDecimal phiVanChuyen;

    @Column(name = "tong_tien", precision = 19, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "thanh_toan", precision = 19, scale = 2)
    private BigDecimal thanhToan;

    @Column(name = "tien_giam_gia", precision = 19, scale = 2)
    private BigDecimal tienGiamGia;

    @Column(name = "xu", precision = 19, scale = 2)
    private BigDecimal xu;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    @Enumerated(ORDINAL)
    private TrangThaiDonHang trangThai;

    @Column(name = "loai_hoa_don")
    @Enumerated(ORDINAL)
    private LoaiHoaDon loaiHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phieu_giam_gia", referencedColumnName = "id", nullable = true)
    private PhieuGiamGia maGiamGia;

}
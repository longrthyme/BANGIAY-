package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.entity.HoaDonChiTiet;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.PhieuGiamGia;

import java.math.BigDecimal;
import java.util.List;

public interface  BanHangService {
    List<HoaDon> getHoaDonCho();

    List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(Long idHoaDon);

    HoaDon getOneById(Long idHoaDon);

    ChiTietSanPham getChiTietSanPhamById(Long idChiTietSanPham);

    List<ChiTietSanPham> getChiTietSanPham();

    HoaDon themHoaDon(HoaDon hoaDon,Long idNhanVien);

    HoaDonChiTiet taoHoaDonChiTiet(Long idSanPham,Long idHoaDon, HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet getOneByIdHDCT(Long idHDCT);

    HoaDonChiTiet xoaHoaDonChiTiet(Long idHoaDonChiTiet);

    HoaDon thanhToanHoaDon(Long idHoaDon, BigDecimal tienGiamGia);

    HoaDon checkXuHoaDon(Long idHoaDon, String tongTien, String thanhTien, Boolean xuTichDiem);

    BigDecimal voucher(Long idHoaDon, BigDecimal tongTien);

    Integer checkVoucher(Long idHoaDon, Long idMaGiamGia, BigDecimal tongTien);

    BigDecimal getTongTien(Long idHoaDon);

    BigDecimal getThanhTien(Long idHoaDon, BigDecimal tongTien);

    ChiTietSanPham updateSoLuong(Long idSanPham, Integer soLuong);

    ChiTietSanPham updateSoLuongTuHDCT(Long idHDCT);

    HoaDon updateKhachHang(Long idHoaDon, Long idKhachHang);


    PhieuGiamGia updateGiamGia(Long idHoaDon);



    HoaDonChiTiet tangSoLuongSanPham(Long idHDCT, Integer soLuong);

    HoaDonChiTiet tangSoLuongSanPhamHoaDon(Long idHDCT, Integer soLuong);

    HoaDonChiTiet giamSoLuongSanPhamHoaDon(Long idHDCT, Integer soLuong);

    ChiTietSanPham suaSoLuongSanPham(Long idHDCT);

    Boolean huyDon(Long idHoaDon);

    KhachHang tichDiem(Long idKhachHang, String thanhTien);



}

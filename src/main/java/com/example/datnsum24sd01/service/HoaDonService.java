package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAll();

    List<HoaDon> getAllByKhachHang(Long id);


    List<HoaDon> getByTrangThai(TrangThaiDonHang trangThai);

    List<HoaDon> getByTrangThaiAndKhachHang(TrangThaiDonHang trangThai,Long id);

    HoaDon findById(Long id);

    HoaDon save(HoaDon hoaDon);

    List<HoaDon> findHoaDonsByNgayTao(LocalDate start, LocalDate end);

    boolean validate(HoaDon hoaDon, TrangThaiDonHang trangThai, String newGhiChu);


    HoaDon createHdHoanTra(HoaDon hoaDon,Long idHd);

    boolean removeGioHangChiTietHoanTra(GioHangChiTiet gioHangChiTiet, HoaDon hoaDon);


    ChiTietSanPham refund(GioHangChiTiet gioHangChiTiet);

    BigDecimal maGiamGia(Long idHd);

    public HoaDon findByMa(String maHd);



}

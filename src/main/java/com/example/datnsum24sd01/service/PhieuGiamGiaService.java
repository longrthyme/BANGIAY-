package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import com.example.datnsum24sd01.request.PhieuGiamGiaRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface PhieuGiamGiaService {
    List<PhieuGiamGia> getAll();

    List<PhieuGiamGia> getListHoatDong();

    List<PhieuGiamGia> getByTrangThai(TrangThaiPhieuKhuyenMai trangThaiKhuyenMai);
    String delete(Long id);
    void updateTrangThai();

    PhieuGiamGia add(PhieuGiamGiaRequest phieuGiamGiaRequest);

    PhieuGiamGia getById(Long id);

    PhieuGiamGia update(PhieuGiamGiaRequest phieuGiamGiaRequest);

    void huy(Long id);
    void bat(Long id);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);

    List<PhieuGiamGia> layList(Long tongGiaTri);
    List<PhieuGiamGia> findMaGiamGia(LocalDate start, LocalDate end, TrangThaiPhieuKhuyenMai trangThaiKhuyenMai);
}

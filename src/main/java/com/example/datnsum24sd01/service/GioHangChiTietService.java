package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.GioHangChiTiet;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietService {
    List<GioHangChiTiet> getAll();

    List<GioHangChiTiet> getAll(Long idKhachHang);

    List<GioHangChiTiet> findGioHangChiTietById(Long id);

    Optional<GioHangChiTiet> getOne(Long id);

    GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);
}

package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.ChiTietSanPhamRequest;

import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAll();

    ChiTietSanPham getById(Long id);

    List<ChiTietSanPham> getAllChiTietSanPham();

    ChiTietSanPham add(ChiTietSanPhamRequest chiTietSanPhamRequest);

    String delete(Long id);

    ChiTietSanPham getOne(Long id);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void thayDoiTrangThai(Long id);

    ChiTietSanPham findByColorAndSize(Long colorId, Long sizeId, Long productId);

}

package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.DongSanPham;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.DongSanPhamRequest;

import java.util.List;

public interface DongSanPhamService {
    List<DongSanPham> getList();

    List<DongSanPham> getByTrangThai(TrangThai trangThai);

    DongSanPham save(DongSanPhamRequest request);

    DongSanPham update(DongSanPhamRequest request);

    void remove(Long id);

    DongSanPham findById(Long id);


    boolean existByMa(String ma);

    boolean existsByTen(String ten);


    boolean existsByTenAndIdNot(String ten, Long id);
}

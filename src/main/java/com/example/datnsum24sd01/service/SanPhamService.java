package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.custom.SanPhamCustom;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.SanPhamRequest;

import java.util.List;

public interface SanPhamService {

    List<SanPham> getList();

    List<SanPhamCustom> getAll();

    List<SanPhamCustom> getByTrangThai(TrangThai trangThai);

    SanPham save(SanPhamRequest request);

    SanPham update(SanPhamRequest request);


    SanPham findById(Long id);

    boolean existByMa(String ma);


    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);
    String delete(Long id);

}

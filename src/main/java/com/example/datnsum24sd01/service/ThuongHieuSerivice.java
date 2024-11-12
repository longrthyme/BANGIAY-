package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.ThuongHieuRequest;

import java.util.List;

public interface ThuongHieuSerivice {

    List<ThuongHieu> getList();

    List<ThuongHieu> getByTrangThai(TrangThai trangThai);

    ThuongHieu save(ThuongHieuRequest request);

    ThuongHieu update(ThuongHieuRequest request);

    void remove(Long id);

    ThuongHieu findById(Long id);

    boolean existByMa(String ma);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);


    Integer transferPage(Integer pageNo);
}

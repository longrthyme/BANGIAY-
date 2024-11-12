package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.NhaCungCapRequest;

import java.util.List;

public interface NhaCungCapServiec {
    List<NhaCungCap> getList();

    List<NhaCungCap> getByTrangThai(TrangThai trangThai);

    NhaCungCap save(NhaCungCapRequest request);

  NhaCungCap update(NhaCungCapRequest request);

    void remove(Long id);

    NhaCungCap findById(Long id);


    boolean existByMa(String ma);

    boolean existsByTen(String ten);


    boolean existsByTenAndIdNot(String ten, Long id);


}

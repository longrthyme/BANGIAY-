package com.example.datnsum24sd01.custom;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.entity.DongSanPham;
import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.entity.ThuongHieu;

import java.util.List;

public interface SanPhamCustom {
    Long getId();

    String getMa();

    String getTen();

    String getMoTa();

    Integer getTrangThai();

    ThuongHieu getThuongHieu();

    NhaCungCap getNhaCungCap();
   DongSanPham getDongSanPham();

    List<AnhBia> listAnh();

    String getAnhChinh();

}

package com.example.datnsum24sd01.custom;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.entity.SanPham;

import java.math.BigDecimal;
import java.util.List;

public interface ChiTietSanPhamCustom {

    Long getId();

    SanPham getSanPham();

    MauSac getMauSac();

    KichThuoc getKichThuoc();

    Integer getSoLuongTon();

    BigDecimal getGiaBan();

    Integer getTrangThai();
    List<AnhBia> listAnh();

    String getAnhChinh();

}

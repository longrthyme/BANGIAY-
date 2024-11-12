package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.entity.SanPham;

import java.util.List;

public interface AnhBiaSeriviec {
    AnhBia save(AnhBia anhSanPham);

    AnhBia update(AnhBia anhSanPham);

    List<AnhBia> getAnh(SanPham sanPham);

    void deleteByIdSp(Long id);
}

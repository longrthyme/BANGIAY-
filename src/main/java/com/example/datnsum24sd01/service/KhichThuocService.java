package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.request.KichThuocRequest;

import java.util.List;

public interface KhichThuocService {
    List<KichThuoc> getAllKichThuoc();

    KichThuoc add(KichThuocRequest kichThuocRequest);

    String delete(Long id);

    KichThuoc getOne(Long id);

    KichThuoc findById(Long id);

    KichThuoc update(KichThuoc kichThuoc);

    List<KichThuoc> findByProductId(Long productId);
}

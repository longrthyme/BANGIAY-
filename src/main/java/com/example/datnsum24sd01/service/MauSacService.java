package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.request.KichThuocRequest;
import com.example.datnsum24sd01.request.MauSacRequest;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAllMauSac();

    MauSac add(MauSacRequest mauSacRequest);

    String delete(Long id);

    MauSac getOne(Long id);

    MauSac findById(Long id);

    MauSac update(MauSac mauSac);

    List<MauSac> findByProductAndSize(Long productId, Long size);
}

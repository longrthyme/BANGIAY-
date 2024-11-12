package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.DongSanPham;
import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.DongSanPhamRequest;
import com.example.datnsum24sd01.responsitory.DongSanPhamResponsitory;
import com.example.datnsum24sd01.service.DongSanPhamService;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DongSanPhamServiceImpl implements DongSanPhamService {
    private final DongSanPhamResponsitory repository;

    public DongSanPhamServiceImpl(DongSanPhamResponsitory repository) {
        this.repository = repository;
    }


    @Override
    public List<DongSanPham> getList() {
        return repository.findAll();
    }

    @Override
    public List<DongSanPham> getByTrangThai(TrangThai trangThai) {
        return repository.getAllByTrangThai(trangThai);
    }


    @Override
    public DongSanPham save(DongSanPhamRequest request) {
        DongSanPham dongSanPham = new DongSanPham();
        dongSanPham.setMa(request.getMa());
        dongSanPham.setTen(request.getTen());
        dongSanPham.setNgayTao(LocalDate.now());
        dongSanPham.setNgaySua(LocalDate.now());
        dongSanPham.setTrangThai(TrangThai.DANG_HOAT_DONG);
         DongSanPham dongspauto = repository.save(dongSanPham);
        String maKT = "DSP" + dongspauto.getId().toString();
       dongspauto.setMa(maKT);
        return repository.save(dongSanPham);
    }


    @Override
    public DongSanPham update(DongSanPhamRequest request) {
        DongSanPham dongSanPham1 = repository.findById(request.getId()).orElse(null);
        if (dongSanPham1 != null) {
            dongSanPham1.setMa(request.getMa());
            dongSanPham1.setTen(request.getTen());
            dongSanPham1.setNgaySua(LocalDate.now());
            dongSanPham1.setTrangThai(request.getTrangThai());
            return repository.save(dongSanPham1);
        }
        return null;
    }


    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DongSanPham findById(Long id) {
        Optional<DongSanPham> dongSanPham = repository.findById(id);
        if (dongSanPham.isPresent()) {
            return dongSanPham.get();
        }
        return null;
    }

    @Override
    public boolean existByMa(String ma) {
        return repository.existsByMa(ma);
    }

    @Override
    public boolean existsByTen(String ten) {
        return repository.existsByTen(ten);
    }

    @Override
    public boolean existsByTenAndIdNot(String ten, Long id) {
        return repository.existsByTenAndIdNot(ten, id);
    }

}

package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.HoaDonChiTiet;
import com.example.datnsum24sd01.responsitory.HoaDonChiTietRepo;
import com.example.datnsum24sd01.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietHoaDonServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepo hoaDonChiTietRepo;
//hoa don online
    @Override
    public List<HoaDonChiTiet> getCtspById(Long id) {
        return hoaDonChiTietRepo.getHoaDonChiTietByIdHoaDon(id);
    }
}
package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.GioHang;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.responsitory.GioHangChiTietRepository;
import com.example.datnsum24sd01.responsitory.GioHangRepository;
import com.example.datnsum24sd01.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public List<GioHangChiTiet> getAll(Long idKhachHang) {
        GioHang gioHang = gioHangRepository.getByKhachHangId(idKhachHang);
        if(gioHang != null) {
            return gioHangChiTietRepository.findAllByGioHang(gioHang.getId());
        } else return null;
    }

    @Override
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public List<GioHangChiTiet> findGioHangChiTietById(Long id) {
        return gioHangChiTietRepository.findByHoaDon(id);
    }

    @Override
    public Optional<GioHangChiTiet> getOne(Long id) {

        return gioHangChiTietRepository.findById(id);
    }

    @Override
    public GioHangChiTiet save(GioHangChiTiet gioHangChiTiet) {

        return gioHangChiTietRepository.save(gioHangChiTiet);
    }
}

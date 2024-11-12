package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.responsitory.AnhBiaResponsitory;
import com.example.datnsum24sd01.service.AnhBiaSeriviec;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnhBiaSeriviecImpl implements AnhBiaSeriviec {
    //view add, update delete ảnh service
    private final AnhBiaResponsitory repository;

    public AnhBiaSeriviecImpl(AnhBiaResponsitory repository) {
        this.repository = repository;
    }


    @Override
    public AnhBia save(AnhBia anhSanPham) {
        return repository.save(anhSanPham);
    }

    @Override
    public AnhBia update(AnhBia anhSanPham) {
        AnhBia anhSanPham1 = repository.findById(anhSanPham.getId()).orElse(null);
        if (anhSanPham1 != null) {
            anhSanPham.setUrl(anhSanPham1.getUrl());
            return repository.save(anhSanPham);
        }
        return null;
    }

    @Override
    public List<AnhBia> getAnh(SanPham sanPham) {
        {
            return repository.findBySanPham(sanPham);
        }
    }

    @Transactional
    @Override
    public void deleteByIdSp(Long id) {
        List<AnhBia> anhSanPhams = repository.findBySanPhamId(id);
        repository.deleteInBatch(anhSanPhams);
    }
}

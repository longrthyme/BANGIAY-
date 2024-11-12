package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.ThuongHieuRequest;
import com.example.datnsum24sd01.responsitory.ThuongHieuResponsitory;
import com.example.datnsum24sd01.service.ThuongHieuSerivice;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ThuongHieuSeriviceImpl implements ThuongHieuSerivice {

    private final ThuongHieuResponsitory repository;

    public ThuongHieuSeriviceImpl(ThuongHieuResponsitory repository) {
        this.repository = repository;
    }

    @Override
    public List<ThuongHieu> getList() {
        return repository.findAll();
    }

    @Override
    public List<ThuongHieu> getByTrangThai(TrangThai trangThai) {
        return repository.getAllByTrangThai(trangThai);
    }




    @Override
    public ThuongHieu save(ThuongHieuRequest request)  {

        ThuongHieu thuongHieu = new ThuongHieu();
        thuongHieu.setMa(request.getMa());
        thuongHieu.setTen(request.getTen());
        thuongHieu.setNgayTao(LocalDate.now());
        thuongHieu.setNgaySua(LocalDate.now());
        thuongHieu.setTrangThai(TrangThai.DANG_HOAT_DONG);
       ThuongHieu mauSacAddLater = repository.save(thuongHieu);
        String maMS = "TH" + mauSacAddLater.getId().toString();
        mauSacAddLater.setMa(maMS);
        return repository.save(mauSacAddLater);
    }


    @Override
    public ThuongHieu update(ThuongHieuRequest request) {
        ThuongHieu ThuongHieu1 = repository.findById(request.getId()).orElse(null);
        if (ThuongHieu1 != null) {
            ThuongHieu1.setMa(request.getMa());
            ThuongHieu1.setTen(request.getTen());
            ThuongHieu1.setNgaySua(LocalDate.now());
            ThuongHieu1.setTrangThai(request.getTrangThai());
            return repository.save(ThuongHieu1);
        }
        return null;
    }


    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ThuongHieu findById(Long id) {
        Optional<ThuongHieu> ThuongHieu = repository.findById(id);
        if (ThuongHieu.isPresent()) {
            return ThuongHieu.get();
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


    @Override
    public Integer transferPage(Integer pageNo) {
        Integer sizeList = repository.findAll().size();
        System.out.println(sizeList);
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        System.out.println(pageCount);
        if (pageNo >= pageCount) {
            pageNo = 0;
        } else if (pageNo < 0) {
            pageNo = pageCount - 1;
        }
        System.out.println(pageNo);
        return pageNo;
    }
}

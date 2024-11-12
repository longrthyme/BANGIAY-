package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.custom.SanPhamCustom;
import com.example.datnsum24sd01.entity.DongSanPham;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.entity.ThuongHieu;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.SanPhamRequest;
import com.example.datnsum24sd01.responsitory.DongSanPhamResponsitory;
import com.example.datnsum24sd01.responsitory.NhaCungCapResponsitory;
import com.example.datnsum24sd01.responsitory.SanPhamRepository;
import com.example.datnsum24sd01.responsitory.ThuongHieuResponsitory;
import com.example.datnsum24sd01.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    private final SanPhamRepository repository;
    private final NhaCungCapResponsitory nhaCungCapResponsitory;
    private final ThuongHieuResponsitory thuongHieuRepository;
    private final DongSanPhamResponsitory dongSanPhamResponsitory;

    public SanPhamServiceImpl(SanPhamRepository repository, DongSanPhamResponsitory dongSanPhamResponsitory, NhaCungCapResponsitory nhaCungCapResponsitory, ThuongHieuResponsitory thuongHieuRepository, DongSanPhamResponsitory dongSanPhamResponsitory1) {
        this.repository = repository;
        this.nhaCungCapResponsitory = nhaCungCapResponsitory;
        this.thuongHieuRepository = thuongHieuRepository;
        this.dongSanPhamResponsitory = dongSanPhamResponsitory;
    }

    @Autowired
    private SanPhamRepository sanPhamRepository;


    @Override
    public List<SanPham> getList() {
        return repository.findAll();
    }


    @Override
    public List<SanPhamCustom> getAll() {
        return repository.getPageSanPhamCusTom();
    }

    @Override
    public List<SanPhamCustom> getByTrangThai(TrangThai trangThai) {
        return sanPhamRepository.getAllByTrangThai(trangThai);
    }


    @Override
    public SanPham save(SanPhamRequest request) {
        SanPham sanPham = new SanPham();

        sanPham.setMa(request.getMa());
        sanPham.setTen(request.getTen());
        sanPham.setMoTa(request.getMoTa());
        sanPham.setListAnhSanPham(request.getListAnh());
        sanPham.setAnhChinh(request.getAnhChinh());
        NhaCungCap nhaCungCap = nhaCungCapResponsitory.findById(request.getNhaCungCap()).orElse(null);
        sanPham.setNhaCungCap(nhaCungCap);
        ThuongHieu thuongHieu = thuongHieuRepository.findById(request.getThuongHieu()).orElse(null);
        sanPham.setThuongHieu(thuongHieu);
        DongSanPham dongSanPham = dongSanPhamResponsitory.findById(request.getDongSanPham()).orElse(null);
        sanPham.setDongSanPham(dongSanPham);

        sanPham.setNgayTao(LocalDate.now());
        sanPham.setNgaySua(LocalDate.now());
        sanPham.setTrangThai(TrangThai.DANG_HOAT_DONG);
        return repository.save(sanPham);
    }


    @Override
    public SanPham update(SanPhamRequest request) {
        SanPham sanPham = repository.findById(request.getId()).orElse(null);
        if (sanPham != null) {
            sanPham.setMa(request.getMa());
            sanPham.setTen(request.getTen());
            sanPham.setMoTa(request.getMoTa());
            sanPham.setListAnhSanPham(request.getListAnh());
            sanPham.setAnhChinh(request.getAnhChinh());
            NhaCungCap nhaCungCap = nhaCungCapResponsitory.findById(request.getNhaCungCap()).orElse(null);
            sanPham.setNhaCungCap(nhaCungCap);
            ThuongHieu thuongHieu = thuongHieuRepository.findById(request.getThuongHieu()).orElse(null);
            sanPham.setThuongHieu(thuongHieu);
            DongSanPham dongSanPham = dongSanPhamResponsitory.findById(request.getDongSanPham()).orElse(null);
            sanPham.setDongSanPham(dongSanPham);
            sanPham.setNgaySua(LocalDate.now());
            sanPham.setTrangThai(request.getTrangThai());
            return repository.save(sanPham);
        }
        return null;
    }




    @Override
    public SanPham findById(Long id) {
        Optional<SanPham> sanPham = repository.findById(id);
        if (sanPham.isPresent()) {
            return sanPham.get();
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
    public String delete(Long id) {
        String note = "";
        try {
            sanPhamRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete SanPham with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete San Pham with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

}

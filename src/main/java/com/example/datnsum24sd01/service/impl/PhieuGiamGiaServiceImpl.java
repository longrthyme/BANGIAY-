package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import com.example.datnsum24sd01.request.PhieuGiamGiaRequest;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.responsitory.PhieuGiamGiaResponsitory;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {
    @Autowired
    PhieuGiamGiaResponsitory responsitory;

    ZoneId systemZone = ZoneId.systemDefault();
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public List<PhieuGiamGia> getAll() {
        return responsitory.findAll().stream()
                .sorted(Comparator.comparing(PhieuGiamGia::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<PhieuGiamGia> getListHoatDong() {
        return responsitory.listphieuGiamGiaHoatDong();
    }

    @Override
    public List<PhieuGiamGia> getByTrangThai(TrangThaiPhieuKhuyenMai trangThaiKhuyenMai) {
        return responsitory.getAllByTrangThai(trangThaiKhuyenMai).stream()
                .sorted(Comparator.comparing(PhieuGiamGia::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public String delete(Long id) {
        String noti = "";
        try {
            responsitory.deleteById(id);
            noti = "Thành Công!";
        } catch (DataIntegrityViolationException e) {

        }
        return noti;
    }


    @Override
    public PhieuGiamGia add(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        // Lấy thời gian hiện tại theo múi giờ hiện tại của hệ thống

        PhieuGiamGia pgg = new PhieuGiamGia();
        NhanVien nhanVien = new NhanVien();

        pgg.setMa(phieuGiamGiaRequest.getMa());
        pgg.setTen(phieuGiamGiaRequest.getTen());
        pgg.setMoTa(phieuGiamGiaRequest.getMoTa());
        pgg.setMucGiamGia(phieuGiamGiaRequest.getMucGiamGia());
        pgg.setMucGiamToiDa(phieuGiamGiaRequest.getMucGiamToiDa());
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        pgg.setNgayTao(currentDateTime);
        pgg.setSoLuong(phieuGiamGiaRequest.getSoLuong());
        pgg.setGiaTriDonHang(phieuGiamGiaRequest.getGiaTriDonHang());
        pgg.setNgayBatDau(phieuGiamGiaRequest.getNgayBatDau());
        pgg.setNgayKetThuc(phieuGiamGiaRequest.getNgayKetThuc());
        pgg.setTrangThai(phieuGiamGiaRequest.htTrangThai());


//       emailService .sendMaPhieuGiamGiaKH(nhanVien.getEmail(), phieuGiamGiaRequest.getMa());
        return responsitory.save(pgg);
    }

    @Override
    public PhieuGiamGia getById(Long id) {

        Optional<PhieuGiamGia> optional = responsitory.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public PhieuGiamGia update(PhieuGiamGiaRequest phieuGiamGiaRequest) {
        PhieuGiamGia pgg = responsitory.findById(phieuGiamGiaRequest.getId()).orElse(null);
        pgg.setTen(phieuGiamGiaRequest.getTen());
        pgg.setMoTa(phieuGiamGiaRequest.getMoTa());
        pgg.setMucGiamGia(phieuGiamGiaRequest.getMucGiamGia());
        pgg.setMucGiamToiDa(phieuGiamGiaRequest.getMucGiamToiDa());
        LocalDateTime currentTime = LocalDateTime.now();
        Date currentDate = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        pgg.setNgaySua(currentDate);
        pgg.setSoLuong(phieuGiamGiaRequest.getSoLuong());
        pgg.setGiaTriDonHang(phieuGiamGiaRequest.getGiaTriDonHang());
        pgg.setNgayBatDau(phieuGiamGiaRequest.getNgayBatDau());
        pgg.setNgayKetThuc(phieuGiamGiaRequest.getNgayKetThuc());
        pgg.setTrangThai(phieuGiamGiaRequest.htTrangThai());
        return responsitory.save(pgg);
    }

    @Override
    public void huy(Long id) {
        PhieuGiamGia phieuGiamGia = responsitory.findById(id).orElse(null);
        if (phieuGiamGia != null) {
            phieuGiamGia.setTrangThai(TrangThaiPhieuKhuyenMai.DA_KET_THUC);
            responsitory.save(phieuGiamGia);
        }
    }

    @Override
    public void bat(Long id) {
        PhieuGiamGia phieuGiamGia = responsitory.findById(id).orElse(null);
        if (phieuGiamGia != null) {
            phieuGiamGia.setTrangThai(TrangThaiPhieuKhuyenMai.DANG_DIEN_RA);
            responsitory.save(phieuGiamGia);
        }
    }
    @Override
    public void updateTrangThai() {
        responsitory.updateTrangThaiDangHoatDong();
        responsitory.updateTrangThaiDungHoatDong1();
        responsitory.updateTrangThaiDungHoatDong2();
        responsitory.updateTrangThaiSapDienRa();
    }

    @Override
    public boolean existsByTen(String ten) {

        return responsitory.existsByTen(ten);
    }

    @Override
    public boolean existsByTenAndIdNot(String ten, Long id) {
      return   responsitory.existsByTenAndIdNot(ten, id);
    }

    @Override
    public List<PhieuGiamGia> layList(Long tongGiaTri) {
        return responsitory.getAllByGiaTriDonHang(tongGiaTri);
    }

    @Override
    public List<PhieuGiamGia> findMaGiamGia(LocalDate start, LocalDate end, TrangThaiPhieuKhuyenMai trangThaiKhuyenMai) {
        return responsitory.findMaGiamGiasByByNgayBatDauAndNgayKetThuc(start, end);
    }
}

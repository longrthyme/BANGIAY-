package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.DiaChi;
import com.example.datnsum24sd01.entity.GioHang;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.enumation.GioiTinh;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.request.RegisterRequest;
import com.example.datnsum24sd01.responsitory.DiaChiResponsitory;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.KhachHangService;
import com.example.datnsum24sd01.worker.AutoGenCodeRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangResponsitory khachHangResponsitory;
    @Autowired
    DiaChiResponsitory diaChiResponsitory;
    @Autowired
    private EmailService emailService;


    @Override
    public List<KhachHang> getList() {
        return khachHangResponsitory.getListKhachHang();
    }

    @Override
    public boolean existsBySdt(String sdt) {
        return khachHangResponsitory.existsBySdt(sdt);
    }

    @Override
    public boolean existsByEmail(String email) {
        return khachHangResponsitory.existsByEmail(email);
    }

    @Override
    public boolean existsBySdtAndIdNot(String sdt, Long id) {
        return khachHangResponsitory.existsBySdtAndIdNot(sdt, id);
    }

    //đăng kí khách hàng gửi mật khẩu random về mail
    @Override
    public KhachHang registration(RegisterRequest request) {

        KhachHang khachHang = khachHangResponsitory.save(RegisterRequest.convertToEntity(request));
        String matKhau = AutoGenCodeRandom.genUUID();
        khachHang.setMatKhau(matKhau);
        khachHangResponsitory.save(khachHang);

        emailService.sendNewAccountKHEmail(khachHang.getEmail(), khachHang.getEmail(), matKhau);

        return khachHang;
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangResponsitory.findAll();
    }

    @Override
    public KhachHang add(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        khachHang.setTen(khachHangRequest.getTen());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setGioiTinh(GioiTinh.valueOf(khachHangRequest.getGioiTinh()));
        khachHang.setSdt(khachHangRequest.getSdt());
        String matKhau = UUID.randomUUID().toString().substring(0, 10);
        khachHang.setMatKhau(matKhau);
        khachHang.setTrangThai((byte) 1);
        khachHang.setTichDiem(BigDecimal.ZERO);
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        khachHang.setNgayTao(LocalDate.now());
        KhachHang khachHangMa = khachHangResponsitory.save(khachHang);
        String ma = "KH" + khachHangMa.getId().toString();
        khachHangMa.setMa(ma);

        return khachHangResponsitory.save(khachHang);
    }

    @Override
    public String delete(Long id) {
        Optional<KhachHang> khachHangOptional = khachHangResponsitory.findById(id);
        if (khachHangOptional.isPresent()) {
            khachHangResponsitory.deleteById(id);
            return "Thành Công!";
        } else {
            return "Không thể xóa khách hàng với id = " + id + ". Không tìm thấy khách hàng.";
        }
    }

    @Override
    public boolean checkSdtDuplicate(String sdt) {
        return khachHangResponsitory.existsKhachHangBySdt(sdt);
    }

    @Override
    public boolean checkEmailDuplicate(String email) {
        return khachHangResponsitory.existsKhachHangByEmail(email);
    }

    @Override
    public KhachHang getById(Long id) {
        Optional<KhachHang> optional = khachHangResponsitory.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public KhachHang getOne(Long id) {
        Optional<KhachHang> khachHang = khachHangResponsitory.findById(id);
        return khachHang.orElse(null);
    }

    @Override
    public KhachHang update(KhachHangRequest khachHangRequest) {
        if (khachHangRequest == null) {
            return null;
        }
        KhachHang existingKhachHang = khachHangResponsitory.getReferenceById(khachHangRequest.getId());
        if (existingKhachHang == null) {
            return null;
        }
        existingKhachHang.setMa(khachHangRequest.getMa());
        existingKhachHang.setTen(khachHangRequest.getTen());
        existingKhachHang.setSdt(khachHangRequest.getSdt());
        existingKhachHang.setGioiTinh(GioiTinh.valueOf(khachHangRequest.getGioiTinh()));
        existingKhachHang.setNgaySua(LocalDate.now());
        existingKhachHang.setEmail(khachHangRequest.getEmail());
        existingKhachHang.setTrangThai(khachHangRequest.getTrangThai());
        try {
            khachHangResponsitory.save(existingKhachHang);
            return existingKhachHang;
        } catch (Exception e) {
            return null;
        }
    }

    //view địa chỉ của từng khách hàng
    @Override
    public List<DiaChi> getDiaChiByIdKhachHang(Long idKhachHang) {
        List<DiaChi> list = new ArrayList<>();
        for (DiaChi dc : diaChiResponsitory.findAll()) {
            if (dc.getKhachHang().getId() == idKhachHang) {
                list.add(dc);
            }
        }
        return list;

    }

    @Override
    public DiaChi getByIdDiaChi(Long idDiaChi) {
        return diaChiResponsitory.findById(idDiaChi).orElse(null);

    }

    //đổi mật khẩu
    @Override
    public boolean changeUserPassword(Long idKh, String oldPassword, String newPassword) {
        KhachHang khachHang = khachHangResponsitory.findById(idKh).orElse(null);

        khachHang.setMatKhau(newPassword);
        khachHangResponsitory.save(khachHang);
        return true;

    }

    //quên mật khẩu
    public boolean forgotpassword(String email) {

        KhachHang khachHang = khachHangResponsitory.findByEmail(email).orElse(null);
        if (khachHang != null) {

            String newPassword = generateRandomPassword(10);

            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            khachHang.setMatKhau(newPassword); // Lưu mật khẩu không mã hóa


            khachHangResponsitory.save(khachHang);

            // Gửi email với mật khẩu mới
            emailService.sendPasswordEmail(email, newPassword);

            return true;
        }
        return false;
    }


    private String generateRandomPassword(int length) {
        return RandomStringUtils.random(length, true, true); // Sinh chuỗi ngẫu nhiên bao gồm chữ cái và số
    }
}




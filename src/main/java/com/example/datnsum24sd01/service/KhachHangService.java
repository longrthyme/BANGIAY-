package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.DiaChi;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.request.RegisterRequest;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KhachHangService {
    //    List<KhachHang> getByTrangThai(TrangThai trangThai);
    List<KhachHang> getList();
    boolean existsBySdt(String sdt);

    boolean existsByEmail(String email);

    boolean existsBySdtAndIdNot(String sdt,Long id);
    KhachHang registration(RegisterRequest khachHang);
    List<KhachHang> getAll();

    KhachHang add(KhachHangRequest khachHangRequest);

    String delete(Long id);

    boolean checkSdtDuplicate(String sdt);


    boolean checkEmailDuplicate(String email);
    KhachHang getById(Long id);

    KhachHang getOne(Long id);

    KhachHang update(KhachHangRequest khachHangRequest);

    List<DiaChi> getDiaChiByIdKhachHang(Long idKhachHang);

    DiaChi getByIdDiaChi(Long idDiaChi);
    boolean changeUserPassword(Long idKh,String oldPassword, String newPassword);
    boolean forgotpassword(String newPassword);
}

package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.request.NhanVienRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NhanVienService {
    List<NhanVien> getAll();

    NhanVien add(NhanVienRequest nhanVienRequset);

    String delete(Long id);

    boolean checkPhoneDuplicate(String phone);
    boolean checkPhoneDuplicate(String phone, long id);

    boolean checkCccdDuplicate(String cccd);
    boolean checkCccdDuplicate(String cccd, long id);

    boolean checkEmailDuplicate(String mail);
    boolean checkEmailDuplicate(String mail, long id);

    NhanVien getOne(long id);

    NhanVien update(NhanVien nhanVien);

    void thayDoiTrangThai(long id);



}

package com.example.datnsum24sd01.security.admin;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.security.shop.CustomKhachHangDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomNhanVienDetailService implements UserDetailsService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhachHangResponsitory khachHangRepository;
//tìm xem người dùng có tài khoản hay không, tự động chuyển đến đường trang với tài khoản tương ứng
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NhanVien> nhanVien = nhanVienRepository.findByEmail(username);
        Optional<KhachHang> khachHang = khachHangRepository.findByEmailAndIdNot(username, 1L);

        if (khachHang.isPresent()) {
            return new CustomKhachHangDetail(khachHang.get());
        } else if (nhanVien.isPresent()) {
            return new CustomNhanVienDetail(nhanVien.get());
        } else {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + username);
        }
    }
}

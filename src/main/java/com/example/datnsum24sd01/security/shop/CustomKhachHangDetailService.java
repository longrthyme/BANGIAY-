package com.example.datnsum24sd01.security.shop;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//tìm kiếm khách hàng theo email (username) và chuyển đổi đối tượng KhachHang thành đối tượng UserDetails
// Spring Security có thể sử dụng để thực hiện xác thực và phân quyền.
public class CustomKhachHangDetailService implements UserDetailsService {
    @Autowired
    private KhachHangResponsitory khachHangRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KhachHang khachHang = khachHangRepository.findByEmailAndIdNot(username, 1L).orElseThrow(()-> new UsernameNotFoundException("Invalid"));
        return new CustomKhachHangDetail(khachHang);
    }
}

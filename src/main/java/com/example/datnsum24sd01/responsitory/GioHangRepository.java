package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang,Long> {

    GioHang getByKhachHangId(Long id);
}

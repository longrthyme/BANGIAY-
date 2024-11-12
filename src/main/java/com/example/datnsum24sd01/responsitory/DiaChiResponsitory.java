package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaChiResponsitory extends JpaRepository<DiaChi,Long> {

    @Query(value ="select * from dia_chi where id_khach_hang= :idKhachHang",nativeQuery = true )
    List<DiaChi> getAllTheoKhachHang(@Param("idKhachHang") Long id);
}

package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.custom.HoaDonChiTietCustom;
import com.example.datnsum24sd01.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet,Long> {
    @Query(value = "select * from hoa_don_chi_tiet where id_hoa_don = :idHoaDon", nativeQuery = true)
    List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(@Param("idHoaDon") Long idHoaDon);


    @Query(value = "SELECT hoa_don_chi_tiet.ten_san_pham, SUM(hoa_don_chi_tiet.so_luong) AS tong_so_luong\n" +
            "FROM hoa_don_chi_tiet\n" +
            "JOIN hoa_don ON hoa_don.id = hoa_don_chi_tiet.id_hoa_don\n" +
            " WHERE hoa_don.trang_thai in(5,7,9)\n" +
            "  AND hoa_don.ngay_thanh_toan BETWEEN ?1 AND ?2\n"+
            "GROUP BY hoa_don_chi_tiet.ten_san_pham  \n" +
            "ORDER BY tong_so_luong DESC \n" +
            "LIMIT 5;",nativeQuery = true)
    List<Object[]> countHoaDonChiTiet(LocalDate from, LocalDate to);

}

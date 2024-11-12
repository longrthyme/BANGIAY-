package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.custom.HoaDonCustom;
import com.example.datnsum24sd01.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query(value = "select hd.id, hd.ma, kh.ten, hd.sdt, hd.ngay_tao, hd.thanh_toan \n" +
            "from hoa_don hd \n" +
            "join khach_hang kh on hd.id_khach_hang = kh.id \n" +
            "where hd.id = :idHoaDon",
            nativeQuery = true)
    HoaDonCustom getHoaDonById(@Param("idHoaDon") Long idHoaDon);

    @Query(value = "select count(hoa_don.trang_thai) from hoa_don where hoa_don.trang_thai = 0", nativeQuery = true)
    Integer checkHoaDonCho();



}


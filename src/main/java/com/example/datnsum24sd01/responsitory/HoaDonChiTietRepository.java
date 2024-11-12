package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.custom.HoaDonChiTietCustom;
import com.example.datnsum24sd01.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {

    @Query(value = "select * from hoa_don_chi_tiet where id_hoa_don = :idHoaDon", nativeQuery = true)
    List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(@Param("idHoaDon") Long idHoaDon);

    @Query(value = "select hdct.id, sp.ma, hdct.ten_san_pham, hdct.mau_sac, hdct.kich_thuoc, hdct.gia_ban, hdct.so_luong \n" +
            "from hoa_don_chi_tiet hdct \n" +
            "join chi_tiet_san_pham ctsp on hdct.id_chi_tiet_san_pham= ctsp.id \n" +
            "join san_pham sp on sp.id = ctsp.id_san_pham \n" +
            "where hdct.id_hoa_don = :idHoaDon", nativeQuery = true)
    List<HoaDonChiTietCustom> getOneHDCTByHD(@Param("idHoaDon") Long idHoaDon);


    @Query(value = "select * from hoa_don_chi_tiet where trang_thai = 3 and id_hoa_don = :idHoaDon", nativeQuery = true)
    Page<HoaDonChiTiet> getPhanTrang(Pageable pageable, @Param("idHoaDon") Long idHoaDon);
}

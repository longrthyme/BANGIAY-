package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PhieuGiamGiaResponsitory extends JpaRepository<PhieuGiamGia,Long> {

    @Query("""
                SELECT pgg FROM PhieuGiamGia pgg
                WHERE 
                   pgg.trangThai = :trangThai
            """)
    List<PhieuGiamGia> getAllByTrangThai(
            @Param("trangThai") TrangThaiPhieuKhuyenMai trangThaiKhuyenMai
    );

    @Query(value = "select * from phieu_giam_gia mgg where (mgg.ngay_bat_dau between :start and :end) or (mgg.ngay_ket_thuc between :start and :end)", nativeQuery = true)
    List<PhieuGiamGia> findMaGiamGiasByByNgayBatDauAndNgayKetThuc(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Transactional
    @Modifying
    @Query(value = "update phieu_giam_gia pgg set pgg.trang_thai = 0\n" +
            "where pgg.id > 0 and pgg.so_luong > 0  and current_date() between pgg.ngay_bat_dau and pgg.ngay_ket_thuc and pgg.trang_thai != 1;", nativeQuery = true)
    int updateTrangThaiDangHoatDong();

    @Transactional
    @Modifying
    @Query(value = "update phieu_giam_gia mgg set pgg.trang_thai = 1\n" +
            "where pgg.id > 0 and (pgg.so_luong <= 0  or pgg.ngay_ket_thuc < current_date());", nativeQuery = true)
    int updateTrangThaiDungHoatDong1();

    @Transactional
    @Modifying
    @Query(value = "update phieu_giam_gia mgg set pgg.trang_thai = 1\n" +
            "where pgg.id > 0 and  date_sub(pgg.ngay_bat_dau, interval 4 day) >= current_date();", nativeQuery = true)
    int updateTrangThaiDungHoatDong2();

    @Transactional
    @Modifying
    @Query(value = "update phieu_giam_gia mgg set pgg.trang_thai = 2\n" +
            "where pgg.id > 0 and CURDATE() < pgg.ngay_bat_dau and CURDATE() >= pgg.ngay_bat_dau - interval 3 day and pgg.ngay_bat_dau <= pgg.ngay_ket_thuc;", nativeQuery = true)
    int updateTrangThaiSapDienRa();

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);

    @Query("""
                SELECT pgg FROM PhieuGiamGia pgg
                WHERE\s
                   :tongGiaTri > pgg.giaTriDonHang and pgg.trangThai = 0 and pgg.soLuong > 0 and pgg.ngayBatDau <= CURRENT_DATE and CURRENT_DATE <= pgg.ngayKetThuc  
            """)
    List<PhieuGiamGia> getAllByGiaTriDonHang(
            @Param("tongGiaTri") Long tongGiaTri);

    @Query(value = "select * from phieu_giam_gia\n" +
            "where phieu_giam_gia.trang_thai = 0;", nativeQuery = true)
    List<PhieuGiamGia> listphieuGiamGiaHoatDong();

}

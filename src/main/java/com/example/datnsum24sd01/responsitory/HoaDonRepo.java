package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface HoaDonRepo extends JpaRepository<HoaDon, Long> {

    List<HoaDon> getAllByTrangThai(TrangThaiDonHang trangThai);

    List<HoaDon> findHoaDonByTrangThaiAndKhachHangId(TrangThaiDonHang trangThai, Long id);

    @Query("SELECT hd FROM HoaDon hd WHERE hd.ngayTao >= :start AND hd.ngayTao <= :end")
    List<HoaDon> findHoaDonsByNgayTao(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query(value = "select * from hoa_don where id_khach_hang=?1", nativeQuery = true)
    List<HoaDon> getAllByKhachHang(Long idKh);

    @Query(value = "SELECT SUM(hoa_don.thanh_toan) FROM hoa_don WHERE trang_thai = 5" +
            "  AND hoa_don.ngay_thanh_toan BETWEEN ?1 AND ?2\n", nativeQuery = true)
    Long doanhThu(LocalDate from, LocalDate to);

    @Query(value = "SELECT COUNT(*) from hoa_don where  trang_thai=6" +
            "  AND hoa_don.ngay_tao BETWEEN ?1 AND ?2\n", nativeQuery = true)
    Long soDonHuy(LocalDate from, LocalDate to);

    @Query(value = "SELECT trang_thai, COUNT(trang_thai) FROM hoa_don" +
            "  where hoa_don.ngay_tao BETWEEN  :from AND  :to\n" +
            "    group by  hoa_don.trang_thai\n", nativeQuery = true)
    List<Object[]> thongKeHoaDon(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = "SELECT SUM(hoa_don.thanh_toan-hoa_don.phi_van_chuyen) AS total_thanh_toan, hoa_don.ngay_thanh_toan\n" +
            "FROM hoa_don \n" +
            "WHERE hoa_don.trang_thai IN (5, 8, 9)\n" +
            "  AND hoa_don.ngay_thanh_toan BETWEEN ?1 AND ?2\n" +
            "GROUP BY hoa_don.ngay_thanh_toan\n" +
            "ORDER BY hoa_don.ngay_thanh_toan ASC;\n", nativeQuery = true)
    List<Object[]> thongKeDoanhTHu(@Param("from") LocalDate from, @Param("to") LocalDate to);

    HoaDon findHoaDonByMa(String ma);

    boolean existsByMa(String ma);


}
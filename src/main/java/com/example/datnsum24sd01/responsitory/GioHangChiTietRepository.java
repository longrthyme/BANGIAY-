package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.GioHang;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {


    GioHangChiTiet findByGioHangAndChiTietSanPhamAndHoaDonIsNull(GioHang gioHang, ChiTietSanPham chiTietSanPham);

//    List<GioHangChiTiet> findAllByByGioHangAndHoaDonIsNull(Long idGioHang);

    //    List<GioHangChiTiet> findAllByGioHangAndHoaDonIsNull();
    @Query(value = "select * from gio_hang_chi_tiet where id_hoa_don=?1", nativeQuery = true)
    List<GioHangChiTiet> findByHoaDon(long id);


    @Query(value = "SELECT gio_hang_chi_tiet.* from gio_hang_chi_tiet join hoa_don on gio_hang_chi_tiet.id_hoa_don= hoa_don.id  where (gio_hang_chi_tiet.trang_thai=9 OR gio_hang_chi_tiet.trang_thai=4 ) \n" +
            "  AND gio_hang_chi_tiet.ngay_tao BETWEEN ?1 AND ?2\n", nativeQuery = true)
    List<GioHangChiTiet> findByHoaDonHoanTraHoanLaiKho(LocalDate from, LocalDate to);

    @Query(value = "SELECT gio_hang_chi_tiet.* from gio_hang_chi_tiet join hoa_don on gio_hang_chi_tiet.id_hoa_don= hoa_don.id  where gio_hang_chi_tiet.trang_thai=9 OR gio_hang_chi_tiet.trang_thai=7 \n \n", nativeQuery = true)
    List<GioHangChiTiet> findByHoaDonHoanTraHoanLaiKho1();

    @Query(value = "select * from gio_hang_chi_tiet " +
            "where id_hoa_don is null and id_gio_hang = :idGioHang", nativeQuery = true)
    List<GioHangChiTiet> findAllByGioHang(@Param("idGioHang") Long idGioHang);

    @Query(value = "SELECT SUM(gio_hang_chi_tiet.so_luong) from gio_hang_chi_tiet join hoa_don on gio_hang_chi_tiet.id_hoa_don= hoa_don.id  where gio_hang_chi_tiet.trang_thai=9\n \n" +
            "  AND hoa_don.ngay_thanh_toan BETWEEN ?1 AND ?2\n", nativeQuery = true)
    Long sanPhamHoanTra(LocalDate from, LocalDate to);


    @Query(value = "SELECT ct.chiTietSanPham.sanPham.ten, SUM(ct.soLuong) AS tong_so_luong " +
            "FROM GioHangChiTiet ct " +
            "JOIN ct.hoaDon hd " +
            "WHERE hd.trangThai = com.example.datnsum24sd01.enumation.TrangThaiDonHang.HOAN_THANH " +
            "  AND hd.ngayThanhToan BETWEEN ?1 AND ?2 \n" +
            "GROUP BY ct.chiTietSanPham.sanPham.ten " +
            "ORDER BY tong_so_luong DESC " +  // Adding an order to get top quantities first
            "LIMIT 5")
    List<Object[]> countAllGhct(LocalDate from, LocalDate to);


    @Query(value = "SELECT SUM(hoa_don.thanh_toan) AS total_thanh_toan, hoa_don.ngay_thanh_toan\n" +
            "FROM hoa_don \n" +
            "WHERE hoa_don.trang_thai IN (5, 8, 9)\n" +
            "  AND hoa_don.ngay_tao BETWEEN ?1 AND ?2\n" +
            "GROUP BY hoa_don.ngay_tao\n" +
            "ORDER BY hoa_don.ngay_tao ASC;\n", nativeQuery = true)
    List<Object[]> thongKeDoanhTHu(@Param("from") LocalDate from, @Param("to") LocalDate to);


}


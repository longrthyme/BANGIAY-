package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.custom.ChiTietSanPhamCustom;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.enumation.TrangThai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ChiTietSanPhamResponsitory extends JpaRepository<ChiTietSanPham,Long> {

    List<ChiTietSanPham> findBySanPham_Id(Long sanPhamId);


    @Query(value = "select * from chi_tiet_san_pham where id = :idChiTietSanPham", nativeQuery = true)
    Optional<ChiTietSanPham> getChiTietSanPhamById(@Param("idChiTietSanPham") Long idChiTietSanPham);

    @Query(value = "select chi_tiet_san_pham.id_san_pham, chi_tiet_san_pham.id_de_giay, chi_tiet_san_pham.id_mau_sac,\n" +
            " chi_tiet_san_pham.id_kich_thuoc, chi_tiet_san_pham.id_lot_giay,\n" +
            " chi_tiet_san_pham.id_co_giay, chi_tiet_san_pham.so_luong_ton,\n" +
            " chi_tiet_san_pham.gia_mac_dinh, chi_tiet_san_pham.gia_ban\n" +
            "from chi_tiet_san_pham", nativeQuery = true)
    List<ChiTietSanPhamCustom> getAll();

    @Query(value = "select chi_tiet_san_pham.id_san_pham, chi_tiet_san_pham.id_de_giay, chi_tiet_san_pham.id_mau_sac,\n" +
            " chi_tiet_san_pham.id_kich_thuoc, chi_tiet_san_pham.id_lot_giay,\n" +
            " chi_tiet_san_pham.id_co_giay, chi_tiet_san_pham.so_luong_ton,\n" +
            " chi_tiet_san_pham.gia_mac_dinh, chi_tiet_san_pham.gia_ban\n" +
            "from chi_tiet_san_pham", nativeQuery = true)
    Page<ChiTietSanPhamCustom> getPage(PageRequest page);

    @Query("""
                SELECT ctsp FROM ChiTietSanPham ctsp
                WHERE 
                   ctsp.trangThai = :trangThai
            """)
    List<ChiTietSanPham> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai);

    @Query(value = "select * from chi_tiet_san_pham where trang_thai = 0", nativeQuery = true)
    List<ChiTietSanPham> fillAllDangHoatDong();

    Page<ChiTietSanPham> findBySanPham_TenContainingIgnoreCase(String ten, Pageable pageable);

    @Query(value = "select sp.ma, sp.ten , sum(ctsp.so_luong_ton) as soLuongTon from chi_tiet_san_pham ctsp \n" +
            "join san_pham sp on ctsp.id_san_pham = sp.id \n" +
            "group by sp.ma, sp.ten \n" +
            "HAVING COUNT(*) > 1", nativeQuery = true)
    List<ChiTietSanPhamCustom> getSanPham();

    @Query(value = "select sp.ma, sp.ten, ms.ten, kt.ten, ctsp.gia_ban, ctsp.so_luong_ton from chi_tiet_san_pham ctsp \n" +
            "join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "join mau_sac ms on ms.id = ctsp.id_mau_sac\n" +
            "join kich_thuoc kt on kt.id = ctsp.id_kich_thuoc \n" +
            "where sp.ma like :maSanPham and sp.ten like :tenSanPham", nativeQuery = true)
    List<ChiTietSanPham> getSanPhamByMaAndTen(@Param("maSanPham") String maSanPham, @Param("tenSanPham") String tenSanPham);

    @Query(value = "select sp.ma, sp.ten, ms.ten, kt.ten, ctsp.gia_ban, ctsp.so_luong_ton from chi_tiet_san_pham ctsp \n" +
            "join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "join mau_sac ms on ms.id = ctsp.id_mau_sac\n" +
            "join kich_thuoc kt on kt.id = ctsp.id_kich_thuoc \n" +
            "where sp.ma like :maSanPham and sp.ten like :tenSanPham and ms.ten like :mauSac and kt.ten like :kichThuoc", nativeQuery = true)
    List<ChiTietSanPham> getSanPhamByMaAndTenAndMauAndSize(@Param("maSanPham") String maSanPham, @Param("tenSanPham") String tenSanPham, @Param("mauSac") String mauSac, @Param("kichThuoc") String kichThuoc);



    @Query("SELECT c FROM ChiTietSanPham c WHERE c.mauSac.id = :idMauSac AND c.kichThuoc.id = :idKichThuoc AND c.sanPham.id = :productId" )
    List<ChiTietSanPham> findByMauSacAndKichThuoc(@Param("idMauSac") Long idMauSac, @Param("idKichThuoc") Long idKichThuoc, Long productId);



}

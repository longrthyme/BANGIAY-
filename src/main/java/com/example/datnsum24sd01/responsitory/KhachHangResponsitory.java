package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.enumation.TrangThai;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KhachHangResponsitory extends JpaRepository<KhachHang,Long> {
    @Query("""
                SELECT kh FROM KhachHang kh
                WHERE 
                   kh.trangThai = :trangThai and kh.id not in (1)
            """)
    List<KhachHang> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai
    );

    Optional<KhachHang> findByEmail(String email);
    Optional<KhachHang> findByEmailAndIdNot(String email,Long id);
    boolean existsBySdt(String sdt);
    boolean existsBySdtAndIdNot(String sdt,Long id);
    boolean existsByEmail(String email);
    boolean existsKhachHangByEmail(String email);

    boolean existsKhachHangBySdt(String sdt);

    List<KhachHang> findAllByTenContains(String ten);
    @Query("SELECT kh FROM KhachHang kh WHERE kh.ten LIKE :kw OR kh.sdt = :kw OR kh.email LIKE :kw")
    List<KhachHang> findByStr(@Param("kw") String keyWord);
    @Query("SELECT kh FROM KhachHang kh WHERE kh.trangThai = :status")
    List<KhachHang> findByStatus(@Param("status") Integer status);

    @Query("SELECT kh FROM KhachHang kh WHERE (kh.ten LIKE :kw OR kh.sdt LIKE :kw OR kh.email LIKE :kw) AND kh.trangThai = :status")
    List<KhachHang> findByStrAndStatus(@Param("kw") String keyWord, @Param("status") Integer status);
    @Query(value = "SELECT *\n" +
            "FROM khach_hang\n" +
            "WHERE id NOT IN (1);", nativeQuery = true)
    List<KhachHang> getListKhachHang();

}

package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    boolean existsNhanVienByCanCuocCongDan(String cccd);

    boolean existsNhanVienByCanCuocCongDanAndIdNot(String cccd, long id);

    boolean existsNhanVienBySdtAndIdNot(String cccd, long id);

    boolean existsNhanVienByEmailAndIdNot(String cccd, long id);

    boolean existsNhanVienBySdt(String sdt);

    boolean existsNhanVienByEmail(String email);

    @Query(value = "SELECT * FROM nhan_vien WHERE TRIM(LOWER(ten)) = TRIM(LOWER(:searchVal)) OR TRIM(LOWER(email)) = TRIM(LOWER(:searchVal)) OR TRIM(LOWER(sdt)) = TRIM(LOWER(:searchVal))  OR TRIM(LOWER(dia_chi)) = TRIM(LOWER(:searchVal))", nativeQuery = true)
    List<NhanVien> findByTenOrEmail(@Param("searchVal") String searchVal);


    @Query(value = "SELECT * FROM nhan_vien WHERE trang_thai = :status", nativeQuery = true)
    List<NhanVien> findByStatus(@Param("status") Integer status);

    Optional<NhanVien> findByEmail(String email);


}

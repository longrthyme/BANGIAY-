package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.DongSanPham;
import com.example.datnsum24sd01.enumation.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DongSanPhamResponsitory extends JpaRepository<DongSanPham,Long> {


    @Query("""
                SELECT dsp FROM DongSanPham dsp
                WHERE 
                   dsp.trangThai = :trangThai
            """)
    List<DongSanPham> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai
    );

    boolean existsByMa(String ma);

    boolean existsByTen(String ten);


    boolean existsByTenAndIdNot(String ten, Long id);
}

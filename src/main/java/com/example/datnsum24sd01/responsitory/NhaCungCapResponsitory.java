package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.enumation.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NhaCungCapResponsitory extends JpaRepository<NhaCungCap,Long> {

    @Query("""
                SELECT dsp FROM NhaCungCap dsp
                WHERE 
                   dsp.trangThai = :trangThai
            """)
    List<NhaCungCap> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai
    );

    boolean existsByMa(String ma);

    boolean existsByTen(String ten);


    boolean existsByTenAndIdNot(String ten, Long id);
}

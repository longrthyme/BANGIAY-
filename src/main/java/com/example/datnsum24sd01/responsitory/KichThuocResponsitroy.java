package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KichThuocResponsitroy extends JpaRepository<KichThuoc,Long> {


    @Query(value = "SELECT kt.* FROM kich_thuoc kt " +
            "JOIN chi_tiet_san_pham ctsp ON kt.id = ctsp.id_kich_thuoc " +
            "WHERE ctsp.id_san_pham = :productId",
            nativeQuery = true)
    List<KichThuoc> findByProductId(@Param("productId") Long productId);

}

package com.example.datnsum24sd01.responsitory;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnhBiaResponsitory extends JpaRepository<AnhBia,Long> {
    List<AnhBia> findBySanPham(SanPham sanPham);

    List<AnhBia> findBySanPhamId(Long id);
}

package com.example.datnsum24sd01.request;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.enumation.TrangThai;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ChiTietSanPhamRequest {

        private Long id;

        private BigDecimal giaBan;

        private BigDecimal giaMacDinh;

        private LocalDateTime ngaySua;

        private LocalDateTime ngayTao;

        private int soLuongTon;

        private int trangThai;

        private Long deGiay;

        private Long kichThuoc;

        private Long mauSac;

        private Long sanPham;


        private List<AnhBia> listAnh = new ArrayList<>();

        private String anhChinh;
}

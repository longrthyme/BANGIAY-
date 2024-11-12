package com.example.datnsum24sd01.request;

import com.example.datnsum24sd01.enumation.TrangThai;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ThuongHieuRequest {
    private Long id;

    private String ma;
    @NotBlank(message = "Tên không được để trống!")
    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private TrangThai trangThai;
}


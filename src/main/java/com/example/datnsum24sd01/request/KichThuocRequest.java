package com.example.datnsum24sd01.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class KichThuocRequest {

    private Long id;

    private String ma;

    private LocalDateTime ngaySua;
    private LocalDateTime ngayTao;

    @NotEmpty(message = "Tên không được để trống!")
    private String ten;
}

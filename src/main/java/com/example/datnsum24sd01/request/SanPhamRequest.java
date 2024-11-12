package com.example.datnsum24sd01.request;

import com.example.datnsum24sd01.entity.AnhBia;
import com.example.datnsum24sd01.enumation.TrangThai;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SanPhamRequest {
    private Long id;
    @NotBlank(message = "Mã không được để trống!")
    private String ma;
    @NotBlank(message = "Tên không được để trống!")
    private String ten;

    private Long nhaCungCap;

    private Long thuongHieu;
    private Long dongSanPham;

    private List<AnhBia> listAnh = new ArrayList<>();

    private String anhChinh;

    private TrangThai trangThai;

    @NotBlank(message = "Mô tả không được để trống!")
    private String moTa;

    private List<MultipartFile> fileImages;


}

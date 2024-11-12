package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.custom.ChiTietSanPhamCustomerCt;
import com.example.datnsum24sd01.dto.Anhspcustom;
import com.example.datnsum24sd01.dto.ChiTietSanPhamDTO;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BanHangOnlineCustomService {
    Page<ChiTietSanPham> pageAllInShop(Integer pageNo, Integer size);

    Integer nextPage(Integer pageNo);

    List<ChiTietSanPhamCustomerCt> list3Custom();



    List<ChiTietSanPhamCustomerCt> list4Random();

    List<Anhspcustom> listAnhDetail(Long id);

    ChiTietSanPham getById(Long id);



    public ChiTietSanPhamDTO convertToDTO(ChiTietSanPham chiTietSanPham);

    public List<ChiTietSanPhamDTO> convertToDTOList(List<ChiTietSanPham> chiTietSanPhamList);

    Page<ChiTietSanPhamDTO> findAllByCondition(List<String> tenThuongHieu, List<String> tenDongSanPham, List<String> tenKichThuoc, List<String> tenDeGiay, List<String> tenMauSac, Double minGia, Double maxGia, int page, int pageSize, String sortField, String tenSanPham);
}

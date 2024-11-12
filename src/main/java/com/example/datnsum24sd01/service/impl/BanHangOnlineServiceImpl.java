package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.custom.ChiTietSanPhamCustomerCt;
import com.example.datnsum24sd01.dto.Anhspcustom;
import com.example.datnsum24sd01.dto.ChiTietSanPhamDTO;
import com.example.datnsum24sd01.dto.ChiTietSanPhamMapper;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.SanPham;
import com.example.datnsum24sd01.responsitory.BanHangOnlineResponsitory;
import com.example.datnsum24sd01.service.BanHangOnlineCustomService;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BanHangOnlineServiceImpl  implements BanHangOnlineCustomService {
    //phân trang trong shop
    @Autowired
    private BanHangOnlineResponsitory repository;

    @Override
    public Page<ChiTietSanPham> pageAllInShop(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repository.pageAllInShop(pageable);
    }

    @Override
    public Integer nextPage(Integer pageNo) {
        Integer sizeList = repository.findAll().size();
        System.out.println(sizeList);
        Integer pageCount = (int) Math.ceil((double) sizeList / 20);
        System.out.println(pageCount);
        if (pageNo >= pageCount) {
            pageNo = 0;
        } else if (pageNo < 0) {
            pageNo = pageCount - 1;
        }
        System.out.println(pageNo);
        return pageNo;
    }
//list các sản phẩm trong shop



    @Override
    public List<ChiTietSanPhamCustomerCt> list3Custom() {
        return repository.list3Custom();
    }

    @Override
    public List<ChiTietSanPhamCustomerCt> list4Random() {
        return repository.list4Random();
    }

    @Override
    public List<Anhspcustom> listAnhDetail(Long id) {
        return repository.listAnhDetail(id);
    }

    @Override
    public ChiTietSanPham getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public ChiTietSanPhamDTO convertToDTO(ChiTietSanPham chiTietSanPham) {
        return null;
    }

    @Override
    public List<ChiTietSanPhamDTO> convertToDTOList(List<ChiTietSanPham> chiTietSanPhamList) {
        return chiTietSanPhamList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


//bộ lọc trong shop
    @Override
    public Page<ChiTietSanPhamDTO> findAllByCondition(
            List<String> tenThuongHieu,
            List<String> tenDongSanPham,
            List<String> tenKichThuoc,
            List<String> tenDeGiay,
            List<String> tenMauSac,
            Double minGia,
            Double maxGia,
            int page,
            int pageSize,
            String sortField,
            String tenSanPham
    ) {
        Specification<ChiTietSanPham> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<ChiTietSanPham, SanPham> sanPhamJoin = root.join("sanPham", JoinType.INNER);
            if (tenSanPham != null && !tenSanPham.isEmpty()) {
                predicates.add(cb.like(sanPhamJoin.get("ten"), "%" + tenSanPham + "%"));
            }
            if (tenThuongHieu != null && !tenThuongHieu.isEmpty()) {
                predicates.add(sanPhamJoin.get("thuongHieu").get("ten").in(tenThuongHieu));
            }
            if (tenDongSanPham != null && !tenDongSanPham.isEmpty()) {
                predicates.add(sanPhamJoin.get("dongSanPham").get("ten").in(tenDongSanPham));
            }
            if (tenDeGiay != null && !tenDeGiay.isEmpty()) {
                predicates.add(root.get("deGiay").get("ten").in(tenDeGiay));
            }
            if (tenKichThuoc != null && !tenKichThuoc.isEmpty()) {
                predicates.add(root.get("kichThuoc").get("ten").in(tenKichThuoc));
            }
            if (tenMauSac != null && !tenMauSac.isEmpty()) {
                predicates.add(root.get("mauSac").get("ten").in(tenMauSac));
            }
            if (minGia != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("giaBan"), minGia));
            }
            if (maxGia != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("giaBan"), maxGia));
            }
            predicates.add(cb.greaterThan(root.get("soLuongTon"), 0));
            predicates.add(cb.equal(root.get("trangThai"), 0));


            // Group by sanPham.id and select one record for each group
            query.groupBy(root.get("sanPham").get("id"));


            return cb.and(predicates.toArray(new Predicate[0]));
        };

        // Sử dụng ChiTietGiayRepository và PageRequest để lấy kết quả phân trang
        Sort sort = Sort.unsorted();

        if ("sapXepTheoGiaBanCaoDenThap".equals(sortField)) {
            sort = Sort.by("giaBan").descending();
        } else if ("sapXepTheoGiaBanThapDenCao".equals(sortField)) {
            sort = Sort.by("giaBan").ascending();
        }
        else if ("sapXepTheoTen".equals(sortField)) {
            sort = Sort.by("sanPham.ten").ascending();
        } else if ("sapXepTheoNgayTao".equals(sortField)) {
            sort = Sort.by("ngayTao").descending();
        }

        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return ChiTietSanPhamMapper.toDTOPage(repository.findAll(spec, pageable));
    }

}

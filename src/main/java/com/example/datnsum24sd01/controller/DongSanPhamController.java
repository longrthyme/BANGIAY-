package com.example.datnsum24sd01.controller;


import com.example.datnsum24sd01.entity.DongSanPham;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.DongSanPhamRequest;
import com.example.datnsum24sd01.service.DongSanPhamService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/dong-san-pham")
public class DongSanPhamController {

    private final DongSanPhamService dongSanPhamService;
    private Spingsecurity spingsecurity = new Spingsecurity();

    public DongSanPhamController(DongSanPhamService dongSanPhamService) {
        this.dongSanPhamService = dongSanPhamService;
    }



    Integer pageNo = 0;
    List<TrangThai> list = new ArrayList<>(Arrays.asList(TrangThai.DANG_HOAT_DONG, TrangThai.DUNG_HOAT_DONG));

    @GetMapping()
    public String getAllDSP(Model model) {

        model.addAttribute("listDongSp", dongSanPhamService.getList());
        model.addAttribute("trangThais", list);
        model.addAttribute("index", pageNo + 1);
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());

        return "admin-template/dong_san_pham/dong_san_pham";
    }

    @GetMapping("/trang-thai/{trangThai}")
    public String getByTrangThai(Model model,
                                 @PathVariable("trangThai") TrangThai trangThai) {

        model.addAttribute("trangThais", list);
        model.addAttribute("listDongSp", dongSanPhamService.getByTrangThai(trangThai));
        return "admin-template/dong_san_pham/dong_san_pham";
    }

    @GetMapping("/view-add-dong-san-pham")
    public String getViewAdd(@ModelAttribute("dongSp") DongSanPham dongSanPham, Model model) {


        return "admin-template/dong_san_pham/them_dong_san_pham";
    }

    @PostMapping("/add")
    public String addNew(@Valid @ModelAttribute("dongSp") DongSanPhamRequest dongSanPham,
                         BindingResult bindingResult, Model model) {

        String ma = dongSanPham.getMa();
        String ten = dongSanPham.getTen();
        if (bindingResult.hasErrors()) {
            return "admin-template/dong_san_pham/them_dong_san_pham";
        }

        if (dongSanPhamService.existsByTen(ten)) {
            model.addAttribute("errorTen", "Tên  đã tồn tại");

            return "admin-template/dong_san_pham/them_dong_san_pham";
        }
        model.addAttribute("success", "Thêm thành công");
        dongSanPhamService.save(dongSanPham);
        return "redirect:/admin/dong-san-pham?success";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        try {
            dongSanPhamService.remove(id);
            model.addAttribute("success", "Xóa thành công Dòng Sản Phẩm");
            return "redirect:/admin/dong-san-pham?success";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Không thể xóa bản ghi vì có ràng buộc khóa ngoại.");
            return "redirect:/admin/dong-san-pham?errorMessage";
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi xóa bản ghi.");
            return "redirect:/admin/dong-san-pham?errorMessage";
        }

    }

    @GetMapping("view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id,
                             Model model) {

        model.addAttribute("dongSp", dongSanPhamService.findById(id));
        return "admin-template/dong_san_pham/sua_dong_san_pham";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dongSp") DongSanPhamRequest dongSanPham,
                         BindingResult bindingResult,
                         Model model) {

        String ten = dongSanPham.getTen();
        Long id = dongSanPham.getId();
        if (bindingResult.hasErrors()) {
            return "admin-template/dong_san_pham/sua_dong_san_pham";
        } else {
            if (dongSanPhamService.existsByTenAndIdNot(ten, id)) {
                model.addAttribute("errorTen", "Tên  đã tồn tại");
                return "admin-template/dong_san_pham/sua_dong_san_pham";
            }

            model.addAttribute("success", "Thêm thành công");
            dongSanPhamService.update(dongSanPham);
            return "redirect:/admin/dong-san-pham?success";
        }
    }
}

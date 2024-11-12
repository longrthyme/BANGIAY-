package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.NhaCungCap;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.NhaCungCapRequest;
import com.example.datnsum24sd01.request.ThuongHieuRequest;
import com.example.datnsum24sd01.service.NhaCungCapServiec;
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
@RequestMapping("/admin/nha-cung-cap")
public class NhaCungCapController {

    private Spingsecurity spingsecurity = new Spingsecurity();

    private final NhaCungCapServiec nhaCungCapServiec;

    public NhaCungCapController(NhaCungCapServiec nhaCungCapServiec) {
        this.nhaCungCapServiec = nhaCungCapServiec;
    }



    Integer pageNo = 0;
    List<TrangThai> list = new ArrayList<>(Arrays.asList(TrangThai.DANG_HOAT_DONG, TrangThai.DUNG_HOAT_DONG));

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());

        model.addAttribute("listnhacc", nhaCungCapServiec.getList());
        model.addAttribute("trangThais", list);
        model.addAttribute("index", pageNo + 1);

        return "admin-template/nha_cung_cap/nha_cung_cap";
    }

    @GetMapping("/trang-thai/{trangThai}")
    public String getByTrangThai(Model model,
                                 @PathVariable("trangThai") TrangThai trangThai) {

        model.addAttribute("trangThais", list);
        model.addAttribute("listnhacc", nhaCungCapServiec.getByTrangThai(trangThai));

        return "admin-template/nha_cung_cap/nha_cung_cap";
    }

    @GetMapping("/view-add-nha-cung-cap")
    public String getViewAdd(@ModelAttribute("nhacc") NhaCungCapRequest nhaCungCap, Model model) {

        return "admin-template/nha_cung_cap/them_nha_cung_cap";
    }
    @PostMapping("/add")
    public String addNew(@Valid @ModelAttribute("nhacc") NhaCungCapRequest nhaCungCap,
                         BindingResult bindingResult, Model model) {


        String ten = nhaCungCap.getTen();
        if (bindingResult.hasErrors()) {
            return "admin-template/nha_cung_cap/them_nha_cung_cap";
        }
        if (nhaCungCapServiec.existsByTen(ten)) {

            model.addAttribute("errorTen", "Tên  đã tồn tại");
            return "admin-template/nha_cung_cap/them_nha_cung_cap";
        }
        model.addAttribute("success", "Thêm thành công");
        nhaCungCapServiec.save(nhaCungCap);
        return "redirect:/admin/nha-cung-cap?success";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {

        try {
         nhaCungCapServiec.remove(id);
            model.addAttribute("success", "Xóa thành công Nhà Cung Cấp");
            return "redirect:/admin/nha-cung-cap?success";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Không thể xóa bản ghi vì có ràng buộc khóa ngoại.");
            return "redirect:/admin/nha-cung-cap?errorMessage";
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi xóa bản ghi.");
            return "redirect:/admin/nha-cung-cap?errorMessage";
        }

    }

    @GetMapping("view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id,
                             Model model) {

        model.addAttribute("nhacc", nhaCungCapServiec.findById(id));
        return "admin-template/nha_cung_cap/sua_nha_cung_cap";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("nhacc")NhaCungCapRequest nhaCungCap,
                         BindingResult bindingResult,
                         Model model) {

        String ten = nhaCungCap.getTen();
        Long id = nhaCungCap.getId();
        if (bindingResult.hasErrors()) {

            return "admin-template/nha_cung_cap/sua_nha_cung_cap";
        } else {
            if (nhaCungCapServiec.existsByTenAndIdNot(ten, id)) {
                model.addAttribute("errorTen", "Tên Nhà Cung Cấp Đã đã tồn tại");
                return "admin-template/nha_cung_cap/sua_nha_cung_cap";
            }

            model.addAttribute("success", "Sửa Thành Công Nhà Cung Cấp ");
            nhaCungCapServiec.update(nhaCungCap);
            return "redirect:/admin/nha-cung-cap?success";
        }
    }

}


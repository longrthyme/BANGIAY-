package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import com.example.datnsum24sd01.request.PhieuGiamGiaRequest;
import com.example.datnsum24sd01.service.NhanVienService;
import com.example.datnsum24sd01.service.PhieuGiamGiaService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/phieu-giam-gia")
public class PhieuGiamGiaController {
    @Autowired
    private PhieuGiamGiaService service;
    @Autowired
    private NhanVienService nhanVienService;

//    @Autowired
//    private EmailService emailService;

    List<TrangThaiPhieuKhuyenMai> list = new ArrayList<>(Arrays.asList(TrangThaiPhieuKhuyenMai.DANG_DIEN_RA, TrangThaiPhieuKhuyenMai.DA_KET_THUC, TrangThaiPhieuKhuyenMai.SAP_DIEN_RA));
    private Spingsecurity spingsecurity = new Spingsecurity();

    @GetMapping()
    public String getAllphieukhuyenmai(Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());
        model.addAttribute("listMaGiam", service.getAll());
        model.addAttribute("listTrangThai", list);
        return "admin-template/phieu_giam_gia/phieu_giam_gia";
    }

    @GetMapping("/deletepgg/{id}")
    public String deletepgg(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String noti =
               service.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", noti);
        return "redirect:/admin/phieu-giam-gia";
    }
    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute("phieuGiamGia") PhieuGiamGiaRequest phieuGiamGiaRequest,
                          Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        List<NhanVien> nhanVienList = nhanVienService.getAll();
        model.addAttribute("nhanVien", nhanVienList);
        model.addAttribute("phieuGiamGia", new PhieuGiamGia());
        return "admin-template/phieu_giam_gia/them_phieu_giam_gia";
    }

    @PostMapping("/add")
    public String themphieukhuyenmai(
            @Valid
            @ModelAttribute("phieuGiamGia") PhieuGiamGiaRequest phieuGiamGiaRequest,
            BindingResult bindingResult,
            Model model
    ) {
        String ten = phieuGiamGiaRequest.getTen();
        if (bindingResult.hasErrors()) {
            return "admin-template/phieu_giam_gia/them_phieu_giam_gia";
        } else {
            if (service.existsByTen(ten)) {
                model.addAttribute("errorTen", "Tên Phiếu giảm giá đã tồn tại");
                return "admin-template/phieu_giam_gia/them_phieu_giam_gia";
            }
            service.add(phieuGiamGiaRequest);
            model.addAttribute("successMessage", "Thêm thành công Phiếu giảm giá.");
            return "redirect:/admin/phieu-giam-gia";
        }
    }



    @GetMapping("/trang-thai/{trangThai}")
    public String getByTrangThai(Model model,
                                 @PathVariable("trangThai") TrangThaiPhieuKhuyenMai trangThaiKhuyenMai) {

        model.addAttribute("listMaGiam", service.getByTrangThai(trangThaiKhuyenMai));
        model.addAttribute("listTrangThai", list);
        return "admin-template/phieu_giam_gia/phieu_giam_gia";
    }


    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            @PathVariable("id") Long id,
            Model model
    ) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        model.addAttribute("phieuGiamGia", service.getById(id));

        return "admin-template/phieu_giam_gia/sua_phieu_giam_gia";
    }

    @PostMapping("/update")
    public String updatepkm(@Valid @ModelAttribute("phieuGiamGia") PhieuGiamGiaRequest phieuGiamGiaRequest,
                         BindingResult bindingResult,
                         Model model) {

        String ten = phieuGiamGiaRequest.getTen();
        Long id = phieuGiamGiaRequest.getId();
        if (bindingResult.hasFieldErrors("ten") || bindingResult.hasFieldErrors("mucGiamGia")
                || bindingResult.hasFieldErrors("mucGiamToiDa") || bindingResult.hasFieldErrors("soLuong")
                || bindingResult.hasFieldErrors("giaTriDonHang") || bindingResult.hasFieldErrors("ngayKetThuc")) {

            return "admin-template/phieu_giam_gia/sua_phieu_giam_gia";
        } else {
            if (service.existsByTenAndIdNot(ten, id)) {
                model.addAttribute("errorTen", "Tên Phiếu giảm giá đã tồn tại");
                return "admin-template/phieu_giam_gia/sua_phieu_giam_gia";
            }
            service.update(phieuGiamGiaRequest);
            return "redirect:/admin/phieu-giam-gia";
        }

    }

    @GetMapping("/huy/{id}")
    public String tattrangthaiphieukhuyenmai(@PathVariable("id") Long id) {
        service.huy(id);
        return "redirect:/admin/phieu-giam-gia";
    }
    @GetMapping("/bat/{id}")
    public String battrangthaiphieukhuyenmai(@PathVariable("id") Long id) {
        service.bat(id);
        return "redirect:/admin/phieu-giam-gia";
    }

    @GetMapping("/filter")
    public String filterNgay(Model model,
                             @Param("trangThai") TrangThaiPhieuKhuyenMai trangThaiKhuyenMai,
                             @Param("startDate") LocalDate startDate,
                             @Param("endDate") LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            model.addAttribute("listTrangThai", list);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            return "redirect:/admin/phieu-giam-gia";
        }
        model.addAttribute("listTrangThai", list);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("listMaGiam", service.findMaGiamGia(startDate, endDate, trangThaiKhuyenMai));

        return "admin-template/phieu_giam_gia/phieu_giam_gia";
    }


}

package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.request.DiaChiRequest;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.DiaChiService;
import com.example.datnsum24sd01.service.KhachHangService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {


    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private KhachHangResponsitory khachHangRepository;

    @Autowired
    private EmailService emailService;
    private Spingsecurity spingsecurity = new Spingsecurity();

    List<TrangThai> list = new ArrayList<>(Arrays.asList(TrangThai.DANG_HOAT_DONG, TrangThai.DUNG_HOAT_DONG));
    @GetMapping()
    public String getAll(Model model,
                         @RequestParam(name = "keyWord", required = false) String keyWord,
                         @RequestParam(name = "status", required = false) String status) {
        List<KhachHang> kh;
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());


        if (keyWord == null || keyWord.isEmpty()) {
            if (status == null || status.isEmpty()) {
                kh = khachHangService.getAll();
            } else {
                kh = khachHangRepository.findByStatus(Integer.valueOf(status));
            }
        } else {
            String k = "%" + keyWord + "%";
            if (status == null || status.isEmpty()) {
                kh = khachHangRepository.findByStr(k);
            } else {
                kh = khachHangRepository.findByStrAndStatus(k, Integer.valueOf(status));
            }
        }

        model.addAttribute("list", kh);
        model.addAttribute("trangThais", list);

        model.addAttribute("keyWord", keyWord);
        model.addAttribute("selectedStatus", status);
        return "admin-template/khach_hang/khach_hang";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) { Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());


        model.addAttribute("newKhachHang", new KhachHangRequest());
        return "admin-template/khach_hang/them_khach_hang";
    }

    @GetMapping("/trang-thai/{trangThai}")
    public String getByTrangThai(Model model,
                                 @PathVariable("trangThai") TrangThai trangThai) {

        model.addAttribute("trangThais", list);
//        model.addAttribute("diaChi", new DiaChiRequest());
//        model.addAttribute("listDC", diaChiService.getAll());


        return "admin-template/khach_hang/khach_hang";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id, Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());


        KhachHang khachHang = khachHangService.getById(id);
        model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(id));
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("idKhachHang", id);
        model.addAttribute("diaChi", new DiaChiRequest());

        return "admin-template/khach_hang/sua_khach_hang";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("newKhachHang") KhachHangRequest khachHangRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin-template/khach_hang/them_khach_hang";
        }
        khachHangService.add(khachHangRequest);
        emailService.sendNewAccountKHEmail(khachHangRequest.getEmail(), khachHangRequest.getEmail(), khachHangRequest.getMatKhau());

        return "redirect:/admin/khach-hang";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("khachHang") KhachHangRequest khachHangRequest,
                         BindingResult bindingResult,
                         Model model) {

        Long id = khachHangRequest.getId();
        String sdt = khachHangRequest.getSdt();
        if (bindingResult.hasErrors()) {
            model.addAttribute("diaChi",new DiaChiRequest());
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(id));
            model.addAttribute("idKhachHang", id);

            return "admin-template/khach_hang/sua_khach_hang";
        }

        if (khachHangService.existsBySdtAndIdNot(sdt, id)) {
            model.addAttribute("errorTen", "Số điện thoại đã tồn tại");
            model.addAttribute("diaChi",new DiaChiRequest());
            model.addAttribute("khachHang", khachHangService.getById(id));
            model.addAttribute("idKhachHang", id);
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(id));

            return "admin-template/khach_hang/sua_khach_hang";
        }
        model.addAttribute("success", "Cập nhật thành công!");
        khachHangService.update(khachHangRequest);
        return "redirect:/admin/khach-hang?success";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String noti = khachHangService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", noti);
        return "redirect:/admin/khach-hang";
    }

    @GetMapping("/sdt/{sdt}")
    public ResponseEntity<Boolean> checkSdtDuplicate(@PathVariable String sdt) {
        boolean exists = khachHangService.checkSdtDuplicate(sdt);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        boolean exists = khachHangService.checkEmailDuplicate(email);
        return ResponseEntity.ok(exists);
    }
    @PostMapping("/add-dia-chi/{idKhachHang}")
    public String addDiaChi(
            @Valid
            @ModelAttribute("diaChi") DiaChiRequest diaChiRequest,
            @PathVariable("idKhachHang") String idKhachHang,
            @RequestParam("phuongXaID") String phuongXa,
            @RequestParam("quanHuyenID") String quanHuyen,
            @RequestParam("thanhPhoID") String thanhPho
    ) {
        diaChiService.add(diaChiRequest, Long.valueOf(idKhachHang), thanhPho, quanHuyen, phuongXa);
        return "redirect:/admin/khach-hang/view-update/" + idKhachHang + "?success";
    }

    @PostMapping("/update-dia-chi/{id}/{idKH}")
    public String updateDiaChi(
            @PathVariable("id") Long id,
            @PathVariable("idKH") Long idKH,
            @ModelAttribute("diaChi") DiaChiRequest diaChiRequest,
            @RequestParam("phuongXa") String phuongXa,
            @RequestParam("quanHuyen") String quanHuyen,
            @RequestParam("thanhPho") String thanhPho,
            Model model
    ) {

        diaChiService.update(diaChiRequest, thanhPho, quanHuyen, phuongXa);
        return "redirect:/admin/khach-hang/view-update/" + idKH;
    }

    @GetMapping("/delete-dia-chi/{id}/{idKH}")
    public String deleteDiaChi(@PathVariable("id") Long id,
                               @PathVariable("idKH") Long idKH) {

        diaChiService.remove(id);
        return "redirect:/admin/khach-hang/view-update/" + idKH;
    }
    @PostMapping("/forgot")
    public String forgotPassword(@RequestParam String email,  RedirectAttributes redirectAttributes, Model model) {
        boolean result = khachHangService.forgotpassword(email);
        if (result) {
            redirectAttributes.addFlashAttribute("status", "Mật khẩu mới đã được gửi đến email của bạn.");
        } else {
            redirectAttributes.addFlashAttribute("status", "Có lỗi xảy ra hoặc email không tồn tại. Vui lòng kiểm tra và thử lại.");
        }


        return "redirect:/login";
    }
}
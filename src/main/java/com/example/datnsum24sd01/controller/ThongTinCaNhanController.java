package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.request.DiaChiRequest;
import com.example.datnsum24sd01.request.KhachHangRequest;
import com.example.datnsum24sd01.service.DiaChiService;
import com.example.datnsum24sd01.service.KhachHangService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/thongtincanhan")
public class ThongTinCaNhanController {
    private final KhachHangService khachHangService;
    private final DiaChiService diaChiService;
    private Spingsecurity spingsecurity = new Spingsecurity();

    public ThongTinCaNhanController(KhachHangService khachHangService, DiaChiService diaChiService) {
        this.khachHangService = khachHangService;
        this.diaChiService = diaChiService;
    }

    /**
     * Get User By IdKh
     *
     * @param model
     * @return
     */
    @GetMapping()
    public String getAll(Model model) {
        Long id = spingsecurity.getCurrentUserId();
        if (id == null) {
            return "redirect:/login";
        }
        KhachHang khachHang = khachHangService.getById(id);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(id));
        model.addAttribute("diaChi", new DiaChiRequest());
        return "customer-template/ThongTinTaiKhoan";
    }

    /**
     * Update KhachHang
     *
     * @param khachHangRequest
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("khachHang") KhachHangRequest khachHangRequest,
                         BindingResult bindingResult,
                         Model model
    ) {
        Long id = khachHangRequest.getId();
        String sdt = khachHangRequest.getSdt();
        KhachHang khachHang = khachHangService.getById(id);
        if (khachHangService.existsBySdtAndIdNot(sdt, id)) {
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(id));
            model.addAttribute("diaChi", new DiaChiRequest());
            model.addAttribute("errorTen", "Số điện thoại đã tồn tại");
            return "customer-template/ThongTinTaiKhoan";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("errorTen", "Số điện thoại hoặc tên không được để trống");
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(id));
            model.addAttribute("diaChi", new DiaChiRequest());
            return "customer-template/ThongTinTaiKhoan";
        }
        model.addAttribute("success", "Cập nhật thành công!");
        khachHangService.update(khachHangRequest);
        return "redirect:/admin/thongtincanhan?success";
    }

    /**
     * Âdd new address
     *
     * @param diaChiRequest
     * @param idKhachHang
     * @return
     */
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
        return "redirect:/admin/thongtincanhan?success";
    }

    /**
     * Update adress
     *
     * @param id
     * @param idKH
     * @param diaChiRequest
     * @param model
     * @return
     */
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
        KhachHang khachHang = khachHangService.getById(idKH);
        if (diaChiRequest.getTenNguoiNhan().isEmpty()) {
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(idKH));
            model.addAttribute("diaChi", new DiaChiRequest());
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("errorTen", "Tên người nhận không được để trống!");
            return "customer-template/ThongTinTaiKhoan";
        }
        if (diaChiRequest.getSdt().isEmpty()) {
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(idKH));
            model.addAttribute("diaChi", new DiaChiRequest());
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("errorTen", "Số điện thoại không được để trống!");
            return "customer-template/ThongTinTaiKhoan";
        }
        if (diaChiRequest.getDiaChi().isEmpty()) {
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(idKH));
            model.addAttribute("diaChi", new DiaChiRequest());
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("errorTen", "Địa chỉ không được để trống!");
            return "customer-template/ThongTinTaiKhoan";
        } else {
            diaChiService.update(diaChiRequest, thanhPho, quanHuyen, phuongXa);
        }
        return "redirect:/admin/thongtincanhan?success";
    }

    /**
     * Delete address By id
     *
     * @param id
     * @param idKH
     * @return
     */
    @GetMapping("/delete-dia-chi/{id}/{idKH}")
    public String deleteDiaChi(@PathVariable("id") Long id,
                               @PathVariable("idKH") Long idKH

    ) {
        diaChiService.remove(id);
        return "redirect:/admin/thongtincanhan?success";
    }

    /**
     * Change passwword
     *
     * @param idKh
     * @param oldPassword
     * @param newPassword
     * @param model
     * @return
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("id") Long idKh,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model) {
        KhachHang khachHang = khachHangService.getById(idKh);
        if (khachHang.getMatKhau().isEmpty()) {
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(idKh));
            model.addAttribute("diaChi", new DiaChiRequest());
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("errorTen", "Mật khẩu không được để trống!");
            return "customer-template/user/profile";
        }
        if (!khachHang.getMatKhau().equals(oldPassword)) {
            model.addAttribute("listDC", diaChiService.getAllTheoKhachHang(idKh));
            model.addAttribute("diaChi", new DiaChiRequest());
            model.addAttribute("khachHang", khachHang);
            model.addAttribute("errorTen", "Mật khẩu bạn nhập không trùng khớp!");
            return "customer-template/user/profile";
        } else {

            khachHangService.changeUserPassword(idKh, oldPassword, newPassword);
            return "redirect:/admin/thongtincanhan?success";
        }
    }
    }

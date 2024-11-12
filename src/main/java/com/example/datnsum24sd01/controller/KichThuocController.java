package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.request.KichThuocRequest;
import com.example.datnsum24sd01.service.KhichThuocService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/kich-thuoc")
public class KichThuocController {


    private Spingsecurity spingsecurity = new Spingsecurity();

    @Autowired
    private KhichThuocService kichThuocService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        model.addAttribute("listKichThuoc", kichThuocService.getAllKichThuoc());
        return "admin-template/kich_thuoc/kich_thuoc";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("kichThuocRequest", new KichThuocRequest());
        return "admin-template/kich_thuoc/them_kich_thuoc";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable long id) {
        KichThuoc kichThuoc = this.kichThuocService.getOne(id);
        model.addAttribute("kichThuocUpdate", kichThuoc);
        return "admin-template/kich_thuoc/sua_kich_thuoc";
    }

    @PostMapping("/themKichThuoc")
    public String addKichThuoc(@Valid @ModelAttribute("kichThuocRequest") KichThuocRequest kichThuocRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-template/kich_thuoc/them_kich_thuoc";
        }
        kichThuocService.add(kichThuocRequest);

        return "redirect:/admin/kich-thuoc";
    }

    @PostMapping("/suaKichThuoc")
    public String update(@ModelAttribute("kichThuocRequest") KichThuoc kichThuocRequest) {
        this.kichThuocService.update(kichThuocRequest);
        return "redirect:/admin/kich-thuoc";
    }

    @GetMapping("/xoaKichThuoc/{id}")
    public String deleteKichThuoc(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String note = kichThuocService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/kich-thuoc";
    }



}




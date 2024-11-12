package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.request.KichThuocRequest;
import com.example.datnsum24sd01.request.MauSacRequest;
import com.example.datnsum24sd01.service.MauSacService;
import com.example.datnsum24sd01.service.impl.KichThuocServiceImpl;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {


    @Autowired
    private MauSacService mauSacService;
    private Spingsecurity spingsecurity = new Spingsecurity();


    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        model.addAttribute("listMauSac", mauSacService.getAllMauSac());
        return "admin-template/mau_sac/mau_sac";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("mauSacRequest", new MauSacRequest());
        return "admin-template/mau_sac/them_mau_sac";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable long id) {
        MauSac mauSac = this.mauSacService.getOne(id);
        model.addAttribute("mauSacUpdate", mauSac);
        return "admin-template/mau_sac/sua_mau_sac";
    }

    @PostMapping("/themMauSac")
    public String addMauSac(@Valid @ModelAttribute("mauSacRequest") MauSacRequest mauSacRequest,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-template/mau_sac/them_mau_sac";
        }
        mauSacService.add(mauSacRequest);

        return "redirect:/admin/mau-sac";
    }

    @PostMapping("/suaMauSac")
    public String update(@ModelAttribute("mauSacRequest") MauSac mauSacRequest) {
        this.mauSacService.update(mauSacRequest);
        return "redirect:/admin/mau-sac";
    }

    @GetMapping("/xoaMauSac/{id}")
    public String deleteMauSac(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String note = mauSacService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/mau-sac";
    }


}



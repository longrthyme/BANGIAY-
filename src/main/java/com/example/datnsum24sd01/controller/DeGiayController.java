package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.DeGiay;
import com.example.datnsum24sd01.request.DeGiayRequest;
import com.example.datnsum24sd01.service.Degiayserviec;
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
@RequestMapping("/admin/de-giay")
public class DeGiayController {

    @Autowired
    private Degiayserviec deGiayService;
    private Spingsecurity spingsecurity = new Spingsecurity();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        model.addAttribute("listDeGiay", deGiayService.getAllDeGiay());
        return "admin-template/de_giay/de_giay";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("deGiayRequest", new DeGiayRequest());
        return "admin-template/de_giay/them_de_giay";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable long id) {
        DeGiay deGiay = this.deGiayService.getOne(id);
        model.addAttribute("deGiayUpdate", deGiay);
        return "admin-template/de_giay/sua_de_giay";
    }

    @PostMapping("/themDeGiay")
    public String addDeGiay(@Valid @ModelAttribute("deGiayRequest") DeGiayRequest deGiayRequest,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-template/de_giay/them_de_giay";
        }
        deGiayService.add(deGiayRequest);

        return "redirect:/admin/de-giay";
    }

    @PostMapping("/suaDeGiay")
    public String update(@ModelAttribute("deGiayRequest") DeGiay deGiayRequest) {
        this.deGiayService.update(deGiayRequest);
        return "redirect:/admin/de-giay";
    }

    @GetMapping("/xoaDeGiay/{id}")
    public String deleteDeGiay(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String note = deGiayService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/de-giay";
    }
}


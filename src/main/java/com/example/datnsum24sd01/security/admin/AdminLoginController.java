package com.example.datnsum24sd01.security.admin;

import com.example.datnsum24sd01.request.RegisterRequest;
import com.example.datnsum24sd01.service.KhachHangService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminLoginController {
    @Autowired
 private    KhachHangService khachHangService;
    @GetMapping("/login")

    public String LoginAdmin(Model model) {

        model.addAttribute("khachHang", new RegisterRequest());
        model.addAttribute("validateRegis", false);
        return "admin-template/login";
    }
    @PostMapping("/login")
    public String loginAdmin(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // Dummy authentication logic for demonstration purposes
        boolean isAuthenticated = authenticate(email, password);

        if (!isAuthenticated) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        }
        redirectAttributes.addAttribute("thanhcong", true);
        // Successful login logic here
        return "redirect:admin/ban-hang";
    }

    private boolean authenticate(String email, String password) {
        // Replace with actual authentication logic (e.g., checking against a database)
        return "admin@example.com".equals(email) && "password".equals(password);
    }
    @PostMapping("/dang-ky")
    public String dangKy(
            @Valid @ModelAttribute("khachHang") RegisterRequest khachHang,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(result.hasErrors() || khachHangService.existsByEmail(khachHang.getEmail()) || khachHangService.existsBySdt(khachHang.getSdt())){
            model.addAttribute("validateRegis", true);
            model.addAttribute("exSdt", khachHangService.existsBySdt(khachHang.getSdt()));
            model.addAttribute("exEmail", khachHangService.existsByEmail(khachHang.getEmail()));
            System.out.println("In error ");
            model.addAttribute("errorLogin", true);
            return "admin-template/login";
        }
        khachHangService.registration(khachHang);
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công, check mail để lấy password !");

        return "redirect:/login";
    }
    @GetMapping("/quen-mat-khau")
    public String QuenMatKhau(Model model) {

        return "admin-template/fogotpassword";

    }
    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/ban-hang";
        }
        return "redirect:/beestore/trang-chu";

    }

}

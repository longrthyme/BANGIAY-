package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.request.NhanVienRequest;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.service.NhanVienService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    //    employee status list
    private final List<String> employeeStatus = Arrays.asList("Đang làm", "Nghỉ việc");

    // selected employee index
    private Integer empStatusIndex = 0;

    // Create a map from index to status
    Map<Integer, String> statusMap = new HashMap<>();


    public NhanVienController() {
        statusMap = new HashMap<>();
        for (int i = 0; i < employeeStatus.size(); i++) {
            statusMap.put(i, employeeStatus.get(i));
        }
    }

    private Spingsecurity spingsecurity = new Spingsecurity();
    @GetMapping()
    public String getAll(Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        List<NhanVien> nhanVienList = nhanVienService.getAll();

        model.addAttribute("employeeStatus", employeeStatus);
        model.addAttribute("empStatusIndex", empStatusIndex);
        model.addAttribute("statusMap", statusMap);
        model.addAttribute("nhanVien", nhanVienList);
        return "admin-template/nhan_vien/nhan_vien";
    }


    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam("tSearch") String tSearch,
                         @RequestParam(name = "empStatus", required = false) Integer empStatus) {

        empStatusIndex = empStatus;
        List<NhanVien> filterEmployeeList = nhanVienRepository.findAll();


        // case has input search text
        if(!tSearch.isBlank() ) {
            filterEmployeeList = nhanVienRepository.findByTenOrEmail(tSearch);
        } else {
            filterEmployeeList = nhanVienRepository.findAll();
        }

        // filter status
        filterEmployeeList = filterEmployeeList.stream()
                .filter(employee -> employee.getTrangThai() == empStatusIndex)
                .collect(Collectors.toList());

        // Add attributes to the model
        model.addAttribute("nhanVien", filterEmployeeList);
        model.addAttribute("employeeStatus", employeeStatus);
        model.addAttribute("empStatusIndex", empStatusIndex);
        model.addAttribute("statusMap", statusMap);

        return "admin-template/nhan_vien/nhan_vien";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable long id
    ) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());


        NhanVien nhanVien = this.nhanVienService.getOne(id);
        model.addAttribute("nhanVienForUpdating", nhanVien);

        return "admin-template/nhan_vien/sua_nhan_vien";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        model.addAttribute("nhanVienRequest", new NhanVienRequest());
        return "admin-template/nhan_vien/them_nhan_vien";
    }

    @PostMapping("/add")
    public String addd(@ModelAttribute("nhanVienRequest") NhanVienRequest nhanVienRequest) {
        nhanVienService.add(nhanVienRequest);
        return "redirect:/admin/nhan-vien"; //
    }
    @GetMapping("/thay-doi-trang/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        nhanVienService.thayDoiTrangThai(id);
        return "redirect:/admin/nhan-vien"; //
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("nhanVienRequest") NhanVien nhanVienRequest) {

        this.nhanVienService.update(nhanVienRequest);
        return "redirect:/admin/nhan-vien"; //
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String noti =
                nhanVienService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", noti);
        return "redirect:/admin/nhan-vien";
    }

    //    kiểm tra trùng lặp
    @GetMapping("/phone/{sdt}")
    public ResponseEntity<Boolean> checkPhoneDuplicate(@PathVariable String sdt) {
        boolean exists = nhanVienService.checkPhoneDuplicate(sdt);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Boolean> checkCccdDuplicate(@PathVariable String cccd) {
        boolean exists = nhanVienService.checkCccdDuplicate(cccd);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/cccd-update")
    public ResponseEntity<Boolean> checkCccdDuplicateUpdate(@RequestParam String cccd, @RequestParam Long id) {
        boolean exists = nhanVienRepository.existsNhanVienByCanCuocCongDanAndIdNot(cccd, id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/sdt-update")
    public ResponseEntity<Boolean> checkSdtDuplicateUpdate(@RequestParam String sdt, @RequestParam Long id) {
        boolean exists = nhanVienRepository.existsNhanVienBySdtAndIdNot(sdt, id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/email-update")
    public ResponseEntity<Boolean> checkEmailDuplicateUpdate(@RequestParam String email, @RequestParam Long id) {
        boolean exists = nhanVienRepository.existsNhanVienByEmailAndIdNot(email, id);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        boolean exists = nhanVienService.checkEmailDuplicate(email);
        return ResponseEntity.ok(exists);
    }


}

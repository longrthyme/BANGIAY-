package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.GioHangChiTietService;
import com.example.datnsum24sd01.service.HoaDonChiTietService;
import com.example.datnsum24sd01.service.HoaDonService;
import com.example.datnsum24sd01.service.KhachHangService;
import com.example.datnsum24sd01.service.NhanVienService;
import com.example.datnsum24sd01.service.ThongKeService;
import com.example.datnsum24sd01.worker.Spingsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {
    private final ThongKeService thongKeService;
    private final HoaDonService hoaDonService;
    private final HoaDonChiTietService hoaDonChiTietService;
    private final GioHangChiTietService gioHangChiTietService;
    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    public ThongKeController(ThongKeService thongKeService, HoaDonService hoaDonService, HoaDonChiTietService hoaDonChiTietService, GioHangChiTietService gioHangChiTietService) {
        this.thongKeService = thongKeService;
        this.hoaDonService = hoaDonService;
        this.hoaDonChiTietService = hoaDonChiTietService;
        this.gioHangChiTietService = gioHangChiTietService;

    }

    private Spingsecurity spingsecurity = new Spingsecurity();
    /**
     * Thống Kê
     * @param model
     * @param from
     * @param to
     * @return
     */
    @GetMapping()
    public String hienThi(Model model,
                          @RequestParam(value = "startDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                          @RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null) {
            return "redirect:/login";
        }
        from = (from != null) ? from : LocalDate.of(2000, 1, 1);
        to = (to != null) ? to : LocalDate.now();
        //thống kê Hoá Đơn
        List<Object[]> listHdct = thongKeService.hoaDonChiTiet(from,to);
        List<Object[]> listHd = thongKeService.soLuongLoaiHoaDon(from,to);
        Map<String, Integer> dataForChart = new HashMap<>();
        Map<String, Integer> dataForChart1 = new HashMap<>();
        List<Object[]> listThongKe = thongKeService.thongKeDoanhThu(from,to);

        Map<LocalDate, BigDecimal> doanhThuMap = new HashMap<>();

        for (Object[] row : listThongKe) {
            BigDecimal totalThanhToan = (BigDecimal) row[0];
            java.sql.Date sqlDate = (java.sql.Date) row[1];
            LocalDate ngayThanhToan = sqlDate.toLocalDate();
            doanhThuMap.put(ngayThanhToan, totalThanhToan);
        }
//        sắp xếp lại thứ tự map theo ngày
        Map<LocalDate, BigDecimal> sortedDoanhThuMap = new LinkedHashMap<>();
        doanhThuMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> sortedDoanhThuMap.put(entry.getKey(), entry.getValue()));

        for (Object[] result : listHd) {
            String trangThai = String.valueOf(result[0]);
            int soLuong = Integer.parseInt(String.valueOf(result[1]));
            dataForChart.put(trangThai, soLuong);
        }
        for (Object[] result : listHdct) {
            String ten = String.valueOf(result[0]);
            int soLuong = Integer.parseInt(String.valueOf(result[1]));
            dataForChart1.put(ten, soLuong);
        }
        Map<String, Integer> sortedMap = dataForChart1.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        model.addAttribute("listHdct",sortedMap);
        model.addAttribute("listThongKe",sortedDoanhThuMap);
        model.addAttribute("doanhThu", thongKeService.doanhThu(from,to));
        model.addAttribute("table1", thongKeService.getAllSoSanPhamHoanTraHoanLaiKho(from,to));
        model.addAttribute("soDonHuy", thongKeService.soDonHuy( from,  to));
        model.addAttribute("hoanTra", thongKeService.soSanPhamHoanTra( from,  to));
        model.addAttribute("hoadons", hoaDonService.findHoaDonsByNgayTao(from, to));
        model.addAttribute("listHd",dataForChart);
        model.addAttribute("endDate", to);
        model.addAttribute("startDate", from);
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        List<NhanVien> nhanVienList = nhanVienService.getAll();
        List<KhachHang> kh = khachHangService.getAll();

        // Adding the list and its count to the model
        model.addAttribute("list", kh);
        model.addAttribute("khachHangCount", kh.size());

        // Adding the list and its count to the model
        model.addAttribute("nhanViens", nhanVienList);
        model.addAttribute("nhanVienCount", nhanVienList.size());
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.getAllChiTietSanPham();

        // Adding the list and its count to the model
        model.addAttribute("listChiTietSanPham", listChiTietSanPham);
        model.addAttribute("sanPhamCount", listChiTietSanPham.size());

        return "admin-template/thong_ke/thong_ke";
    }

    @GetMapping("/chi-tiet-hoa-don/{id}")
    public String detaiOff(Model model,
                           @PathVariable("id") Long idHd,
                           @Param("trangThai") TrangThai trangThai) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null) {
            return "redirect:/login";
        }
        model.addAttribute("hoaDon", hoaDonService.findById(idHd));
        model.addAttribute("hdcts", hoaDonChiTietService.getCtspById(idHd));
        model.addAttribute("trangThai", trangThai);
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        return "admin-template/thong_ke/chi_tiet_hoa_don";
    }

    /**
     * get HoaDon Online
     *
     * @param model
     * @param idHd
     * @param trangThai
     * @return
     */
    @GetMapping("/chi-tiet-gio-hang/{id}")
    public String detailOn(Model model,
                           @PathVariable("id") Long idHd,
                           @Param("trangThai") TrangThai trangThai) {
        Long idNhanVien =spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null) {
            return "redirect:/login";
        }
        model.addAttribute("hoaDon", hoaDonService.findById(idHd));
        model.addAttribute("ghcts", gioHangChiTietService.findGioHangChiTietById(idHd));
        model.addAttribute("trangThai", trangThai);
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        return "admin-template/thong_ke/chi_tiet_hd_online";
    }

//    @GetMapping
//    public String hienThi(Model model) {
//        return "admin-template/thong_ke/thong_ke";
//    }


}

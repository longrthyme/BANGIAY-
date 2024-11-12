package com.example.datnsum24sd01.controller;

import com.example.datnsum24sd01.custom.ChiTietSanPhamCustomerCt;
import com.example.datnsum24sd01.dto.Anhspcustom;
import com.example.datnsum24sd01.dto.ChiTietSanPhamDTO;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.entity.KichThuoc;
import com.example.datnsum24sd01.entity.MauSac;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.responsitory.SanPhamRepository;
import com.example.datnsum24sd01.service.BanHangOnlineCustomService;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.Degiayserviec;
import com.example.datnsum24sd01.service.DongSanPhamService;
import com.example.datnsum24sd01.service.GioHangChiTietService;
import com.example.datnsum24sd01.service.KhichThuocService;
import com.example.datnsum24sd01.service.MauSacService;
import com.example.datnsum24sd01.service.ThuongHieuSerivice;
import com.example.datnsum24sd01.service.impl.ChiTietSanPhamServiceImpl;
import com.example.datnsum24sd01.worker.Spingsecurity;
import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Console;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/beestore")
public class BanHangOnlineController {



    @Autowired
    private GioHangChiTietService gioHangChiTietServicee;
    @Autowired
    private BanHangOnlineCustomService chiTietSanPhamService;

    @Autowired
    private ChiTietSanPhamResponsitory chiTietSanPhamRepository;

    @Autowired
    private SanPhamRepository    sanPhamRepository ;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private Degiayserviec deGiayService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private KhichThuocService kichThuocService;


    @Autowired
    private DongSanPhamService dongSanPhamService;
    @Autowired
    private ThuongHieuSerivice thuongHieuService;

    @Autowired
    private ChiTietSanPhamServiceImpl chiTietSanPhamServiceImpl;

    Integer pageNo = 0;

    private Long selectedSize = null;

    @GetMapping({"/cua-hang", "/"})
    public String getAllShopCustomer(Model model, @RequestParam(name = "tenThuongHieu", defaultValue = "", required = false) List<String> tenThuongHieu,
                                     @RequestParam(name = "tenDongSanPham", defaultValue = "", required = false) List<String> tenDongSanPham,
                                     @RequestParam(name = "tenKichThuoc", defaultValue = "", required = false) List<String> tenKichThuoc,
                                     @RequestParam(name = "tenDeGiay", defaultValue = "", required = false) List<String> tenDeGiay,
                                     @RequestParam(name = "tenMauSac", defaultValue = "", required = false) List<String> tenMauSac,
                                     @RequestParam(name = "minGia", defaultValue = "0", required = false) Double minGia,
                                     @RequestParam(name = "maxGia", defaultValue = "", required = false) Double maxGia,
                                     @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                     @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                     @RequestParam(name = "tenSanPham", defaultValue = "") String tenSanPham,
                                     @RequestParam(name = "sortField", defaultValue = "0", required = false) String sortField) {
        Long id = spingsecurity.getCurrentUserId();
        Boolean checkSecurity=false;
        if (id != null) {
            checkSecurity= true;
        }
        if (maxGia == null) {
            maxGia = 1000000000000.0;
        }
        Long idKhachHang = spingsecurity.getCurrentUserId();
        if(idKhachHang != null) {
            List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.getAll(idKhachHang);

            if (listGioHangChiTiet != null) {
                model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
                int totalQuantity = listGioHangChiTiet.stream()
                        .mapToInt(GioHangChiTiet::getSoLuong)
                        .sum();
                model.addAttribute("totalQuantity", totalQuantity);
            }
        }
        Page<ChiTietSanPhamDTO> chiTietSanPhamPage = chiTietSanPhamService.findAllByCondition(tenThuongHieu, tenDongSanPham, tenKichThuoc, tenDeGiay, tenMauSac, minGia, maxGia, page, pageSize, sortField, tenSanPham);
        int totalPages = chiTietSanPhamPage.getTotalPages();
        long totalCount = chiTietSanPhamPage.getTotalElements();

        if (minGia > maxGia) {
            model.addAttribute("listThuongHieu", tenThuongHieu);
            model.addAttribute("listDongSanPham", tenDongSanPham);
            model.addAttribute("listDeGiay", tenDeGiay);
            model.addAttribute("listMauSac", tenMauSac);
            model.addAttribute("tenSanPham", tenSanPham);
            model.addAttribute("listKichThuoc", tenKichThuoc);
            model.addAttribute("minGia", minGia);
            model.addAttribute("maxGia", maxGia);
            model.addAttribute("sortField", sortField);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("dongSps", dongSanPhamService.getList());
            model.addAttribute("thuongHieus", thuongHieuService.getList());
            model.addAttribute("deGiays", deGiayService.getAllDeGiay());
            model.addAttribute("mauSacs", mauSacService.getAllMauSac());

            model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
            model.addAttribute("err", "mời bạn nhập đúng khoảng giá!");
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("listCTSP", chiTietSanPhamService.findAllByCondition(null, null, null, null, null, null, null,  page, pageSize, sortField, tenSanPham).stream().toList());
//            List<ChiTietSanPhamCustomerCt> list3custom = chiTietSanPhamService.list3Custom();
//            model.addAttribute("list3Custom", list3custom.stream().toList());
            model.addAttribute("page", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalCount", totalCount);
            return "customer-template/product";
        }


//        List<ChiTietSanPhamDTO> chiTietSanPhamList = chiTietSanPhamPage.getContent();
//
//        Set<Long> seenIds = new HashSet<>();
//
//        List<ChiTietSanPhamDTO> filteredList = chiTietSanPhamList.stream()
//                .filter(dto -> seenIds.add(dto.getIdSanPham()))  // Only keep items whose idSanPham hasn't been seen yet
//                .collect(Collectors.toList());


        model.addAttribute("tenSanPham", tenSanPham);
        model.addAttribute("listThuongHieu", tenThuongHieu);
        model.addAttribute("listDongSanPham", tenDongSanPham);

        model.addAttribute("listDeGiay", tenDeGiay);
        model.addAttribute("listMauSac", tenMauSac);
        model.addAttribute("listKichThuoc", tenKichThuoc);
        model.addAttribute("minGia", minGia);
        model.addAttribute("maxGia", maxGia);
        model.addAttribute("sortField", sortField);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", totalCount);

        model.addAttribute("index", pageNo + 1);
        model.addAttribute("listCTSP", chiTietSanPhamPage.getContent());
//        model.addAttribute("listCTSP", filteredList);

//        truyền vào filter
        model.addAttribute("dongSps", dongSanPhamService.getList());
        model.addAttribute("thuongHieus", thuongHieuService.getList());
        model.addAttribute("deGiays", deGiayService.getAllDeGiay());
        model.addAttribute("mauSacs", mauSacService.getAllMauSac());

        model.addAttribute("kichThuocs", kichThuocService.getAllKichThuoc());
        return "customer-template/product";
    }


    private Spingsecurity spingsecurity = new Spingsecurity();

    @GetMapping("/trang-chu")
    public String gettrangchu(Model model) {
        Long idKhachHang = spingsecurity.getCurrentUserId();
        if (idKhachHang != null ) {

            List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.getAll(idKhachHang);
            if (listGioHangChiTiet != null) {

                model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
                int totalQuantity = listGioHangChiTiet.stream()
                        .mapToInt(GioHangChiTiet::getSoLuong)
                        .sum();
                model.addAttribute("totalQuantity", totalQuantity);
                model.addAttribute("loginStatus", true);
            }
        }
        return "customer-template/trangchu";
    }

    @GetMapping("/detaisp/{id}")
    public String detailCustomerSanPham(@PathVariable("id") Long id, Model model,
                                        @RequestParam(value = "sizeId", required = false) Long sizeId,
                                        @RequestParam(value = "colorId", required = false) Long colorId){

        Long idKhachHang = spingsecurity.getCurrentUserId();
        Boolean checkSecurity=false;
        if(idKhachHang != null) {
            List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.getAll(idKhachHang);

            if (listGioHangChiTiet != null) {
                model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
                int totalQuantity = listGioHangChiTiet.stream()
                        .mapToInt(GioHangChiTiet::getSoLuong)
                        .sum();
                model.addAttribute("totalQuantity", totalQuantity);

            }
        }

        Long choosenId = chiTietSanPhamRepository.findBySanPham_Id(id).get(0).getId();

        // hiển thị toàn bộ danh sách kích cỡ và màu sắc, bôi đậm size + màu sắc được chọn
        List<KichThuoc> kichThuocs =  kichThuocService.findByProductId(id)
                .stream()
                .collect(
                        Collectors.toMap(
                                KichThuoc::getId,  //sử dụng id kích thước làm khóa
                                kichThuoc -> kichThuoc,// Sử dụng đối tượng `KichThuoc` làm giá trị
                                (existing, replacement) -> existing
                        )
                )
                .values().stream().toList();
        model.addAttribute("kichThuocs", kichThuocs);


        // lấy màu sắc theo kích thước + productId
        if(sizeId != null) {
            model.addAttribute("selectedSize", sizeId);
            List<MauSac> mauSacs =  mauSacService.findByProductAndSize(id, (sizeId));
            model.addAttribute("mauSacs", mauSacs);
        }

        if(colorId != null) {
            model.addAttribute("selectedColor", colorId);
        }

        ChiTietSanPham chiTietSanPham;

        if(colorId != null && sizeId != null) {

//            chiTietSanPham = chiTietSanPhamServiceImpl.findByColorAndSize(colorId, sizeId);
            chiTietSanPham = chiTietSanPhamServiceImpl.findByColorAndSize(colorId, sizeId, id);
        } else {

//            sanPhamRepository.findById(id);
//            chiTietSanPham = chiTietSanPhamServiceImpl.getById(id);
            chiTietSanPham = chiTietSanPhamServiceImpl.getById(choosenId);
        }

        List<Anhspcustom> listAnhdetail = null;
        if (idKhachHang == null) {
            model.addAttribute("checkSecurity",checkSecurity);
            List<ChiTietSanPhamCustomerCt> listRand = chiTietSanPhamService.list4Random();
            model.addAttribute("listRandom", listRand.stream().toList());

            model.addAttribute("spDetail", chiTietSanPham);

            if(chiTietSanPham != null) {

                listAnhdetail = chiTietSanPhamService.listAnhDetail(chiTietSanPham.getId());
            } else {

                listAnhdetail = chiTietSanPhamService.listAnhDetail(id);
            }

            model.addAttribute("listAnhDetail", listAnhdetail.stream().toList());
            model.addAttribute("soLuongTon", chiTietSanPham.getSoLuongTon());
        }else {

            checkSecurity= true;
            model.addAttribute("checkSecurity",checkSecurity);
            List<ChiTietSanPhamCustomerCt> listRand = chiTietSanPhamService.list4Random();
            model.addAttribute("listRandom", listRand.stream().toList());
            if(chiTietSanPham != null) {

                listAnhdetail = chiTietSanPhamService.listAnhDetail(chiTietSanPham.getId());
            } else {

                listAnhdetail = chiTietSanPhamService.listAnhDetail(id);
            }
//            List<Anhspcustom> listAnhdetail = chiTietSanPhamService.listAnhDetail(id);
            model.addAttribute("listAnhDetail", listAnhdetail.stream().toList());
            model.addAttribute("spDetail", chiTietSanPham);
            model.addAttribute("soLuongTon", chiTietSanPham.getSoLuongTon());
            List<GioHangChiTiet> cartItems = gioHangChiTietService.getAll(idKhachHang);

            // Tìm mục trong giỏ hàng dựa trên ID sản phẩm
           if (cartItems != null) {

               GioHangChiTiet gioHangChiTiet = cartItems.stream()
                       .filter(item -> item.getChiTietSanPham().getId().equals(id))
                       .findFirst()
                       .orElse(null);

               int soLuongTrongGioHang = (gioHangChiTiet != null) ? gioHangChiTiet.getSoLuong() : 0;
               model.addAttribute("soLuongTrongGioHang", soLuongTrongGioHang);

           }

        }
        return "customer-template/detailproduct";
    }

    @GetMapping("/checkout")
    public String getthanhtoan(Model model) {

        return "customer-template/checkout";
    }
    @GetMapping("/dichvu")
    public String dichvu(Model model) {

        return "customer-template/dichvu";
    }
    @GetMapping("/thongtin")
    public String thongtin(Model model) {

        return "customer-template/thongtin";
    }
    @GetMapping("/lienhe")
    public String lienhe(Model model) {

        return "customer-template/lienhe";
    }

}

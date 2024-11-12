package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.dto.GioHangWrapper;
import com.example.datnsum24sd01.dto.OrderDataDTO;
import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.GioHang;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.LoaiHoaDon;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.responsitory.GioHangChiTietRepository;
import com.example.datnsum24sd01.responsitory.GioHangRepository;
import com.example.datnsum24sd01.responsitory.HoaDonRepository;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.responsitory.PhieuGiamGiaResponsitory;
import com.example.datnsum24sd01.service.BanHangonlinectservice;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BanHangonlinectserviceImpl implements BanHangonlinectservice {
    @Autowired
    private ChiTietSanPhamResponsitory chiTietSanPhamRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private KhachHangResponsitory khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private PhieuGiamGiaResponsitory maGiamGiaRepository;

    private OrderDataDTO orderData;

    private GioHangWrapper gioHangWrapper;

//thêm sp vào giỏ hàng
    @Override
    public GioHangChiTiet themVaoGioHang(Long khachHangId, Long chiTietSanPhamId, Integer soLuong) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(chiTietSanPhamId).orElse(null);
        GioHang gioHang = gioHangRepository.getByKhachHangId(khachHangId);

        if (gioHang == null) {
            gioHang = new GioHang();
            gioHang.setKhachHang(khachHangRepository.findById(khachHangId).orElse(null));
            gioHangRepository.save(gioHang);
        }

        GioHangChiTiet gioHangChiTiet1 = gioHangChiTietRepository.findByGioHangAndChiTietSanPhamAndHoaDonIsNull(gioHang, chiTietSanPham);
        if (gioHangChiTiet1 != null) {
            gioHangChiTiet1.setSoLuong(gioHangChiTiet1.getSoLuong() + soLuong);
            return gioHangChiTietRepository.save(gioHangChiTiet1);

        }
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setSoLuong(soLuong);
        gioHangChiTiet.setChiTietSanPham(chiTietSanPham);
        gioHangChiTiet.setDonGia(gioHangChiTiet.getChiTietSanPham().getGiaBan());
        gioHangChiTiet.setNgayTao(LocalDate.now());
        gioHangChiTiet.setTrangThai(TrangThai.DANG_HOAT_DONG);
        gioHangChiTiet.setGioHang(gioHang);
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }
//xóa sản phẩm khỏi giỏ hàng
    @Override
    public void xoaKhoiGioHang(Long id) {
        gioHangChiTietRepository.deleteById(id);
    }
    //thanh toán trong giỏ hàng
    @Transactional
    @Override
    public void datHang(List<GioHangChiTiet> listGioHangChiTiet, String ten, String diaChi, String sdt, String ghiChu) {
        KhachHang khachHang = khachHangRepository.findById(Long.valueOf(5)).orElse(null);
        NhanVien nhanVien = nhanVienRepository.findById(Long.valueOf(14)).orElse(null);

        LocalDateTime time = LocalDateTime.now();
        String maHD = "Hóa Đơn" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
        BigDecimal tongTien = BigDecimal.ZERO;
        BigDecimal thanhToan = BigDecimal.ZERO;
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHD);
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setKhachHang(khachHang);
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setSdt(sdt);
        hoaDon.setDiaChi(diaChi);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setTenKhachHang(ten);
        hoaDon.setTrangThai(TrangThaiDonHang.CHO_XAC_NHAN);
        hoaDon.setLoaiHoaDon(LoaiHoaDon.HOA_DON_ONLINE);
        hoaDonRepository.save(hoaDon);

        for (GioHangChiTiet gh : listGioHangChiTiet) {
            gh.setHoaDon(hoaDon);
            gioHangChiTietRepository.save(gh);

            ChiTietSanPham chiTietSanPham = gh.getChiTietSanPham();
            chiTietSanPhamRepository.save(chiTietSanPham);
            BigDecimal itemCost = chiTietSanPham.getGiaBan().multiply(BigDecimal.valueOf(gh.getSoLuong()));
            tongTien = tongTien.add(itemCost);
            BigDecimal itemThanhToan = chiTietSanPham.getGiaBan().multiply(BigDecimal.valueOf(gh.getSoLuong()));
            thanhToan = thanhToan.add(itemThanhToan);
        }

        hoaDon.setTongTien(tongTien);
        hoaDon.setThanhToan(thanhToan);
        hoaDonRepository.save(hoaDon);
    }
//đặt hàng trong checkout
    @Override
    public HoaDon datHangItems(GioHangWrapper gioHangWrapper, Long idKachHang, String ten, String diaChi, String sdt, String ghiChu, BigDecimal shippingFee, BigDecimal tongTien, BigDecimal totalAmount, BigDecimal tienGiamGia, Long selectedVoucherId, BigDecimal diemTichLuy, String useAll) {
        KhachHang khachHang = khachHangRepository.findById(idKachHang).orElse(null);
        LocalDateTime time = LocalDateTime.now();
        String maHD = "Hóa Đơn" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHD);
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setKhachHang(khachHang);
        hoaDon.setSdt(sdt);
        hoaDon.setDiaChi(diaChi);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setTenKhachHang(ten);
        hoaDon.setTrangThai(TrangThaiDonHang.CHO_XAC_NHAN);
        hoaDon.setLoaiHoaDon(LoaiHoaDon.HOA_DON_ONLINE);
        hoaDonRepository.save(hoaDon);
        totalAmount = BigDecimal.valueOf(shippingFee.intValue() + totalAmount.intValue());

        for (GioHangChiTiet gh : gioHangWrapper.getListGioHangChiTiet()) {
            gh.setHoaDon(hoaDon);
            gioHangChiTietRepository.save(gh);
            ChiTietSanPham chiTietSanPham = gh.getChiTietSanPham();
            chiTietSanPhamRepository.save(chiTietSanPham);
        }
        hoaDon.setPhiVanChuyen(shippingFee);
        if (selectedVoucherId == null || selectedVoucherId == 0) {
            hoaDon.setMaGiamGia(null);
        } else {
           PhieuGiamGia maGiamGia = maGiamGiaRepository.findById(selectedVoucherId).get();
            hoaDon.setMaGiamGia(maGiamGia);
            hoaDon.setTienGiamGia(tienGiamGia);
            totalAmount = BigDecimal.valueOf(totalAmount.intValue() - tienGiamGia.intValue());
            maGiamGia.setSoLuong(maGiamGia.getSoLuong() - 1);
            maGiamGiaRepository.save(maGiamGia);
        }
        if (useAll.equals("false")) {
            hoaDon.setXu(BigDecimal.valueOf(0));
        } else {

            if (diemTichLuy.compareTo(BigDecimal.valueOf(50000)) > 0) {
                hoaDon.setXu(BigDecimal.valueOf(50000));
                totalAmount = BigDecimal.valueOf(totalAmount.intValue() - BigDecimal.valueOf(50000).intValue());
                khachHang.setTichDiem(khachHang.getTichDiem().subtract(BigDecimal.valueOf(50000)));
                khachHangRepository.save(khachHang);
            } else {
                hoaDon.setXu(khachHang.getTichDiem());
                totalAmount = BigDecimal.valueOf(totalAmount.intValue() - khachHang.getTichDiem().intValue());
                khachHang.setTichDiem(BigDecimal.valueOf(0));
                khachHangRepository.save(khachHang);
            }
        }
        hoaDon.setTongTien(tongTien);
        hoaDon.setThanhToan(totalAmount);

        return hoaDonRepository.save(hoaDon);
    }
//sửa giỏ hàng
    @Override
    public List<GioHangChiTiet> updateGioHangChiTiet(Long idGioHangChiTiet, Integer soLuong) {
        Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(idGioHangChiTiet);
        List<GioHangChiTiet> updatedItems = new ArrayList<>();

        if (optionalGioHangChiTiet.isPresent()) {
            GioHangChiTiet gioHangChiTiet1 = optionalGioHangChiTiet.get();
            gioHangChiTiet1.setSoLuong(soLuong);
            gioHangChiTietRepository.save(gioHangChiTiet1);
            updatedItems.add(gioHangChiTiet1);
        } else {
            throw new EntityNotFoundException("Không tìm thấy GioHangChiTiet với ID: " + idGioHangChiTiet);
        }

        return updatedItems;
    }
//Tìm tất cả các đối tượng GioHangChiTiet dựa trên một danh sách các ID chuỗi.
    @Override
    public List<GioHangChiTiet> findAllById(List<String> listIdString) {
        List<Long> listIdLong = new ArrayList<>();
        for (String str : listIdString) {
            try {
                Long value = Long.parseLong(str);
                listIdLong.add(value);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
                // Xử lý ngoại lệ nếu có giá trị không hợp lệ
            }
        }
        return gioHangChiTietRepository.findAllById(listIdLong);
    }
// Tìm tất cả các đối tượng GioHangChiTiet dựa trên một danh sách các ID chuỗi và đóng gói chúng vào một đối tượng GioHangWrapper.
    @Override
    public GioHangWrapper findAllItemsById(List<String> listIdString) {
        List<Long> listIdLong = new ArrayList<>();
        for (String str : listIdString) {
            try {
                Long value = Long.parseLong(str);
                listIdLong.add(value);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
        }
        GioHangWrapper gioHangWrapper = new GioHangWrapper();
        gioHangWrapper.setListGioHangChiTiet(gioHangChiTietRepository.findAllById(listIdLong));
        return gioHangWrapper;
    }
    //Lưu trữ thông tin đơn hàng tạm thời.
    @Override
    public void saveOrderData(OrderDataDTO orderDataDTO) {

        // save temp order data

        this.orderData = orderDataDTO;

    }
//truy suất thông tin đơn hàng tạm thời
    @Override
    public OrderDataDTO getOrderData() {

        return this.orderData;

    }
   // Lưu trữ thông tin giỏ hàng tạm thời.
    @Override
    public void saveGioHangWrapper(GioHangWrapper gioHangWrapper) {
        this.gioHangWrapper = gioHangWrapper;
    }
//Truy xuất thông tin giỏ hàng đã lưu trữ.
    @Override
    public GioHangWrapper getGioHangWrapper() {
        return this.gioHangWrapper;
    }
}

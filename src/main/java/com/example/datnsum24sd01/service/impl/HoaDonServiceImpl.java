package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.enumation.LoaiHoaDon;
import com.example.datnsum24sd01.enumation.TrangThai;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.responsitory.GioHangChiTietRepository;
import com.example.datnsum24sd01.responsitory.HoaDonRepo;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.BanHangService;
import com.example.datnsum24sd01.service.ChiTietSanPhamService;
import com.example.datnsum24sd01.service.HoaDonService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepo repository;
    private final GioHangChiTietRepository gioHangChiTietRepository;

    private final ChiTietSanPhamResponsitory chiTietSanPhamRepository;
    private final EmailService sendMailService;


    private final BanHangService banHangService;

    public HoaDonServiceImpl(HoaDonRepo repository,GioHangChiTietRepository gioHangChiTietRepository,  ChiTietSanPhamResponsitory chiTietSanPhamRepository, EmailService sendMailService,  BanHangService banHangService) {
        this.repository = repository;
        this.gioHangChiTietRepository = gioHangChiTietRepository;
        this.chiTietSanPhamRepository = chiTietSanPhamRepository;
        this.sendMailService = sendMailService;
        this.banHangService = banHangService;
    }
// view hóa đơn theo
    public List<HoaDon> getAll() {
        List<HoaDon> sortedList = repository.findAll().stream()
                .sorted(Comparator.comparing(HoaDon::getId).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<HoaDon> getAllByKhachHang(Long id) {
        return repository.getAllByKhachHang(id).stream()
                .sorted(Comparator.comparing(HoaDon::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<HoaDon> getByTrangThai(TrangThaiDonHang trangThai) {
        return repository.getAllByTrangThai(trangThai).stream()
                .sorted(Comparator.comparing(HoaDon::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<HoaDon> getByTrangThaiAndKhachHang(TrangThaiDonHang trangThai, Long id) {
        List<HoaDon> sortedList = repository.findHoaDonByTrangThaiAndKhachHangId(trangThai, id).stream()
                .sorted(Comparator.comparing(HoaDon::getId).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public HoaDon findById(Long id) {
        Optional<HoaDon> hoaDon = repository.findById(id);
        if (hoaDon.isPresent()) {
            return hoaDon.get();
        }
        return null;
    }

    @Override
    public HoaDon save(HoaDon hoaDon) {
        return repository.save(hoaDon);
    }

    @Override
    public List<HoaDon> findHoaDonsByNgayTao(LocalDate start, LocalDate end) {
        return repository.findHoaDonsByNgayTao(start, end).stream()
                .sorted(Comparator.comparing(HoaDon::getId).reversed())
                .collect(Collectors.toList());
    }
//thay đổi trạng thái hóa đơn
    @Override
    public boolean validate(HoaDon hoaDon, TrangThaiDonHang trangThai, String newGhiChu) {
        hoaDon.setTrangThai(trangThai);
        hoaDon.setGhiChu(newGhiChu);
        if (hoaDon.getTrangThai() == TrangThaiDonHang.DANG_GIAO || hoaDon.getTrangThai() == TrangThaiDonHang.XAC_NHAN_TRA_HANG || hoaDon.getTrangThai() == TrangThaiDonHang.DOI_HANG) {
            hoaDon.setNgayThanhToan(LocalDate.now());
        }
        //gửi mail trạng thái đã giao ,đang chuẩn bị
        if(hoaDon.getTrangThai() == TrangThaiDonHang.DA_GIAO|| hoaDon.getTrangThai() == TrangThaiDonHang.DANG_CHUAN_BI|| hoaDon.getTrangThai() == TrangThaiDonHang.DA_HUY){
            sendMailService.sendEmail1(hoaDon.getKhachHang(),hoaDon);

        }
        //hoàn thành thì tích điểm
        if(hoaDon.getTrangThai()==TrangThaiDonHang.HOAN_THANH){
            banHangService.tichDiem(hoaDon.getKhachHang().getId(),hoaDon.getThanhToan().toString());
        }
        hoaDon = repository.save(hoaDon);
        return hoaDon.getTrangThai().equals(trangThai);
    }
    //hòa trả hóa đơn
    @Override
    public HoaDon createHdHoanTra(HoaDon hoaDon, Long idHd) {
        HoaDon hoaDonNew = new HoaDon();
        LocalDateTime time = LocalDateTime.now();
        String maHD = "Hóa Đơn" + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();

        hoaDonNew.setMa(maHD);
        hoaDonNew.setLoaiHoaDon(LoaiHoaDon.HOA_DON_ONLINE);
        hoaDonNew.setTrangThai(TrangThaiDonHang.DA_TRA_HANG);
        hoaDonNew.setKhachHang(repository.findById(idHd).get().getKhachHang());
        hoaDonNew.setGhiChu("Đơn hàng hoàn trả của đơn hàng " + hoaDon.getMa());
        hoaDonNew.setNgayTao(LocalDate.now());
        hoaDonNew.setNgaySua(LocalDate.now());
        hoaDonNew.setSdt(hoaDon.getSdt());
        hoaDonNew.setTenKhachHang(hoaDon.getTenKhachHang());
        hoaDonNew.setNhanVien(hoaDon.getNhanVien());
        repository.save(hoaDonNew);
        return hoaDonNew;
    }

    @Override
    public boolean removeGioHangChiTietHoanTra(GioHangChiTiet gioHangChiTiet, HoaDon hoaDon) {
        if (gioHangChiTiet.getTrangThai().equals(TrangThai.YEU_CAU_TRA_HANG)) {
            gioHangChiTietRepository.delete(gioHangChiTiet);
//            hoaDon.setThanhToan(hoaDon.getThanhToan()- );
        }
        return true;
    }


//trả hàng
    @Override
    public ChiTietSanPham refund(GioHangChiTiet gioHangChiTiet) {
        if (gioHangChiTiet.getTrangThai().equals(TrangThai.YEU_CAU_TRA_HANG)) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(gioHangChiTiet.getChiTietSanPham().getId()).orElse(null);
            if (chiTietSanPham != null) {
                chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + gioHangChiTiet.getSoLuong());
            }
        }
        return null;
    }
    //nếu quá 3 ngày từ trạng thái đã giao mà không xác nhận thì tự động chuyển sang trạng thái hoàn thành
    @Scheduled(fixedRate = 300000)
    public void updateHoaDonEveryDay(){
        List<HoaDon> hoaDons= repository.findAll();
        for(HoaDon hoaDon:hoaDons){
            if(hoaDon.getTrangThai()==TrangThaiDonHang.DA_GIAO && hoaDon.getNgayThanhToan()!=null){
                LocalDate ngayCheck = hoaDon.getNgayThanhToan().plusDays(3);
                if (ngayCheck.isEqual(LocalDate.now())) {
                    hoaDon.setTrangThai(TrangThaiDonHang.HOAN_THANH);
                    repository.save(hoaDon);
                }
            }
        }
    }


    @Override
    public BigDecimal maGiamGia(Long idHd){
        HoaDon hoaDon= repository.findById(idHd).get();
        BigDecimal tongTien = hoaDon.getTongTien();
        BigDecimal phiVanChuyen = hoaDon.getPhiVanChuyen();
        BigDecimal giamGia= BigDecimal.ZERO;
        if(hoaDon.getMaGiamGia()!=null){
            int mucGiamGia = hoaDon.getMaGiamGia().getMucGiamGia();

            BigDecimal tongTienVaPhi = tongTien.add(phiVanChuyen);

            BigDecimal mucGiamToiDa = hoaDon.getMaGiamGia().getMucGiamToiDa();
            BigDecimal chietKhau = tongTienVaPhi.multiply(BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(mucGiamGia)))
                    .divide(BigDecimal.valueOf(100));
            int comparison = chietKhau.compareTo(mucGiamToiDa);
            if (comparison>0) {
                giamGia = mucGiamToiDa;
            } else {
                giamGia= chietKhau;
            }
        }
        return giamGia;
    }
    @Override
    public HoaDon findByMa(String maHd) {
        return repository.findHoaDonByMa(maHd);
    }

}

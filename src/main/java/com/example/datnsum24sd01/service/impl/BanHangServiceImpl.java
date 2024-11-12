
package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.ChiTietSanPham;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.entity.HoaDonChiTiet;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.entity.PhieuGiamGia;
import com.example.datnsum24sd01.enumation.LoaiHoaDon;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.responsitory.ChiTietSanPhamResponsitory;
import com.example.datnsum24sd01.responsitory.HoaDonChiTietRepository;
import com.example.datnsum24sd01.responsitory.HoaDonRepository;
import com.example.datnsum24sd01.responsitory.KhachHangResponsitory;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.responsitory.PhieuGiamGiaResponsitory;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BanHangServiceImpl implements BanHangService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private ChiTietSanPhamResponsitory chiTietSanPhamRepository;

    @Autowired
    private KhachHangResponsitory khachHangRepository;

    @Autowired
    private PhieuGiamGiaResponsitory maGiamGiaRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;


//view hóa đơn chờ
    @Override
    public List<HoaDon> getHoaDonCho() {
        List<HoaDon> listHoaDonCho = new ArrayList<>();
        for (HoaDon hoaDon : hoaDonRepository.findAll()) {
            if (hoaDon.getTrangThai() == TrangThaiDonHang.HOA_DON_CHO) {
                listHoaDonCho.add(hoaDon);
            }
        }
        return listHoaDonCho;
    }
// Lấy danh sách chi tiết hóa đơn dựa trên ID hóa đơn và chỉ trả về các chi tiết có trạng thái là CHO_XAC_NHAN.
    @Override
    public List<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(Long idHoaDon) {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.getHoaDonChiTietByIdHoaDon(idHoaDon)) {
            if (hoaDonChiTiet.getTrangThaiDonHang() == TrangThaiDonHang.CHO_XAC_NHAN) {
                listHDCT.add(hoaDonChiTiet);
            }
        }
        return listHDCT;
    }
//Lấy hóa đơn dựa trên ID hóa đơn.
    @Override
    public HoaDon getOneById(Long idHoaDon) {
        return hoaDonRepository.findById(idHoaDon).get();
    }

    @Override
    public ChiTietSanPham getChiTietSanPhamById(Long idChiTietSanPham) {
        return chiTietSanPhamRepository.findById(idChiTietSanPham).get();
    }


    //Lấy danh sách tất cả chi tiết sản phẩm có trạng thái null.(trạng thái hóa đơn chờ)
    @Override
    public List<ChiTietSanPham> getChiTietSanPham() {
        List<ChiTietSanPham> listChiTietSanPham = new ArrayList<>();
        for (ChiTietSanPham chiTietSanPham : chiTietSanPhamRepository.findAll()) {
            if (chiTietSanPham.getTrangThai() ==null ) {
                listChiTietSanPham.add(chiTietSanPham);
            }
        }
        return listChiTietSanPham;
    }
//thêm hóa đơn chờ tối đa 5 hóa đơn chờ
    @Override
    public HoaDon themHoaDon(HoaDon hoaDon, Long idNhanVien) {
        LocalDateTime time = LocalDateTime.now();
        String maHD = "Hóa Đơn " + String.valueOf(time.getYear()).substring(2) + time.getMonthValue()
                + time.getDayOfMonth() + time.getHour() + time.getMinute() + time.getSecond();
        if (hoaDonRepository.checkHoaDonCho() < 5) {
            for (KhachHang khachHang : khachHangRepository.findAll()) {
                if (khachHang.getMa().equals("KH000")) {
                    NhanVien nhanVien = nhanVienRepository.findById(idNhanVien).get();
                    hoaDon = HoaDon.builder()
                            .ma(maHD)
                            .nhanVien(nhanVien)
                            .khachHang(khachHang)
                            .ngayTao(LocalDate.now())
                            .loaiHoaDon(LoaiHoaDon.HOA_DON_OFFLINE)
                            .trangThai(TrangThaiDonHang.HOA_DON_CHO)
                            .build();
                    return hoaDonRepository.save(hoaDon);
                }
            }
        }
        return null;
    }

    //thêm sản phẩm vào giỏ hàng để thêm vào hóa đơn chờ
    @Override
    public HoaDonChiTiet taoHoaDonChiTiet(Long idSanPham, Long idHoaDon, HoaDonChiTiet hoaDonChiTiet) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idSanPham).get();
        hoaDonChiTiet = HoaDonChiTiet.builder()
                .hoaDon(hoaDon)
                .chiTietSanPham(chiTietSanPham)
                .deGiay(chiTietSanPham.getDeGiay().getTen())
                .kichThuoc(chiTietSanPham.getKichThuoc().getTen())
                .mauSac(chiTietSanPham.getMauSac().getTen())
                .tenSanPham(chiTietSanPham.getSanPham().getTen())
                .ngayTao(LocalDate.now())
                .giaBan(chiTietSanPham.getGiaBan())
                .soLuong(hoaDonChiTiet.getSoLuong())
                .trangThaiDonHang(TrangThaiDonHang.CHO_XAC_NHAN)
                .build();
        for (HoaDonChiTiet hdct : hoaDonChiTietRepository.getHoaDonChiTietByIdHoaDon(idHoaDon)) {
            if (hdct.getChiTietSanPham().getId() == idSanPham) {
                hdct.setSoLuong(hdct.getSoLuong() + hoaDonChiTiet.getSoLuong());
                return hoaDonChiTietRepository.save(hdct);
            }
        }
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet getOneByIdHDCT(Long idHDCT) {
        return hoaDonChiTietRepository.findById(idHDCT).get();
    }
//xóa sản phẩm trong giỏ hàng
    @Override
    public HoaDonChiTiet xoaHoaDonChiTiet(Long idHoaDonChiTiet) {
        hoaDonChiTietRepository.deleteById(idHoaDonChiTiet);
        return null;
    }

//thanh toán hóa đơn chờ
    @Override
    public HoaDon thanhToanHoaDon(Long idHoaDon, BigDecimal tienGiamGia) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        hoaDon.setNgayThanhToan(LocalDate.now());
        hoaDon.setSdt(hoaDon.getKhachHang().getSdt());
        hoaDon.setPhiVanChuyen(BigDecimal.ZERO);
        hoaDon.setTienGiamGia(tienGiamGia);
        hoaDon.setTenKhachHang(hoaDon.getKhachHang().getTen());
        hoaDon.setTrangThai(TrangThaiDonHang.HOAN_THANH);
        return hoaDonRepository.save(hoaDon);
    }
    //check điểm thành viên giảm tối đa 50k điểm

    @Override
    public HoaDon checkXuHoaDon(Long idHoaDon, String tongTien, String thanhTien, Boolean xuTichDiem) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        KhachHang khachHang = khachHangRepository.findById(hoaDon.getKhachHang().getId()).get();
        if (xuTichDiem == true) {
            if (hoaDon.getKhachHang().getTichDiem() == null) {
                hoaDon.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTien)));
                hoaDon.setThanhToan(BigDecimal.valueOf(Double.valueOf(thanhTien)));
                return hoaDonRepository.save(hoaDon);
            } else if (hoaDon.getKhachHang().getTichDiem().compareTo(new BigDecimal("50000")) < 0) {
                hoaDon.setXu(khachHang.getTichDiem());
                hoaDon.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTien)));
                hoaDon.setThanhToan(BigDecimal.valueOf(Double.valueOf(thanhTien)).subtract(hoaDon.getKhachHang().getTichDiem()));
                khachHang.setTichDiem(new BigDecimal(0));
                khachHangRepository.save(khachHang);
                return hoaDonRepository.save(hoaDon);
            }
            hoaDon.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTien)));
            hoaDon.setXu(new BigDecimal("50000"));
            hoaDon.setThanhToan(BigDecimal.valueOf(Double.valueOf(thanhTien)).subtract(new BigDecimal("50000")));
            khachHang.setTichDiem(khachHang.getTichDiem().subtract(new BigDecimal("50000")));
            khachHangRepository.save(khachHang);
            return hoaDonRepository.save(hoaDon);
        }
        hoaDon.setTongTien(BigDecimal.valueOf(Double.valueOf(tongTien)));
        hoaDon.setThanhToan(BigDecimal.valueOf(Double.valueOf(thanhTien)));
        return hoaDonRepository.save(hoaDon);
    }


    //thêm mã giảm giá cho thành viên có tài khoản  chưa done

    @Override
    public BigDecimal voucher(Long idHoaDon, BigDecimal tongTien) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        if (hoaDon.getMaGiamGia() == null) {
            return new BigDecimal(0);
        } else {
            if (hoaDon.getMaGiamGia().getSoLuong() <= 0) {
                return new BigDecimal(0);
            } else {
                BigDecimal phanTramApDung = BigDecimal.valueOf(hoaDon.getMaGiamGia().getMucGiamGia()).divide(BigDecimal.valueOf(Double.valueOf(100)));
                BigDecimal tienApDung = tongTien.multiply(phanTramApDung);
                if (hoaDon.getMaGiamGia().getMucGiamToiDa().compareTo(tienApDung) > 0) {
                    return tienApDung;
                }
                return hoaDon.getMaGiamGia().getMucGiamToiDa();
            }
        }

    }
//chưa done
    @Override
    public Integer checkVoucher(Long idHoaDon, Long idMaGiamGia, BigDecimal tongTien) {
        return null;
    }

//tong tien san pham
    @Override
    public BigDecimal getTongTien(Long idHoaDon) {
        BigDecimal tongTien = BigDecimal.valueOf(0);
        Boolean check = false;
        int index = 0;
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietRepository.getHoaDonChiTietByIdHoaDon(idHoaDon);
        while (index < listHDCT.size() && !check) {
            HoaDonChiTiet hoaDonChiTiet = listHDCT.get(index);
            tongTien = tongTien.add(hoaDonChiTiet.getGiaBan().multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong())));
            index++;

        }
        return tongTien;
    }
//thành tiền khi áp điểm ,gg
    @Override
    public BigDecimal getThanhTien(Long idHoaDon, BigDecimal tongTien) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        if (hoaDon.getMaGiamGia() == null) {
            return tongTien;
        }
        BigDecimal phanTramApDung = BigDecimal.valueOf(hoaDon.getMaGiamGia().getMucGiamGia()).divide(BigDecimal.valueOf(Double.valueOf(100)));
        BigDecimal tienApDung = tongTien.multiply(phanTramApDung);
        if (tienApDung.compareTo(hoaDon.getMaGiamGia().getMucGiamToiDa()) < 0) {
            return tongTien.subtract(tienApDung);
        }
        return tongTien.subtract(hoaDon.getMaGiamGia().getMucGiamToiDa());


    }
//update sl sp khi thêm vào giỏ
    @Override
    public ChiTietSanPham updateSoLuong(Long idSanPham, Integer soLuong) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.getChiTietSanPhamById(idSanPham).orElse(null);
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() - soLuong);
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }
//
    @Override
    public ChiTietSanPham updateSoLuongTuHDCT(Long idHDCT) {
        Long idSanPham;
        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.findAll()) {
            if (hoaDonChiTiet.getId() == idHDCT) {
                idSanPham = hoaDonChiTiet.getChiTietSanPham().getId();
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.getChiTietSanPhamById(idSanPham).orElse(null);
                chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + hoaDonChiTiet.getSoLuong());
                return chiTietSanPhamRepository.save(chiTietSanPham);
            }
        }
        return null;
    }
//thêm khách hàng vào hóa đơn chờ
    @Override
    public HoaDon updateKhachHang(Long idHoaDon, Long idKhachHang) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        KhachHang khachHang = khachHangRepository.findById(idKhachHang).get();
        if (hoaDon != null) {
            hoaDon.setKhachHang(khachHang);
            hoaDonRepository.save(hoaDon);
        }
        return null;
    }
//chưa done
    @Override
    public PhieuGiamGia updateGiamGia(Long idHoaDon) {
        return null;
    }


    @Override
    public HoaDonChiTiet tangSoLuongSanPham(Long idHDCT, Integer soLuong) {
        Long idSanPham;
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.getReferenceById(idHDCT);
        hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + soLuong);
        idSanPham = hoaDonChiTiet.getChiTietSanPham().getId();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.getReferenceById(idSanPham);
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() - soLuong);
        chiTietSanPhamRepository.save(chiTietSanPham);
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }


    //tăng số lượng sản phẩm trong giỏ hàng chưa done

    @Override
    public HoaDonChiTiet tangSoLuongSanPhamHoaDon(Long idHDCT, Integer soLuong) {
        Long idSanPham;
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(idHDCT).orElse(null);
        hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + soLuong);
        idSanPham = hoaDonChiTiet.getChiTietSanPham().getId();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idSanPham).orElse(null);
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() - soLuong);
        chiTietSanPhamRepository.save(chiTietSanPham);
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }
    //tăng số lượng sản phẩm trong giỏ hàng chưa done
    @Override
    public HoaDonChiTiet giamSoLuongSanPhamHoaDon(Long idHDCT, Integer soLuong) {
        Long idSanPham;
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(idHDCT).orElse(null);
        hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() - soLuong);
        idSanPham = hoaDonChiTiet.getChiTietSanPham().getId();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idSanPham).orElse(null);
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + soLuong);
        chiTietSanPhamRepository.save(chiTietSanPham);
        if (hoaDonChiTiet.getSoLuong() <= 0) {
            hoaDonChiTietRepository.deleteById(idHDCT);
            return null;
        }
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }
//sửa slsp chưa done
    @Override
    public ChiTietSanPham suaSoLuongSanPham(Long idHDCT) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findById(idHDCT).orElse(null);
        Long idSanPham = hoaDonChiTiet.getChiTietSanPham().getId();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idSanPham).orElse(null);
        chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + hoaDonChiTiet.getSoLuong());
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }
//hủy đơn hàng
    @Override
    public Boolean huyDon(Long idHoaDon) {
        Boolean check = false;
        int index = 0;
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietRepository.getHoaDonChiTietByIdHoaDon(idHoaDon);
        if (!listHDCT.isEmpty()) {
            while (index < listHDCT.size() && !check) {
                HoaDonChiTiet hoaDonChiTiet = listHDCT.get(index);
                Long idSanPham = hoaDonChiTiet.getChiTietSanPham().getId();
                ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idSanPham).get();
                chiTietSanPham.setSoLuongTon(chiTietSanPham.getSoLuongTon() + hoaDonChiTiet.getSoLuong());
                hoaDonChiTietRepository.deleteById(hoaDonChiTiet.getId());
                chiTietSanPhamRepository.save(chiTietSanPham);
                index++;

            }
            hoaDonRepository.deleteById(idHoaDon);
            return true;
        } else {
            hoaDonRepository.deleteById(idHoaDon);
            return true;
        }
    }
//tích điểm khách hàng 
    @Override
    public KhachHang tichDiem(Long idKhachHang, String thanhTien) {
        KhachHang khachHang = khachHangRepository.findById(idKhachHang).orElse(null);
        if (khachHang != null && "KH000".equals(khachHang.getMa())) {
            return null;
        } else if (khachHang.getTichDiem() != null) {
            khachHang.setTichDiem(khachHang.getTichDiem().add(BigDecimal.valueOf(Double.valueOf(thanhTien)).multiply(new BigDecimal("0.01"))));
            return khachHangRepository.save(khachHang);
        } else if (khachHang.getTichDiem() == null) {
            khachHang.setTichDiem(BigDecimal.valueOf(Double.parseDouble(thanhTien)).multiply(new BigDecimal("0.01")));
            return khachHangRepository.save(khachHang);
        }
        return null;
    }



}

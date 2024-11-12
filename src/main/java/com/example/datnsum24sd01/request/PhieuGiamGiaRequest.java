package com.example.datnsum24sd01.request;
import com.example.datnsum24sd01.enumation.TrangThaiPhieuKhuyenMai;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PhieuGiamGiaRequest {
    private Long id;
    @NotBlank(message = "Mã Phiếu Không Được Để Trống !")
    private String ma;

    @NotBlank(message = "Tên Phiếu Không Được Để Trống !")
    private String ten;

    @NotBlank(message = "Bạn Chưa Nhập Mô Tả !")
    private String moTa;


    @NotNull(message = "Mức Giảm Giá Không Được Để Trống !")
    @Positive(message = "Mức Giảm Giá Phải Lớn Hơn 0 !")
    @Max(value = 100, message = "Mức Giảm Giá Không Được Vượt Quá 100 !")
    private Integer mucGiamGia;

    @NotNull(message = "Tiền Giảm Tối Đa Không Được Để Trống !")
    @Min(value = 1, message = "Mức Giảm Giá Tối Đa Thấp Nhất Là 1 !")
    private BigDecimal mucGiamToiDa;

    @NotNull(message = "Số Lượng Không Được Để Trống !")
    @Positive(message = "Số lượng Phải Lớn Hơn 0 !")
    private Integer soLuong;

    @NotNull(message = "Giá Trị áp Dụng Tối Thiểu Không Được Để Trống !")
    @Min(value = 0, message = "Giá Trị Áp Dụng Tối Thiểu Là 0 !")
    private BigDecimal giaTriDonHang;

    @NotNull(message = "Ngày Bắt Đầu Không Được Để Trống !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Ngày Bắt Đầu Phải Là Ngày Hiện Tại Hoặc Tương Lai !")
    private LocalDate ngayBatDau;

    @NotNull(message = "Ngày Kết Thúc Không Được Để Trống !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Ngày Kết Thúc Phải ở Hiện Tại Hoặc Tương Lai !")
    private LocalDate ngayKetThuc;

    private TrangThaiPhieuKhuyenMai trangThai;
    public TrangThaiPhieuKhuyenMai htTrangThai() {
        LocalDate now = LocalDate.now();
        //kiểm tra trạng SAP_DIEN_RAthái nếu ngày hiện tại cách ngày bắt đầu trong vòng 4 ngày.
        LocalDate daysAgo = this.ngayBatDau.minusDays(4);
//        kiểm tra xem ngày hiện tại có phải là ngày bắt đầu chương trình khuyến mãi hay không hoặc bất kỳ ngày nào trong thời gian khuyến mãi
        if (now.isEqual(ngayBatDau) || (now.isAfter(ngayBatDau) && now.isBefore(ngayKetThuc.plusDays(1)))) {
            return TrangThaiPhieuKhuyenMai.DANG_DIEN_RA;
        } else if (now.isAfter(daysAgo) && now.isBefore(ngayBatDau)) {
            return TrangThaiPhieuKhuyenMai.SAP_DIEN_RA;
            //kiểm tra trạng DA_KET_THUCthái nếu ngày kết thúc khuyến mãi đã qua hoặc số lượng bằng 0.
        } else if (now.isAfter(ngayKetThuc) || soLuong == 0) {
            return TrangThaiPhieuKhuyenMai.DA_KET_THUC;
        } else if (ngayBatDau.isAfter(now)) {
            return TrangThaiPhieuKhuyenMai.SAP_DIEN_RA;  // Assuming there is a status for "not started yet"
        } else {
            return TrangThaiPhieuKhuyenMai.DA_KET_THUC;
        }
    }
//    public  TrangThaiPhieuKhuyenMai htTrangThai() {
//        LocalDate DaysAgo = this.ngayBatDau.minusDays(4);
//        if (LocalDate.now().isEqual(ngayBatDau)) {
//            return  TrangThaiPhieuKhuyenMai.DANG_DIEN_RA;
//        } else if (LocalDate.now().isAfter(DaysAgo) && LocalDate.now().isBefore(ngayBatDau)) {
//            return  TrangThaiPhieuKhuyenMai.SAP_DIEN_RA;
//        } else if (ngayBatDau.isAfter(LocalDate.now())) {
//            return  TrangThaiPhieuKhuyenMai.DA_KET_THUC;
//        } else if (ngayKetThuc.isBefore(LocalDate.now())) {
//            return  TrangThaiPhieuKhuyenMai.DA_KET_THUC;
//        } else if(soLuong == 0){
//            return  TrangThaiPhieuKhuyenMai.DA_KET_THUC;
//        } else {
//            return  TrangThaiPhieuKhuyenMai.DANG_DIEN_RA;
//        }
//    }



}


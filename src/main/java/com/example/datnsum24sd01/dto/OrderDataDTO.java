package com.example.datnsum24sd01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//banhangonlineserviceimpl
public class OrderDataDTO {
    private String diaChi;
    private String xaPhuong;
    private String quanHuyen;
    private String thanhPho;
    private String sdt;
    private String ghiChu;
    private String ten;
    private BigDecimal shippingFee;
    private String tongTienHang;
    private BigDecimal totalAmount;
    private Long selectedVoucherId;
    private String useAllPointsHidden;
    private BigDecimal diemTichLuy;
    private BigDecimal tienGiamGia;
    private String paymentMethod;

}

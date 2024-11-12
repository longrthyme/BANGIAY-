package com.example.datnsum24sd01.enumation;

public enum TrangThaiDonHang {
    HOA_DON_CHO(0),
    CHO_XAC_NHAN(1),
    DANG_CHUAN_BI(2),
    DANG_GIAO(3),
    DA_GIAO(4),
    HOAN_THANH(5),
    DA_HUY(6),
    XAC_NHAN_TRA_HANG(7),
    DA_TRA_HANG(8),
    DOI_HANG(9);


    private final Integer trangThai;

    TrangThaiDonHang(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }

    public String getDisplayName() {
        switch (this) {
            case HOA_DON_CHO:
                return "Hoá Đơn Chờ";
            case CHO_XAC_NHAN:
                return "Chờ Xác Nhận";
            case DANG_CHUAN_BI:
                return "Đang Chuẩn Bị";
            case DANG_GIAO:
                return "Đang Giao";
            case DA_GIAO:
                return "Đã Giao";
            case HOAN_THANH:
                return "Hoàn Thành";
            case DA_HUY:
                return "Đã Hủy";
            case DA_TRA_HANG:
                return "Đã Hoàn Trả ";
            case XAC_NHAN_TRA_HANG:
                return " Hoàn Trả";
            case DOI_HANG:
                return "Đổi Hàng";
            default:
                return this.name(); // Returns the default enum name if no corresponding name is found
        }
    }
}

package com.example.datnsum24sd01.enumation;


public enum TrangThai {

    DANG_HOAT_DONG(0),
    DUNG_HOAT_DONG(1),
    YEU_CAU_TRA_HANG(2),
    DA_TRA_HANG(3),
    TU_CHOI_TRA_HANG(4),
    DOI_HANG(5),
    DA_DOI_HANG(6),
    TU_CHOI_DOI_HANG(7),
    XAC_NHAN_HOAN_TRA(8),
    DA_HOAN_TRA(9),
    SAP_DIEN_RA(10);

    private final Integer trangThai;

    TrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }
    public String getDisplayName() {
        switch (this) {
            case DANG_HOAT_DONG:
                return "Đang Hoạt Động";
            case DUNG_HOAT_DONG:
                return "Dừng Hoạt Động";
            case YEU_CAU_TRA_HANG:
                return "Yêu Cầu Hoàn Trả";
            case DA_TRA_HANG:
                return "Đã Hoàn Trả";
            case TU_CHOI_TRA_HANG:
                return "Từ Chối Hoàn Trả";
            case DOI_HANG:
                return "Đổi Hàng";
            case DA_DOI_HANG:
                return "Đã Đổi Hàng";
            case TU_CHOI_DOI_HANG:
                return "Từ Chối Đổi Hàng";
            case SAP_DIEN_RA:
                return "Sắp diễn ra";
            case XAC_NHAN_HOAN_TRA:
                return "Xác nhận hoàn trả";
            case DA_HOAN_TRA:
                return "Đã hoàn trả";
            default:
                return this.name();
        }
    }
    private int newInvoiceCount;
    public int getNewInvoiceCount() {
        return newInvoiceCount;
    }
}

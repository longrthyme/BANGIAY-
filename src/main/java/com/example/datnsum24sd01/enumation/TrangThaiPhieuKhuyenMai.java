package com.example.datnsum24sd01.enumation;
//biểu diễn một nhóm các hằng số có liên quan
public enum TrangThaiPhieuKhuyenMai {
    DANG_DIEN_RA(0),
    DA_KET_THUC(1),
    SAP_DIEN_RA(2);

    private final Integer trangThai;

    TrangThaiPhieuKhuyenMai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }

    public String getDisplayName() {
        switch (this) {
            case DANG_DIEN_RA:
                return "Đang Diễn Ra";
            case DA_KET_THUC:
                return "Đã Kết Thúc";
            case SAP_DIEN_RA:
                return "Sắp Diễn Ra";
            default:
                return this.name();
        }
    }
    public String getColor() {
        switch (this) {
            case DANG_DIEN_RA:
                return "#86e27f";// light green background
            case DA_KET_THUC:
                return "#ff8c82"; // light red background
            case SAP_DIEN_RA:
                return "#ffe97f"; // light yellow background
            default:
                return "#ffffff"; // default background
        }
    }
}

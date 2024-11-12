package com.example.datnsum24sd01.enumation;

public enum LoaiHoaDon {
     HOA_DON_OFFLINE(0),

    HOA_DON_ONLINE(1);

    private final Integer loaiHoaDon;

    LoaiHoaDon(Integer loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public Integer getLoaiHoaDon() {
        return this.loaiHoaDon;
    }

    public String getDisplayName() {
        switch (this) {
            case HOA_DON_ONLINE:
                return "Mua Hàng Online";
            case HOA_DON_OFFLINE:
                return "Mua Hàng Offline";

            default:
                return this.name(); // Returns the default enum name if no corresponding name is found
        }
    }
}

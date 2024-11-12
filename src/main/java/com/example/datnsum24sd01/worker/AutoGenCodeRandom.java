package com.example.datnsum24sd01.worker;

import java.util.UUID;
//tự dộng gen mật khẩu nhân viên
public class AutoGenCodeRandom {
    public static String genUUID(){
        return UUID.randomUUID().toString().substring(0, 6);
    }
}

package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.dto.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {

    public VNPayResponse createVnPayPayment(HttpServletRequest request, int orderTotal, String orderInfo, String vnPayReturnUrl);



}

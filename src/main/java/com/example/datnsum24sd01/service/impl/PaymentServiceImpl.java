package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.configure.VNPayConfig;
import com.example.datnsum24sd01.dto.VNPayResponse;
import com.example.datnsum24sd01.service.PaymentService;
import com.example.datnsum24sd01.utils.VNPayUtil    ;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private VNPayConfig vnPayConfig;
    
    @Autowired
    private VNPayUtil vnPayUtil;


    // thanh toán
    @Override
    public VNPayResponse createVnPayPayment(HttpServletRequest request, int orderTotal, String orderInfo, String vnPayReturnUrl) {
        String username = request.getParameter("username");
        Map<String, String> vnpParamsMap;
        vnpParamsMap = vnPayConfig.getVNPayConfig(username, vnPayReturnUrl);


        vnpParamsMap.put("vnp_Amount", String.valueOf((long)orderTotal * 100));
        vnpParamsMap.put("vnp_IpAddr", vnPayUtil.getIpAddress(request));

        // Build query URL
        String queryUrl = vnPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = vnPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = vnPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnpPayUrl() + "?" + queryUrl;

        return VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }


//         sau khi thanh toán
        public int orderReturn(HttpServletRequest request){
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }



        String signValue = vnPayUtil.hashAllFields(fields);

        // so sánh vnp_SecureHash và signValue có trùng nhau ?
        if (signValue.equals(vnp_SecureHash)) {
            System.out.println("in compare");
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
}

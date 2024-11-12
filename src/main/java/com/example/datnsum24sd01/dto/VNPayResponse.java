package com.example.datnsum24sd01.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;
@Data
@Builder
public class VNPayResponse {

    public String code;
    public String message;
    public String paymentUrl;

}

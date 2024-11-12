package com.example.datnsum24sd01.service;

import com.example.datnsum24sd01.entity.DeGiay;
import com.example.datnsum24sd01.request.DeGiayRequest;

import java.util.List;

public interface Degiayserviec {
    List<DeGiay> getAllDeGiay();

    DeGiay add(DeGiayRequest deGiayRequest);

    String delete(Long id);

    DeGiay getOne(Long id);

    DeGiay findById(Long id);

    DeGiay update(DeGiay deGiay);
}

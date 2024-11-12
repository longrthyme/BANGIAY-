package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.DeGiay;
import com.example.datnsum24sd01.request.DeGiayRequest;
import com.example.datnsum24sd01.responsitory.DeGiayResponsitory;
import com.example.datnsum24sd01.service.Degiayserviec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DegiayServiceImpl implements Degiayserviec {
    @Autowired
    private DeGiayResponsitory deGiayRepository;

    @Override
    public List<DeGiay> getAllDeGiay() {
        return deGiayRepository.findAll();
    }

    @Override
    public DeGiay add(DeGiayRequest deGiayRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DeGiay deGiay = new DeGiay();
        deGiay.setMa(deGiayRequest.getMa());
        deGiay.setNgaySua(currentDateTime);
        deGiay.setNgayTao(currentDateTime);
        deGiay.setTen(deGiayRequest.getTen());

        DeGiay deGiayAddLater = deGiayRepository.save(deGiay);
        String maDG = "DG" + deGiayAddLater.getId().toString();
        deGiayAddLater.setMa(maDG);
        return deGiayRepository.save(deGiayAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            deGiayRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete DeGiay with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete DeGiay with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public DeGiay getOne(Long id) {
        Optional<DeGiay> deGiay = this.deGiayRepository.findById(id);
        return deGiay.get();
    }

    @Override
    public DeGiay findById(Long id) {
        Optional<DeGiay> deGiay = deGiayRepository.findById(id);
        if (deGiay.isPresent()) {
            return deGiay.get();
        }
        return null;
    }

    @Override
    public DeGiay update(DeGiay deGiay) {
        deGiay.setNgayTao(this.deGiayRepository.findById(deGiay.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        deGiay.setNgaySua(currentDateTime);
        return this.deGiayRepository.save(deGiay);
    }
}

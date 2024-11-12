package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.request.NhanVienRequest;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.sendmail.EmailService;
import com.example.datnsum24sd01.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    String generatedPassword = generateRandomPassword(10);
    @Autowired
    private EmailService emailService;


    private String generateRandomPassword(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien add(NhanVienRequest nhanVienRequset) {


        LocalDateTime currentDateTime = LocalDateTime.now();
        NhanVien nhanVien = new NhanVien();
        nhanVien.setTen(nhanVienRequset.getTen());
        nhanVien.setEmail(nhanVienRequset.getEmail());
        nhanVien.setGioiTinh(nhanVienRequset.isGioiTinh());
        nhanVien.setSdt(nhanVienRequset.getSdt());
        nhanVien.setCanCuocCongDan(nhanVienRequset.getCanCuocCongDan());
        nhanVien.setMatKhau(generatedPassword);
        nhanVien.setCanCuocCongDan(nhanVienRequset.getCanCuocCongDan());
        nhanVien.setDiachi(nhanVienRequset.getDiachi());
        nhanVien.setTrangThai(nhanVienRequset.getTrangThai());
        nhanVien.setNgayTao(currentDateTime);


        NhanVien nhanVienAddLater = nhanVienRepository.save(nhanVien);
        String maNV = "NV" + nhanVienAddLater.getId().toString();
        nhanVienAddLater.setMa(maNV);

        emailService.sendNewAccountNVEmail(nhanVien.getEmail(), nhanVien.getEmail(),generatedPassword);

        return nhanVienRepository.save(nhanVienAddLater);
    }

    @Override
    public String delete(Long id) {
        String noti = "";
        try {
            nhanVienRepository.deleteById(id);
            noti = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            noti = "Cannot delete NhanVien with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete NhanVien with id " + id + " because it is referenced by HoaDon");
        }
        return noti;
    }

    @Override
    public boolean checkPhoneDuplicate(String phone) {
        boolean exists = nhanVienRepository.existsNhanVienBySdt(phone);
        return exists;
    }

    @Override
    public boolean checkPhoneDuplicate(String phone, long id) {
        return false;
    }

    @Override
    public boolean checkCccdDuplicate(String cccd) {
        boolean exists = nhanVienRepository.existsNhanVienByCanCuocCongDan(cccd);
        return exists;
    }

    @Override
    public boolean checkCccdDuplicate(String cccd, long id) {
        return false;
    }

    @Override
    public boolean checkEmailDuplicate(String mail) {
        boolean exists = nhanVienRepository.existsNhanVienByEmail(mail);
        return exists;
    }

    @Override
    public boolean checkEmailDuplicate(String mail, long id) {
        return false;
    }

    @Override
    public NhanVien getOne(long id) {
        Optional<NhanVien> nhanVien = this.nhanVienRepository.findById(id);
        return nhanVien.get();
    }

    @Override
    public NhanVien update(NhanVien nhanVien) {

        nhanVien.setNgayTao(this.nhanVienRepository.findById(nhanVien.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        nhanVien.setNgaySua(currentDateTime);
        return this.nhanVienRepository.save(nhanVien);
    }

    @Override
    public void thayDoiTrangThai(long id) {


        NhanVien nhanVien = this.nhanVienRepository.findById(id).get();

        if(nhanVien.getTrangThai() == 0){
            nhanVien.setTrangThai(1);
        }else{
            nhanVien.setTrangThai(0);
        }
        this.nhanVienRepository.save(nhanVien);
    }
}

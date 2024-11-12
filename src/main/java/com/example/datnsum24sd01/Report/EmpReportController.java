package com.example.datnsum24sd01.Report;


import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.responsitory.NhanVienRepository;
import com.example.datnsum24sd01.service.EmpExcelService;
import com.example.datnsum24sd01.service.impl.EmpExcelImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

@RequestMapping("/employee/report")
public class EmpReportController {


    private final NhanVienRepository nhanVienRepository;


    private EmpExcelService empExcelService;


    public EmpReportController(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }

    @GetMapping("/excel")
    public void exportIntoExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<NhanVien> listOfRecords = nhanVienRepository.findAll();


        empExcelService = new EmpExcelImpl(listOfRecords);


        empExcelService.exportToExcel(response);

    }
}

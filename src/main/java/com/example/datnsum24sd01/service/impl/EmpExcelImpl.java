package com.example.datnsum24sd01.service.impl;

import com.example.datnsum24sd01.entity.NhanVien;
import com.example.datnsum24sd01.service.EmpExcelService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpExcelImpl implements EmpExcelService {
//xuất excel nhân viên
    private List<NhanVien> listRecords;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private final List<String> empCellName = new ArrayList<>(
            List.of("id", "ma", "ten", "gioi_tinh", "sdt", "email", "dia_chi", "cccd", "mat_khau", "ngay_tao", "ngay_sua", "trang_thai")
    );


    public EmpExcelImpl(List<NhanVien> listRecords) {
        this.listRecords = listRecords;
        workbook = new XSSFWorkbook();
    }

    // header cho excel
    private void writeHeader() {
        // ten sheet
        sheet = workbook.createSheet("DS_Nhan_Vien");

        // header - dòng đầu tiên
        Row row = sheet.createRow(0);

        // set style cho cell
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        // gán tên cho header
        for (int i = 0; i < empCellName.size(); i++) {
            String fieldName = empCellName.get(i);

            createCell(row, i, fieldName, style);
        }

    }

    // giá trị cho ô trong excel
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = ((LocalDateTime) value).format(formatter);
            cell.setCellValue(formattedDate);
        }


        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);


        Field[] fields = NhanVien.class.getDeclaredFields();

        for (NhanVien record : listRecords) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;


            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                try {
                    Object value = field.get(record);
                    createCell(row, columnCount++, value, style);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }




        }
    }


    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {

        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}

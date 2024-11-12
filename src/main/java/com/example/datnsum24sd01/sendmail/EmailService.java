package com.example.datnsum24sd01.sendmail;

import com.example.datnsum24sd01.entity.GioHangChiTiet;
import com.example.datnsum24sd01.entity.HoaDon;
import com.example.datnsum24sd01.entity.KhachHang;
import com.example.datnsum24sd01.enumation.TrangThaiDonHang;
import com.example.datnsum24sd01.responsitory.GioHangChiTietRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("duongpvph20350@fpt.edu.vn")
    private String senderEmail;
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNewAccountNVEmail(String recipientEmail, String email ,String matKhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Chào Mừng: Thư xác nhận nhân viên của BeeStore");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
                "Tài khoản mới với tên đăng nhập : "+ email+" ,\n\n" +
                "Mật Khẩu đăng nhập : " + matKhau + " ,\n\n" +
                "Một lần nữa chúc mừng bạn là thành viên của BeeStore : http://localhost:8080/beestore/trang-chu  ,\n\n" +
                " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                "Trân trọng,\n[BEESTORE]");

        javaMailSender.send(message);
    }
    public void sendNewAccountKHEmail(String recipientEmail, String email ,String matkhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Thông báo: Tài khoản mới đã được tạo");
        message.setText("Chào '"+ recipientEmail +"' ,\n\n" +
                "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
                "Tài khoản mới với tên đăng nhập : "+ email +" ,\n\n" +
                "Mật Khẩu đăng nhập : " +matkhau  + " ,\n\n" +
                "Cùng đăng nhập để trải nhiệm nhiều tiện ích tuyệt vời nhé :  http://localhost:8080/beestore/trang-chu  ,\n\n" +
                " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                "Trân trọng,\n[BEESTORE]");

        javaMailSender.send(message);
    }



    public void sendEmail1(KhachHang khachHang, HoaDon hoaDon) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DecimalFormat decimalFormat = (DecimalFormat) currencyFormat;
        decimalFormat.applyPattern("#,### VND");
        String from = "duongpvph20350@fpt.edu.vn";
        String to = khachHang.getEmail();
        String subject = "Thông tin hóa đơn";
        StringBuilder content = new StringBuilder("<div style=\"font-family: 'Arial', sans-serif; font-size: 16px; color: black;\">")
                .append("<p style=\"color: black;\"><b>Xin chào ").append(khachHang.getTen()).append(",</b></p>")
                .append("<p style=\"color: black;\">Cảm ơn bạn đã tin tưởng và mua hàng tại BeeStore.</p>");

        if (hoaDon.getTrangThai() == TrangThaiDonHang.DANG_CHUAN_BI) {
            content.append("<p style=\"color: black;\">Chúng tôi xin thông báo rằng đơn hàng của bạn hiện đang trong quá trình chuẩn bị và sẽ sớm được gửi đến địa chỉ của bạn.</p>")
                    .append("<p style=\"color: black;\">Quý khách có thể tra cứu thông tin đơn hàng tại đây: http://localhost:8080/beestore/tra-cuu-don-hang</p>");
        } else if (hoaDon.getTrangThai() == TrangThaiDonHang.DA_GIAO) {
            content.append("<p style=\"color: black;\">Chúc mừng! Đơn hàng của bạn đã được giao thành công đến địa chỉ của bạn!</p>")
                    .append("<p style=\"color: black;\">Vui lòng đăng nhập để xác nhận bạn đã nhận hàng và hài lòng với sản phẩm trong vòng 3 ngày</p>")
                    .append("<p style=\"color: black;\">Xin cảm ơn bạn đã lựa chọn sản phẩm của chúng tôi!</p>");

        } else if (hoaDon.getTrangThai() == TrangThaiDonHang.DA_HUY) {
            content.append("<p style=\"color: black;\">Xin chào!</p>")
                    .append("<p style=\"color: black;\">Chúng tôi rất tiếc phải thông báo rằng đơn hàng của bạn đã bị huỷ.</p>")
                    .append("<p style=\"color: black;\">Chúng tôi xin lỗi về bất kỳ bất tiện nào gây ra và hy vọng có cơ hội phục vụ bạn trong tương lai.</p>")
                    .append("<p style=\"color: black;\">Xin cảm ơn bạn đã quan tâm và lựa chọn sản phẩm của chúng tôi!</p>");
        } else {
            content.append("<p style=\"color: black;\">Đơn hàng của bạn hiện đang được xử lý. Chúng tôi sẽ thông báo khi đơn hàng gửi đến cho bạn.</p>")
                    .append("<p style=\"color: black;\">Chúng tôi rất cảm kích sự kiên nhẫn của bạn trong quá trình này.</p>");

        }
        if (hoaDon.getTrangThai() != TrangThaiDonHang.DA_HUY) {
            content.append("<p style=\"color: black;\"><strong>Thông tin đơn hàng: [[maHoaDon]]</strong></p>");
            content.append("<table border=\"1\" style=\"border-collapse: collapse;\">")
                    .append("<tr>")
                    .append("<th style=\"border: 1px solid black;\">STT</th>")
                    .append("<th style=\"border: 1px solid black;\">Tên sản phẩm</th>")
                    .append("<th style=\"border: 1px solid black;\">Kích Thước</th>")
                    .append("<th style=\"border: 1px solid black;\">Đế giày</th>")
                    .append("<th style=\"border: 1px solid black;\">Dòng sản phẩm</th>")
                    .append("<th style=\"border: 1px solid black;\">Thương Hiệu</th>")
                    .append("<th style=\"border: 1px solid black;\">Đơn giá</th>")
                    .append("<th style=\"border: 1px solid black;\">Số lượng</th>")
                    .append("</tr>");
            int stt = 1;
            List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietRepository.findByHoaDon(hoaDon.getId());
            for (GioHangChiTiet gioHangChiTiet : gioHangChiTiets) {
                content.append("<tr>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(stt++).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(gioHangChiTiet.getChiTietSanPham().getSanPham().getTen()).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(gioHangChiTiet.getChiTietSanPham().getKichThuoc().getTen()).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(gioHangChiTiet.getChiTietSanPham().getDeGiay().getTen()).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(gioHangChiTiet.getChiTietSanPham().getSanPham().getDongSanPham().getTen()).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(gioHangChiTiet.getChiTietSanPham().getSanPham().getThuongHieu().getTen()).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(decimalFormat.format(gioHangChiTiet.getDonGia())).append("</td>")
                        .append("<td style=\"border: 1px solid black;color: black;\">").append(gioHangChiTiet.getSoLuong()).append("</td>")
                        .append("</tr>");
            }
            content.append("</table>");


            if (hoaDon.getMaGiamGia() != null) {
                content.append("<p style=\"color: black;\" class=\"email-content\">\n" +
                        "Mã Giảm Giá: [[maGiamGia]]\n" +
                        "</p>\n"
                );
            }
            content.append("<p style=\"color: black;\" class=\"email-content\">\n" +
                    "Phí vận chuyển:[[phiVanChuyen]]\n" +
                    "</p>\n" +
                    "<p style=\"color: black;\" class=\"email-content\">\n" +
                    "Tổng tiền:[[tongTien]]\n" +
                    "</p>\n" +
                    "<p style=\"color: black;\" class=\"email-content\">\n" +
                    "Thanh toán: [[thanhToan]]\n" +
                    "</p>\n"
            );
            if (hoaDon.getNgayThanhToan() != null) {
                content.append("<p style=\"color: black;\" class=\"email-content\">\n" +
                        "Ngày thanh toán: [[ngayThanhToan]]\n" +
                        "</p>\n"
                );
            }
            content.append("<br>\n" +
                    "<p style=\"color: black;\" class=\"email-content\">\n" +
                    "Cảm ơn bạn đã chọn BeeStore! Nếu có bất kỳ thắc mắc nào hoặc cần hỗ trợ, hãy liên hệ với chúng tôi qua số :0398194211.\n" +
                    "</p>" +
                    "</div>"
            );
        }

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngayThanhToanFormatted;
            if (hoaDon.getNgayThanhToan() != null) {
                ngayThanhToanFormatted = hoaDon.getNgayThanhToan().format(formatter);
            } else {
                ngayThanhToanFormatted = "";
            }

            helper.setFrom(from, "BeeStore");
            helper.setTo(to);
            helper.setSubject(subject);

            String content1 = content.toString();

            content1 = content1.replace("[[name]]", khachHang.getTen());
            content1 = content1.replace("[[maHoaDon]]", hoaDon.getMa());
            content1 = content1.replace("[[diaChi]]", hoaDon.getDiaChi());
            content1 = content1.replace("[[sdt]]", hoaDon.getSdt());
            if (hoaDon.getMaGiamGia() != null) {
                content1 = content1.replace("[[maGiamGia]]", String.valueOf(hoaDon.getMaGiamGia().getMa()));
            }
            content1 = content1.replace("[[phiVanChuyen]]", decimalFormat.format(hoaDon.getPhiVanChuyen()));
            content1 = content1.replace("[[thanhToan]]", decimalFormat.format(hoaDon.getThanhToan()));
            content1 = content1.replace("[[tongTien]]", decimalFormat.format(hoaDon.getTongTien()));
            if (hoaDon.getNgayThanhToan() != null) {
                content1 = content1.replace("[[ngayThanhToan]]", ngayThanhToanFormatted);
            }
            helper.setText(content1, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void sendPasswordEmail(String recipientEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("THÔNG BÁO : BẠN ĐÃ YÊU CẦU KHÔI PHỤC MẬT KHẨU !");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                "Bạn vừa dùng mail này để xác nhận quên mật khẩu tài khoản BeeStore,\n\n" +
                "Mật Khẩu đăng nhập mới của bạn là : " + newPassword + " ,\n\n" +
                "Nếu bạn không xác nhận quên mật khẩu mà vẫn nhận được mail này thì hãy liên hệ lại với BeeStore ngay lập tức qua hotline :0398194211   ,\n\n" +
                "Trân trọng,\n[BEESTORE]");

        javaMailSender.send(message);
    }
}


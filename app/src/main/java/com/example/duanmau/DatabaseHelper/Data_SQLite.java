package com.example.duanmau.DatabaseHelper;

public class Data_SQLite {
    public static final String INSERT_THU_THU ="insert into ThuThu(maTT,hoTen,matKhau) values" +
            "('admin','Nguyễn Tiến Phước','admin')," +
            "('shindz','Nguyễn  Văn A','shin123')," +
            "('vanb','Nguyễn  Văn B','vanb123')," +
            "('vanga','Nguyễn  Văn Gà','vanga123')";
    public static  final String INSERT_THANH_VIEN="insert into ThanhVien(hoTen,namSinh) values" +
            "('Nguyễn Thanh Thể','2003')," +
            "('Phạm Thiên Đồng','2004')," +
            "('Thạch Bảo Lộc','2001')," +
            "('Phan Văn Toại','2006')";
    public static final  String INSERT_LOAI_SACH="insert into LoaiSach(tenLoai) values" +
            "('Tiếng Anh cơ bản')," +
            "('Tiếng Anh nâng cao')," +
            "('Lập trình cơ bản')," +
            "('Lập trình Android')," +
            "('Lập trình Java')," +
            "('Lập trình Website')";
    public static final String INSERT_SACH ="insert into Sach(tenSach,giaThue,maLoai) values" +
            "('Tiếng Anh cơ bản cho người mất gốc',2000,'1')," +
            "('Hack Não 1500 từ tiếng Anh',2500,'1')," +
            "('English Grammar in Use',3000,'1')," +
            "('Oxford Picture Dictionary ',3500,'2')," +
            "('IELTS Speaking',4000,'2')," +
            "('Advanced Vocabulary in Use',4500,'2')," +
            "('Python cơ bản',5000,'3')," +
            "('Lập trình và cuộc sống',5500,'3')," +
            "('Code dạo kí sự',6000,'3')," +
            "('Android Programming for Beginners',6500,'4')," +
            "('Head First Android Development ',7000,'4')," +
            "('Java Performance: The Definitive Guide',7500,'5')," +
            "('Effective Java',8000,'5')," +
            "('Java Programming: The Big Nerd Ranch Guide',8500,'5')," +
            "('HTML & CSS: Design and Build Websites',9000,'6')," +
            "('JavaScript: The Definitive Guide',9500,'6')";
    public static final  String INSERT_PHIEU_MUON="insert into PhieuMuon (maTT,maTV,maSach,tienThue,ngay,traSach) values" +
            "('admin',1,8,2000,'25/01/2023',1)," +
            "('shindz',2,10,2000,'07/06/2023',1)," +
            "('admin',3,13,2000,'09/03/2023',0)," +
            "('admin',2,16,2000,'09/05/2023',1)," +
            "('vanb',2,2,2000,'14/03/2023',1)," +
            "('vanga',3,2,2000,'15/09/2023',1)," +
            "('admin',4,2,2000,'21/09/2023',0)," +
            "('admin',1,6,2000,'26/09/2023',1)," +
            "('vanb',2,9,2000,'09/08/2023',0)," +
            "('admin',4,4,2000,'09/08/2023',1)";
}

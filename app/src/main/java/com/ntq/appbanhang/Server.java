package com.ntq.appbanhang;
public class Server {
    public static String localhost = "192.168.1.12:8080";
    public static String duongdanLoaiSP = "http://" + localhost + "/server/getloaisanpham.php";
    public static String duongDanSPMoiNhat = "http://" + localhost + "/server/getsanphammoinhat.php";
    public static String feedback = "http://" + localhost + "/server/feedback.php";
    public static String ID_NHAN="100";
    public static final String ID_SEND="idsend";
    public static final String ID_RECEIVE="received";
    public static final String MESS="message";
    public static final String DATE="datetime";
    public static final String PATH_CHAT="chat";
    public static int user_current;
    public  static String urlLoaiSP="http://"+localhost+"/server/getoaisanpham.php";
    public  static String urlSanPhamTheoLoai="http://"+localhost+"/server/getSPTheoLoai.php";


}

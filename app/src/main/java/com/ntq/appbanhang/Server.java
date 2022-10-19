package com.ntq.appbanhang;

import java.util.List;

public class Server {
    public static String localhost = "192.168.12.132:8080";
    public static String duongDanSPMoiNhat = "http://" + localhost + "/server/getsanphammoinhat.php";
    public static String ID_NHAN="100";
    public static final String ID_SEND="idsend";
    public static final String ID_RECEIVE="received";
    public static final String MESS="message";
    public static final String DATE="datetime";
    public static final String PATH_CHAT="chat";
    public static int user_current;
    public  static String urlLoaiSP="http://"+localhost+"/server/getLoaiSanPham.php";
    public  static String urlSanPhamTheoLoai="http://"+localhost+"/server/getSPTheoLoai.php";
    public static List<GioHang> listGioHang;

}

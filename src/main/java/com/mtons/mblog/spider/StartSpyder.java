package com.mtons.mblog.spider;
import java.io.IOException;
import java.net.URLStreamHandler;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mtons.mblog.modules.entity.BaiKe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class StartSpyder {
    public static Boolean runSpider(String word) throws IOException {
        /*根url，python词条页面*/
//        String word="张杰";
        String rooturl = "https://baike.baidu.com/item/"+word;
        Document rootdocument = Jsoup.connect(rooturl).get();
        int index=1;
        /*放置简介字符串*/
        List<String> contents = new ArrayList<>();
        ParseHtml parseHtml = new ParseHtml();
        ConnectNet connectNet = new ConnectNet();
        Map<String,String> data=parseHtml.parse_content(rootdocument, contents);
        data.put("title",word);
        data.put("url",rooturl);
//        System.out.println(data);
        try {
            connect(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static void connect(Map<String,String> data)throws Exception{
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://123.57.76.137/hongshan?useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
        String user="root";
        String pwd ="2000";
        Connection conn = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 打开链接
        System.out.println("连接数据库...");
        conn = (Connection) DriverManager.getConnection(url,user,pwd);
        stmt= (Statement) conn.createStatement();
        System.out.println("连接成");
        String baikeSql="SELECT * FROM baike WHERE title='"+data.get("title")+"' ";
//        stmt.execute(baikeSql);
        PreparedStatement ps =conn.prepareStatement(baikeSql);
        ResultSet rs = ps.executeQuery();
        List<BaiKe> list = new ArrayList<>();
        while(rs.next()) {
            BaiKe baiKe =new BaiKe();
            baiKe.setId(rs.getInt("id"));
            baiKe.setTitle(rs.getString("title"));
            list.add(baiKe);
        }
        if(list.isEmpty()){
            Calendar calendar= Calendar.getInstance();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String sql = "INSERT INTO baike(url,title, summary, basic_info, create_time) VALUES ('"+data.get("url")+"','"+data.get("title")+"','"+data.get("summary")+"','"+data.get("basic_info")+"','"+dateFormat.format(calendar.getTime())+"')";
            stmt.execute(sql);
            conn.close();
            System.out.println("词条保存成功");
        }
//        Calendar calendar= Calendar.getInstance();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        String sql = "INSERT INTO baike(url,title, summary, basic_info, create_time) VALUES ('"+data.get("url")+"','"+data.get("title")+"','"+data.get("basicInfo")+"','"+dateFormat.format(calendar.getTime())+"')";
//        stmt.execute(sql);
//        conn.close();
    }
}

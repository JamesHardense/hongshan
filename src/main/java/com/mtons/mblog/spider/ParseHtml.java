package com.mtons.mblog.spider;

import java.io.IOException;
import java.net.CookieHandler;
import java.util.*;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHtml {
    int num=1;
    public  void parse_a(Document document,List<String> urls) throws IOException {
        /*提取出href属性里面/item以及后面的字符*/
        Elements links = document.select("[href*=/item]");
        /*迭代输出，并且加入到url集合里面*/
        for (Element link : links) {
            String url  = "https://baike.baidu.com"+link.attr("href");
            /*过滤掉重复的url地址*/
            if (!urls.contains(url)) {
                urls.add(url);
            }
        }
    }

    public Map<String,String> parse_content(Document document, List<String> contents ) {
        /*使用Jsoup里面的选择器，详细用法可以查看jsoup的官方文档*/
        Elements links = document.select("div.lemma-summary");
        List<String> replaces= new ArrayList<>();
        String summary="";
        for(Element link:links){
            Elements sups=link.select("sup[class=sup--normal]");
            for (Element sup:sups){
                replaces.add(sup.text());
//                System.out.println(sup.text());
            }
            for (int i=0;i < replaces.size();i++){
                summary=link.text().replace(replaces.get(i),"");
            }
            contents.add(summary);
        }

        List<String> names = new ArrayList<>() ;
        List<String> values = new ArrayList<>();

        Elements namelist=document.select("dt[class=basicInfo-item name]");
        for (Element name:namelist){
            names.add(name.text());
//                value=basicinfo.select("dd[class=basicInfo-item value]").text();

        }
        Elements valuelist = document.select("dd[class=basicInfo-item value]");
        for (Element value:valuelist){
            values.add(value.text().replace("收起",""));
        }
        String basicinfo="";
        for(int i=0;i<names.size();i++){
            basicinfo=basicinfo+names.get(i)+":"+values.get(i)+",";
        }
        basicinfo=basicinfo.substring(0,basicinfo.length()-1);

//        System.out.println(basicinfo);
//        System.out.println(contents.get(0));
        Map<String,String> data=new HashMap<>();
        data.put("summary",contents.get(0));
        data.put("basic_info",basicinfo);
        return data;
    }

}

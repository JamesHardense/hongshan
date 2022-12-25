package com.mtons.mblog.spider;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class ConnectNet {
    public static Document getDom(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document;
    }

}

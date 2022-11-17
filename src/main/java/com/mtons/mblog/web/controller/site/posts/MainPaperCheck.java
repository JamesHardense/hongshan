package com.mtons.mblog.web.controller.site.posts;


import com.mtons.mblog.web.controller.site.utils.HammingUtils;
import com.mtons.mblog.web.controller.site.utils.SimHashUtils;

public class MainPaperCheck {

    public static void main(String[] args) {
        String str0 = "北京字节跳动科技有限公司，简称字节跳动，是一家位于中国北京的跨国互联网技术公司[2]，成立于2012年3月，旗下有产品今日头条和抖音（及其海外版本TikTok）、西瓜视频等。\n";
        String str1 = "北京字节跳动科技有限公司，简称字节跳动，是一家位于中国北京的跨国互联网技术公司[2]，成立于2012年3月，旗下有产品今日头条和抖音（及其海外版本TikTok）、西瓜视频等。\n";
        // 由字符串得出对应的 simHash值
        String simHash0 = SimHashUtils.getSimHash(str0);
        String simHash1 = SimHashUtils.getSimHash(str1);
        // 由 simHash值求出相似度
        double similarity = HammingUtils.getSimilarity(simHash0, simHash1);
        System.out.println(similarity);
    }

}
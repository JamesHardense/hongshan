package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Channel;

/**
 * 百度百科词条
 */
public interface BaiKeService {
    BaiKe findByTitle(String title);
}

package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Channel;
import com.mtons.mblog.modules.entity.Log;

import java.util.List;

/**
 * 百度百科词条
 */
public interface LogService {
    List<Log> findAllLog();
    Log findById(long id);
    List<Log> findLatestLog();
    List<Log> findLatestChannel(long channel_id);
    List<Log> findLatestTitle(String title);
}

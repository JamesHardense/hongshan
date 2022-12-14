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
    List<Log> findById(long id);
    Log findByIdRead(long hid);
    List<Log> findLatestLogs();
    Log findLatestLog(long id);
    List<Log> findLatestChannel(long channel_id);
    List<Log> findLatestTitle(String title);
    Boolean updateStatus(long id,long hid,int status);
    Boolean disagree(long hid);
    Boolean deleteLog(long hid);
    Boolean deletePosts(List<Long> ids);
}

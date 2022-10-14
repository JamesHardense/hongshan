package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.modules.entity.Log;
import com.mtons.mblog.modules.repository.LogRepository;
import com.mtons.mblog.modules.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public List<Log> findAllLog() {
        return logRepository.findAllLog();
    }


    public Log findById(long id){
        return logRepository.findById(id);
    }

    @Override
    public List<Log> findLatestLog() {
        return logRepository.findLatestLog();
    }

    @Override
    public List<Log> findLatestChannel(long channel_id) {
        return logRepository.findLatestChannel(channel_id);
    }

    @Override
    public List<Log> findLatestTitle(String title) {
        return logRepository.findLatestTitle(title);
    }
}

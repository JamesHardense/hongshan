package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Channel;
import com.mtons.mblog.modules.repository.BaiKeRepository;
import com.mtons.mblog.modules.service.BaiKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BaiKeServiceImpl implements BaiKeService {
    @Autowired
    private BaiKeRepository baiKeRepository;
    @Override
    public BaiKe findByTitle(String title) {
        return baiKeRepository.findByTitle(title);
    }
}

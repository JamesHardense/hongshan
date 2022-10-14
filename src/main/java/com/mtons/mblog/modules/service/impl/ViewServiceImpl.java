package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.modules.entity.View;
import com.mtons.mblog.modules.repository.ViewRepository;
import com.mtons.mblog.modules.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ViewServiceImpl implements ViewService {
    @Autowired
    private ViewRepository viewRepository;

    @Override
    public List<View> findAuthorChannel() {
        return viewRepository.findAuthorChannel();
    }
}

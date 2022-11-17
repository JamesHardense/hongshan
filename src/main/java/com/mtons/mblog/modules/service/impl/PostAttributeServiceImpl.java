package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.PostAttribute;
import com.mtons.mblog.modules.repository.BaiKeRepository;
import com.mtons.mblog.modules.repository.PostAttributeRepository;
import com.mtons.mblog.modules.service.PostAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostAttributeServiceImpl implements PostAttributeService {
    @Autowired
    private PostAttributeRepository postAttributeRepository;

    @Override
    public List<PostAttribute> checkSummary() {
        return postAttributeRepository.checkSummary();
    }
}

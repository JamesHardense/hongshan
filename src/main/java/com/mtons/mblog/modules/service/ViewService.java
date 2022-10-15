package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.entity.View;

import java.util.List;

public interface ViewService {
    List<View> findAuthorChannel();
    List<View> findAuthorById(long id);
}

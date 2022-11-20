package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.base.utils.ResourceLock;
import com.mtons.mblog.modules.data.PostVO;
import com.mtons.mblog.modules.entity.Log;
import com.mtons.mblog.modules.entity.Post;
import com.mtons.mblog.modules.entity.PostAttribute;
import com.mtons.mblog.modules.repository.LogRepository;
import com.mtons.mblog.modules.repository.PostAttributeRepository;
import com.mtons.mblog.modules.repository.PostRepository;
import com.mtons.mblog.modules.service.LogService;
import com.mtons.mblog.modules.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostAttributeRepository postAttributeRepository;
    @Autowired
    private PostService postService;
    @Override
    public List<Log> findAllLog() {
        return logRepository.findAllLog();
    }


    public List<Log> findById(long id){
        return logRepository.findById(id);
    }

    public Log findByIdRead(long hid){
        return logRepository.findByIdRead(hid);
    }

    @Override
    public List<Log> findLatestLogs() {
        return logRepository.findLatestLogs();
    }

    @Override
    public Log findLatestLog(long id) {
        return logRepository.findLatestLog(id);
    }

    @Override
    public List<Log> findLatestChannel(long channel_id) {
        return logRepository.findLatestChannel(channel_id);
    }

    @Override
    public List<Log> findLatestTitle(String title) {
        return logRepository.findLatestTitle(title);
    }

    @Override
    @Transactional
    public Boolean updateStatus(long id,long hid ,int status) {
        logRepository.updateStaus(hid,status);
        Log log=logRepository.findByIdRead(hid);
        List<Log> list=logRepository.findById(id);
        Post post = postService.get(id);
        PostVO postVO = new PostVO();
        postVO.setAuthorId(log.getAuthorId());
        postVO.setEditor("markdown");
        postVO.setId(log.getId());
        postVO.setTitle(log.getTitle());
        postVO.setChannelId(log.getChannelId());
        postVO.setId(id);
        postVO.setContent(log.getSummary());
        postService.update(postVO);
        postRepository.updateStatus(id,status);
        return true;
    }

    @Override
    @Transactional
    public Boolean disagree(long hid) {
        Log log=logRepository.findByIdRead(hid);
        long id= log.getId();
        String title = log.getTitle();
        List<Log> list=logRepository.findById(log.getId());
        Post post = postService.get(log.getId());
//        PostVO postVO = new PostVO();
//        postVO.setAuthorId(log.getAuthorId());
//        postVO.setEditor("markdown");
//        postVO.setId(log.getId());
        Optional<Post> optional = postRepository.findById(id);
        if (list.size()>1){
            logRepository.delete(log);
//            Log latest = logRepository.findLatestById(id);
//            postVO.setContent(latest.getSummary());
//            postVO.setChannelId(latest.getChannelId());
//            postVO.setTitle(latest.getTitle());
//            postService.update(postVO);
            postRepository.updateStatus(id,1);
        }else{
            logRepository.delete(log);
            postRepository.delete(post);
            postAttributeRepository.deleteById(id);

        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteLog(long hid) {
        long id = logRepository.findByIdRead(hid).getId();
        List<Log> logs=logRepository.deleteListById(id);
        System.out.println(logs.size());
        for (int i=0;i<logs.size();i++){
            Log log = logs.get(i);
            logRepository.delete(log);
        }
        PostVO postVO = postService.get(id);
        Post post=new Post();
        BeanUtils.copyProperties(postVO, post);
        System.out.println("sdfsdf:"+post.getId());
        postRepository.delete(post);
        postAttributeRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean deletePosts(List<Long> ids) {
        for(int i=0;i<ids.size();i++){
            List<Log> logs=logRepository.deleteListById(ids.get(i));
            for(int j=0;j<logs.size();j++){
                logRepository.delete(logs.get(j));
            }
            PostVO postVO = postService.get(ids.get(i));
            Post post=new Post();
            BeanUtils.copyProperties(postVO, post);
            postRepository.delete(post);
            postAttributeRepository.deleteById(ids.get(i));

        }
        return true;
    }
}

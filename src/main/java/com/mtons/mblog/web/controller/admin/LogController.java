package com.mtons.mblog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Log;
//import com.mtons.mblog.modules.entity.Test;
import com.mtons.mblog.modules.entity.View;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.modules.service.LogService;
import com.mtons.mblog.modules.service.ViewService;
import com.mtons.mblog.web.controller.BaseController;
import org.jboss.logging.annotations.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章操作
 * @author langhsu
 *
 */
@RestController
@RequestMapping("/admin/post")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    @Autowired
    private ViewService viewService;

    /**
     * 查看抽取信息
     * @return
     */

    @GetMapping("/log")
    public List<Log> getLog() {
        return logService.findAllLog();
    }

    @PostMapping("/log/list")
    public List<Log> getLogById(@RequestBody Log log){
        return logService.findById(log.getId());
    }

    @PostMapping("/log/read")
    public Log getReadById(@RequestBody Log log){
        return logService.findByIdRead(log.getHid());
    }

    @GetMapping("/log/latest")
    public List<Log> getLogLatest() {
        return logService.findLatestLogs();
    }

    @PostMapping("/log/channel")
    public List<Log> getLogByChannel(@RequestBody Log log){
        return logService.findLatestChannel(log.getChannelId());
    }

    @PostMapping("/log/title")
    public List<Log> getLogByTitle(@RequestBody Log log){
        return logService.findLatestTitle(log.getTitle());
    }

    @PostMapping("/log/agree")
    public Boolean updateAgree(@RequestBody Log log){
        return  logService.updateStatus(log.getId(),log.getHid(),1);
    }

    @PostMapping("/log/disagree")
    public Boolean updateDisagree(@RequestBody Log log){
        return  logService.disagree(log.getHid());
    }

    @PostMapping("/log/delete")
    public Boolean deleteLog(@RequestBody Log log){
        return logService.deleteLog(log.getHid());
    }

    @PostMapping("/log/status")
    public int findLogStatus(@RequestBody Log log){
        return logService.findLatestLog(log.getId()).getStatus();
    }
}

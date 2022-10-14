package com.mtons.mblog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Log;
//import com.mtons.mblog.modules.entity.Test;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.modules.service.LogService;
import com.mtons.mblog.web.controller.BaseController;
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

    /**
     * 查看抽取信息
     * @return
     */

    @GetMapping("/log")
    public List<Log> getLog() {
        return logService.findAllLog();
    }

    @PostMapping("/log/list")
    public Log getLogById(@RequestBody Log log){
        return logService.findById(log.getId());
    }

}

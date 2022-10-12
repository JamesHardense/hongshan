package com.mtons.mblog.web.controller.site.posts;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Test;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.web.controller.BaseController;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章操作
 * @author langhsu
 *
 */
@RestController
@RequestMapping("/post")
public class BaikeController extends BaseController {
    @Autowired
    private BaiKeService baiKeService;

    /**
     * 查看抽取信息
     * @return
     */

    @PostMapping("/baike")
    public BaiKe getBaike(@RequestBody BaiKe baiKe) {
        return baiKeService.findByTitle(baiKe.getTitle());
    }

}

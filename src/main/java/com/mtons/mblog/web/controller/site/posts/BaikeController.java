package com.mtons.mblog.web.controller.site.posts;

import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文章操作
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/post")
public class BaikeController extends BaseController {
    @Autowired
    private BaiKeService baiKeService;

    /**
     * 查看抽取信息
     * @return
     */
//    @ResponseBody
    @RequestMapping("/baike")
    public String find(String title,ModelMap model) {
        model.put("baike",baiKeService.findByTitle(title));
        return "/post/baike";
    }
}

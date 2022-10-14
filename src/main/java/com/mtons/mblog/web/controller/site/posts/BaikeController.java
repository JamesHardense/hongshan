package com.mtons.mblog.web.controller.site.posts;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Test;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.web.controller.BaseController;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mtons.mblog.modules.data.BaikeVO;
import com.mtons.mblog.modules.data.BasicInfoVO;
import java.util.List;

import java.util.ArrayList;
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
    public BaikeVO getBaike(@RequestBody BaiKe baiKe) {
        BaiKe baike=baiKeService.findByTitle(baiKe.getTitle());
        BaikeVO baikeVO = new BaikeVO();
        baikeVO.setId(baike.getId());
        baikeVO.setSummary(baike.getSummary());
        baikeVO.setTitle(baike.getTitle());
        String[] basicInfo= baike.getBasicInfo().split(",");
        List<BasicInfoVO> basicInfoVOS = new ArrayList<>();
        for (int i=0;i<basicInfo.length;i++){
            String[] map = basicInfo[i].split(":");
            BasicInfoVO basicInfoVO=new BasicInfoVO();
            basicInfoVO.setKey(map[0]);
            basicInfoVO.setName(map[1]);
            basicInfoVOS.add(basicInfoVO);
        }
        baikeVO.setBasicInfoVOS(basicInfoVOS);
        return baikeVO;
    }

}

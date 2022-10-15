package com.mtons.mblog.web.controller.site.posts;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.data.PostVO;
import com.mtons.mblog.modules.entity.BaiKe;
import com.mtons.mblog.modules.entity.Log;
import com.mtons.mblog.modules.entity.Post;
import com.mtons.mblog.modules.entity.Test;
import com.mtons.mblog.modules.repository.LogRepository;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import com.mtons.mblog.modules.data.BaikeVO;
import com.mtons.mblog.modules.data.BasicInfoVO;

import java.util.Date;
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
    @Autowired
    private PostService postService;
    @Autowired
    private LogRepository logRepository;

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

    @PostMapping("/submit")
    public String post(@RequestBody PostVO post) {
//		System.out.println(post.getEditor());
        Assert.notNull(post, "参数不完整");
        Assert.state(StringUtils.isNotBlank(post.getTitle()), "标题不能为空");
        Assert.state(StringUtils.isNotBlank(post.getContent()), "内容不能为空");
        if (postService.findPostByTitle(post.getTitle()) != null && post.getId()<=0){
            String message= "已有词条"+post.getTitle()+"的信息，请勿重新创建";
//            Assert.state(false,message );
            return "已有词条:"+post.getTitle()+" 的信息，请勿重新创建!";
        }
        AccountProfile profile = getProfile();
//        if (post.getId()<=0){
            post.setAuthorId(profile.getId());
//        }


        // 修改时, 验证归属
        if (post.getId() > 0) {
            PostVO exist = postService.get(post.getId());
            Assert.notNull(exist, "词条不存在");
//            post.setAuthorId(exist.getAuthorId());
//			Assert.isTrue(exist.getAuthorId() == profile.getId(), "该文章不属于你");

            Log log = new Log();

            Post po= postService.findPostByTitle(post.getTitle());
            log.setAuthorId(po.getAuthorId());
            log.setChannelId(post.getChannelId());
            log.setEditorId(post.getAuthorId());
            log.setSummary(post.getContent());
            log.setStatus(0);
            log.setCreated(new Date());
            log.setTitle(post.getTitle());
            log.setId(post.getId());
            logRepository.save(log);
            postService.updateStatus(post.getId(),0);
//            postService.update(post);
        } else {
            postService.post(post);
        }
        return "ok";
    }

}

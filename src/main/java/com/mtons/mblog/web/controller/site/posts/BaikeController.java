package com.mtons.mblog.web.controller.site.posts;

import com.alibaba.fastjson.JSON;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.data.PostVO;
import com.mtons.mblog.modules.entity.*;
import com.mtons.mblog.modules.repository.LogRepository;
import com.mtons.mblog.modules.service.BaiKeService;
import com.mtons.mblog.modules.service.PostAttributeService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.web.controller.BaseController;
import com.mtons.mblog.web.controller.site.utils.HammingUtils;
import com.mtons.mblog.web.controller.site.utils.SimHashUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import com.mtons.mblog.modules.data.BaikeVO;
import com.mtons.mblog.modules.data.BasicInfoVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

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
    @Autowired
    private PostAttributeService postAttributeService;

    /**
     * 查看抽取信息
     * @return
     */

    @PostMapping("/baike")
    public BaikeVO getBaike(@RequestBody BaiKe baiKe) {
        try {
            String[] args1=new String[]{"D:/download-necessary/python3.8/python.exe","D:/pycharm-pro/baike-spider/spider_main.py",baiKe.getTitle()};
            Process pr=Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream(),"GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public Result post(@RequestBody PostVO post) {
        Result result =new Result();
//		System.out.println(post.getEditor());
        Assert.notNull(post, "参数不完整");
        Assert.state(StringUtils.isNotBlank(post.getTitle()), "标题不能为空");
        Assert.state(StringUtils.isNotBlank(post.getContent()), "内容不能为空");
        if (postService.findPostByTitle(post.getTitle()) != null && post.getId()<=0){
            String message= "已有词条"+post.getTitle()+"的信息，请勿重新创建!";
//            Assert.state(false,message );
            result.setMessage(message);
            result.setStatus(1);
            return  result;
        }

        List<Post> posts = new ArrayList<>();
        List<PostAttribute> postAttributes=postAttributeService.checkSummary();
        for(PostAttribute postAttribute : postAttributes){
//            Float point =DuplicateDetection.transferFloatToPersentString(DuplicateDetection.detect(postAttribute.getContent(),post.getContent()));
            double point = HammingUtils.getSimilarity(SimHashUtils.getSimHash(postAttribute.getContent()),SimHashUtils.getSimHash(post.getContent()));
            if(point>=0.8){
                 Post post1 = postService.get(postAttribute.getId());
                 Double score = Double.valueOf(String.format("%.2f",point*100));
                 post1.setScore(score);
                 posts.add(post1);
            }
        }
        if(!posts.isEmpty()){
            List<Post> list=posts.stream().sorted(Comparator.comparing(Post::getScore,Comparator.reverseOrder())).collect(Collectors.toList());
            result.setStatus(3);
            result.setMessage("以下词条的内容与您编辑的词条重复度较高，请选择是否合并词条！");
            result.setPost(list.get(0));
            return  result;
        }
        AccountProfile profile = getProfile();
        if (post.getId()<=0){
            post.setAuthorId(profile.getId());
        }
        // 修改时, 验证归属
        if (post.getId() > 0) {
            PostVO exist = postService.get(post.getId());
            Assert.notNull(exist, "词条不存在");
//            post.setAuthorId(exist.getAuthorId());
//			Assert.isTrue(exist.getAuthorId() == profile.getId(), "该文章不属于你");
            Log log = new Log();
            Post po= postService.get(post.getId());
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
//            String str0 = "北京字节跳动科技有限公司，简称字节跳动，是一家位于中国北京的跨国互联网技术公司[2]，成立于2012年3月，旗下有产品今日头条和抖音（及其海外版本TikTok）、西瓜视频等。\n";
//            String str1 = "北京字节跳动科技有限公司，简称字节跳动，是一家位于中国北京的跨国互联网技术公司[2]，成立于2012年3月，旗下有产品今日头条和抖音（及其海外版本TikTok）、西瓜视频等。\n";
//            // 由字符串得出对应的 simHash值
//            String simHash0 = SimHashUtils.getSimHash(str0);
//            String simHash1 = SimHashUtils.getSimHash(str1);
//            // 由 simHash值求出相似度
//            double similarity = HammingUtils.getSimilarity(simHash0, simHash1);
//            System.out.println(similarity);
            postService.post(post);
        }
        result.setStatus(0);
        return result;
    }

    @GetMapping("/check")
	public List<PostAttribute> getAttribute(){
		return postAttributeService.checkSummary();
	}

}

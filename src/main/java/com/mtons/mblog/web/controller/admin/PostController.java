/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2022, hongshan. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller.admin;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.data.PostVO;
import com.mtons.mblog.modules.entity.Log;
import com.mtons.mblog.modules.entity.Post;
import com.mtons.mblog.modules.entity.PostAttribute;
import com.mtons.mblog.modules.entity.View;
import com.mtons.mblog.modules.repository.PostAttributeRepository;
import com.mtons.mblog.modules.service.*;
import com.mtons.mblog.web.controller.BaseController;
import com.mtons.mblog.web.controller.site.utils.HammingUtils;
import com.mtons.mblog.web.controller.site.utils.SimHashUtils;
import javafx.geometry.Pos;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author langhsu
 *
 */
@Controller("adminPostController")
@RequestMapping("/admin/post")
public class PostController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private LogService logService;

	@Autowired
	private ViewService viewService;
	@Autowired
	private PostAttributeService postAttributeService;
	
	@RequestMapping("/list")
	public String list(String title, ModelMap model, HttpServletRequest request) {
		long id = ServletRequestUtils.getLongParameter(request, "id", Consts.ZERO);
		int channelId = ServletRequestUtils.getIntParameter(request, "channelId", Consts.ZERO);

		Pageable pageable = wrapPageable(Sort.by(Sort.Direction.DESC, "weight", "created"));
		Page<PostVO> page = postService.paging4Admin(pageable, channelId, title);
		model.put("page", page);
		List<View> list = viewService.findAuthorChannel();
		model.put("items",list);
		model.put("title", title);
		model.put("id", id);
		model.put("channelId", channelId);
		model.put("channels", channelService.findChannelSort());
		return "/admin/post/list";
	}
	
	/**
	 * 跳转到文章编辑方法
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String toUpdate(Long id, ModelMap model) {
		String editor = siteOptions.getValue("editor");
		if (null != id && id > 0) {
			PostVO view = postService.get(id);
			if (StringUtils.isNoneBlank(view.getEditor())) {
				editor = view.getEditor();
			}
			model.put("view", view);
		}
		model.put("editor", editor);
		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/view";
	}
	
	/**
	 * 更新文章方法
	 * @author LBB
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String subUpdate(PostVO post) {
		if (post != null) {
			if (post.getId() > 0) {
				postService.update(post);
			} else {
				AccountProfile profile = getProfile();
				post.setAuthorId(profile.getId());
				postService.post(post);
			}
		}
		return "redirect:/admin/post/list";
	}

	@RequestMapping("/featured")
	@ResponseBody
	public Result featured(Long id, HttpServletRequest request) {
		Result data = Result.failure("操作失败");
		int featured = ServletRequestUtils.getIntParameter(request, "featured", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postService.updateFeatured(id, featured);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}

	@RequestMapping("/weight")
	@ResponseBody
	public Result weight(Long id, HttpServletRequest request) {
		Result data = Result.failure("操作失败");
		int weight = ServletRequestUtils.getIntParameter(request, "weight", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postService.updateWeight(id, weight);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("id") List<Long> id) {
		Result data = Result.failure("操作失败");
		if (id != null) {
			try {
				postService.delete(id);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}

	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public String toAudit(Long hid, ModelMap model) {
		String editor = siteOptions.getValue("editor");
		if (null != hid && hid > 0) {
			Log audit = logService.findByIdRead(hid);
			model.put("audit", audit);
		}
//		model.put("editor", editor);
//		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/audit";
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String toInfo(Long hid, ModelMap model) {
		String editor = siteOptions.getValue("editor");
		if (null != hid && hid > 0) {
			Log audit = logService.findByIdRead(hid);
			model.put("info", audit);
		}
//		model.put("editor", editor);
//		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/info";
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String list1(String title, ModelMap model, HttpServletRequest request) {
		long id = ServletRequestUtils.getLongParameter(request, "id", Consts.ZERO);
		int channelId = ServletRequestUtils.getIntParameter(request, "channelId", Consts.ZERO);

		Pageable pageable = wrapPageable(Sort.by(Sort.Direction.DESC, "weight", "created"));
		Page<PostVO> page = postService.paging4Admin(pageable, channelId, title);
		model.put("page", page);
		List<View> list = viewService.findAuthorById(id);
		model.put("items",list);
		model.put("title", title);
		model.put("id", id);
		model.put("channelId", channelId);
		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/history";
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String Chceklist(String title, ModelMap model, HttpServletRequest request) {
		long id = ServletRequestUtils.getLongParameter(request, "id", Consts.ZERO);
		int channelId = ServletRequestUtils.getIntParameter(request, "channelId", Consts.ZERO);

		Pageable pageable = wrapPageable(Sort.by(Sort.Direction.DESC, "weight", "created"));
		Page<PostVO> page = postService.paging4Admin(pageable, channelId, title);
		model.put("page", page);
//		List<View> list = viewService.findAuthorById(id);
		List<Post> posts=new ArrayList<>();
		List<PostAttribute> postAttributes=postAttributeService.checkSummary();
		PostVO post = postService.get(id);
		for(PostAttribute postAttribute : postAttributes){
//            Float point =DuplicateDetection.transferFloatToPersentString(DuplicateDetection.detect(postAttribute.getContent(),post.getContent()));
			double point = HammingUtils.getSimilarity(SimHashUtils.getSimHash(post.getContent()),SimHashUtils.getSimHash(postAttribute.getContent()));
			if(point>=0.8){
				Post post1 = postService.get(postAttribute.getId());
				Double score = Double.valueOf(String.format("%.2f",point*100));
				post1.setScore(score);
				posts.add(post1);
			}
		}
		List<Post> list=posts.stream().sorted(Comparator.comparing(Post::getScore,Comparator.reverseOrder())).collect(Collectors.toList());
		model.put("items",list);
		model.put("title", title);
		model.put("id", id);
		model.put("channelId", channelId);
		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/check";
	}
}

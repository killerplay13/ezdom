package tw.com.cha102.forumcollect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;
import tw.com.cha102.forumcollect.service.ForumCollectService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/forum")
public class ForumCollectController {

	private ForumCollectService forumCollectService;

	@Autowired
	public ForumCollectController(ForumCollectService forumCollectService) {
		this.forumCollectService = forumCollectService;
	}

	@PostMapping("/collect")
	public ForumCollectVO collectPost(@RequestBody ForumCollectVO forumCollectVO) {
		ForumCollectVO vo = new ForumCollectVO();
		if (forumCollectService.collect(forumCollectVO)==true) {
				vo.setSuccessful(true);
				vo.setMessage("收藏成功");
			}else{
				vo.setSuccessful(false);
				vo.setMessage("您已經收藏過了");
			}
			return vo;
	}

	@GetMapping("/collect/list")
	public List<ForumCollectVO> listAllCollectedPosts() {
		return  forumCollectService.findAll();
	}

	@GetMapping("/collect/{collectId}")
	public ForumCollectVO getCollectedPostById(@PathVariable Integer collectId) {
		return forumCollectService.getCollectById(collectId);
	}

	@DeleteMapping("/delete/collect/{collectId}")
	public ForumCollectVO deleteCollectedPost(@PathVariable Integer collectId) {
		ForumCollectVO vo=new ForumCollectVO();
		if (forumCollectService.delete(collectId)==true) {
			vo.setSuccessful(true);
			vo.setMessage("刪除成功");
		}else{
			vo.setSuccessful(false);
			vo.setMessage("刪除失敗");
		}
		return vo;
	}



	@GetMapping("/collect/member/{memberId}")
	public List<ForumCollectVO> listCollectedPostsByMember(@PathVariable Integer memberId) {
		return forumCollectService.findByMemberId(memberId);

	}

}

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
	public ResponseEntity<String> collectPost(@RequestBody ForumCollectVO forumCollectVO) {
		ForumCollectVO result = forumCollectService.collect(forumCollectVO);
		if (result != null) {
			return ResponseEntity.ok("收藏成功");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("收藏失敗");
		}
	}

	@GetMapping("/collect/list")
	public ResponseEntity<List<ForumCollectVO>> listAllCollectedPosts() {
		List<ForumCollectVO> collectedPosts = forumCollectService.findAll();
		return ResponseEntity.ok(collectedPosts);
	}

	@GetMapping("/collect/{collectId}")
	public ResponseEntity<ForumCollectVO> getCollectedPostById(@PathVariable Integer collectId) {
		ForumCollectVO result = forumCollectService.getCollectById(collectId);
		if (result != null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/collect/{collectId}")
	public ResponseEntity<String> deleteCollectedPost(@PathVariable Integer collectId) {
		boolean deleted = forumCollectService.delete(collectId);
		if (deleted) {
			return ResponseEntity.ok("刪除收藏成功");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/collect/save")
	public ResponseEntity<String> saveCollectedPost(@RequestBody ForumCollectVO forumCollectVO) {
		boolean saved = forumCollectService.save(forumCollectVO);
		if (saved) {
			return ResponseEntity.ok("儲存收藏成功");
		} else {
			return ResponseEntity.status(500).body("儲存收藏失敗");
		}
	}

	@GetMapping("/collect/member/{memberId}")
	public ResponseEntity<List<ForumCollectVO>> listCollectedPostsByMember(@PathVariable Integer memberId) {
		List<ForumCollectVO> collectedPosts = forumCollectService.findByMemberId(memberId);
		return ResponseEntity.ok(collectedPosts);
	}

}

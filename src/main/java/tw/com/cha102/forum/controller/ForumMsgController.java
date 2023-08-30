package tw.com.cha102.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.model.entity.ForumMsgVO;
import tw.com.cha102.forum.service.ForumMsgService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/forum")
public class ForumMsgController {

	private ForumMsgService forumMsgService;

	@Autowired
	public ForumMsgController(ForumMsgService forumMsgService) {
		this.forumMsgService = forumMsgService;
	}

	@PostMapping("/msg")
	public ResponseEntity<String> createMessage(@RequestBody ForumMsgVO forumMsgVO) {
		ForumMsgVO result = forumMsgService.createMessage(forumMsgVO);
			return ResponseEntity.ok(result.getMessage());

	}

	@DeleteMapping("/delete/msg/{msgId}")
	public ResponseEntity<String> deleteMessage(@PathVariable Integer msgId) {
		boolean deleted = forumMsgService.deleteMessage(msgId);
		if (deleted) {
			return ResponseEntity.ok("刪除留言成功");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/msg/{msgId}")
	public ResponseEntity<ForumMsgVO> getMessageById(@PathVariable Integer msgId) {
		ForumMsgVO msg = forumMsgService.getMessageById(msgId);
		if (msg != null) {
			return ResponseEntity.ok(msg);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/msg1")
	public ResponseEntity<List<ForumMsgVO>> getAllMessages() {
		List<ForumMsgVO> messages = forumMsgService.getAllMessages();
		return ResponseEntity.ok(messages);
	}
}

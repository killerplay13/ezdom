package tw.com.cha102.forummsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forum.model.entity.ForumPostVO;
import tw.com.cha102.forummsg.model.entity.ForumMsgVO;
import tw.com.cha102.forummsg.service.ForumMsgService;

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
	public ForumMsgVO createMessage(@RequestBody ForumMsgVO forumMsgVO) {
		ForumMsgVO vo=new ForumMsgVO();
		if (forumMsgService.createMessage(forumMsgVO)==true) {
			vo.setSuccessful(true);
			vo.setMessage("留言成功");
		} else {
			vo.setSuccessful(false);
			vo.setMessage("留言失敗");
		}
		return vo;
	}

	@DeleteMapping("/delete/msg/{msgId}")
	public ForumMsgVO deleteMessage(@PathVariable Integer msgId) {
		ForumMsgVO vo=new ForumMsgVO();
		if (forumMsgService.deleteMessage(msgId)==true) {
			vo.setSuccessful(true);
			vo.setMessage("刪除成功");
		} else {
			vo.setSuccessful(false);
			vo.setMessage("刪除成功");
		}
		return vo;
	}

	@GetMapping("/msg/{msgId}")
	public ForumMsgVO getMessageById(@PathVariable Integer msgId) {
		return forumMsgService.getMessageById(msgId);
	}

	@GetMapping("/msg1")
	public List<ForumMsgVO> getAllMessages() {
		return  forumMsgService.getAllMessages();
	}

	@GetMapping("/msg/forumPost/{forumPostId}")
	public List<ForumMsgVO> getMessagesByForumPostId(@PathVariable Integer forumPostId) {
		return forumMsgService.getMessagesByForumPostId(forumPostId);
	}
}

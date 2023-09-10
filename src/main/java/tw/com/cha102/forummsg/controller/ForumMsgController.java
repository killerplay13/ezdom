package tw.com.cha102.forummsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.forummsg.model.entity.ForumMsgVO;
import tw.com.cha102.forummsg.service.ForumMsgService;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend/forum")
public class ForumMsgController {
	@Autowired
	private ForumMsgService forumMsgService;


	//進行文章留言
	@PostMapping("/msg")
	public ForumMsgVO createMessage(@RequestBody ForumMsgVO forumMsgVO, HttpSession session) {
		//Integer memberId = (Integer) session.getAttribute("memberId");//要注意型別
		Integer memberId = 3;
		forumMsgVO.setMemberId(memberId);
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


	//刪除文章留言
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


    //列出文章內的所有留言
	@GetMapping("/msg/forumPost/{forumPostId}")
	public List<ForumMsgVO> getMessagesByForumPostId(@PathVariable Integer forumPostId, HttpSession session) {
//		Integer currentUserId = (Integer) session.getAttribute("memberId");
		Integer memberId = 3;
		return forumMsgService.getMessagesByForumPostId(forumPostId);
	}


	//判斷目前登入的memberId
	@GetMapping("/current-member-id")
	public Integer getCurrentMemberId(HttpSession session) {
//		Integer memberId = (Integer) session.getAttribute("memberId");
		Integer memberId=3;
		return memberId;
	}
}

package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coachmember.model.entity.CoachMessageVO;
import tw.com.cha102.coachmember.model.dto.CoachMessage;
import tw.com.cha102.coachmember.model.dto.CoachDetails;
import tw.com.cha102.coachmember.model.dto.CoachList;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.coachmember.service.CoachService;
import tw.com.cha102.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/frontend")
public class FrontendCoachController {
    @Autowired
    private CoachService coachService;
    @Autowired
    private MemberService memberService;

    // ==================== 取得session ==================== //
    @GetMapping("/session")
    public CoachMemberVO session(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        Integer coachId = (Integer)session.getAttribute("coachId");
        CoachMemberVO coachMember = new CoachMemberVO();
        if(coachId != null){
            coachMember.setCoachId(coachId);
        }
        coachMember.setMemberId(memberId);
        return coachMember;
    }

    // ==================== 教練註冊 ==================== //
    @PostMapping("/coach/register")
    public CoachMemberVO register(@Valid @RequestBody CoachMemberVO registerCoachMember){
        return coachService.register(registerCoachMember);
    }

    // ==================== 教練登入 ==================== //
    @GetMapping("/coach/login/{memberId}")
    public CoachMemberVO coachStatus(@PathVariable Integer memberId){
        return coachService.getCoachStatus(memberId);
    }

    // ==================== 瀏覽教練列表 ==================== //
    @GetMapping("/browse/list")
    public List<CoachList> browseCoachList(){
        return coachService.getCoachList();
    }

    // ==================== 瀏覽教練個人頁面 ==================== //
    @GetMapping("/browse/list/{coachId}")
    public CoachDetails browseCoach(@PathVariable Integer coachId){
        return coachService.getCoachDetails(coachId);
    }

    // ==================== 教練修改個人資訊 ==================== // !!!!!
    @PutMapping("/browse/list/update/{coachId}")
    public CoachMemberVO setDetails(@PathVariable Integer coachId,
                                    @RequestBody CoachMemberVO updateDetails){
        return coachService.updateDetails(coachId, updateDetails);
    }

    // ==================== 瀏覽教練留言板 ==================== //
    @GetMapping("/coach/message/{coachId}")
    public List<CoachMessage> coachMessageList(@PathVariable Integer coachId){
        return coachService.getCoachMessage(coachId);
    }

    // ==================== 新增留言 ==================== //
    @PostMapping("/coach/message/add")
    public CoachMessageVO add(@Valid @RequestBody CoachMessageVO coachMessageVO){
        return coachService.addMessage(coachMessageVO);
    }

    // ==================== 刪除留言 ==================== //
    @DeleteMapping("/coach/message/delete/{messageId}")
    public String delete(@Valid @PathVariable Integer messageId){
        return coachService.deleteMessage(messageId);
    }

    // ==================== 修改留言 ==================== //
    @PutMapping("/coach/message/update/{messageId}")
    public CoachMessageVO update(@PathVariable Integer messageId,
                                 @RequestBody String content){
        return coachService.updateMessage(messageId, content);
    }
}

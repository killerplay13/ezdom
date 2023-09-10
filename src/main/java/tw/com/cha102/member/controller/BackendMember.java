package tw.com.cha102.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.dto.MemberStateResponse;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/beckend/member")
@CrossOrigin("*")
public class BackendMember {

    @Autowired
    MemberService memberService;

    @GetMapping("/memberState")
    public List<Member> getMemberState(){
        List<Member> memberState = memberService.getMemberState();
        return  memberState;
    }

    @PutMapping("/status")
    public Member updateMemberStatus(HttpServletRequest request ){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        Member member=new Member();
        if(memberService.updateMemberStatus(memberId)){
            member.setMessage("修改成功");
            member.setSuccessful(true);
        }else{
            member.setMessage("修改失敗");
            member.setSuccessful(false);
        }
        return  member;
    }
}


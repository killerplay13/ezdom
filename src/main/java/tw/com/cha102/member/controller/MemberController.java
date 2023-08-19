package tw.com.cha102.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    //todo 返回類型可全組固定json
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        memberService.login(loginRequest);
        return  ResponseEntity.ok("登入成功");
    }
    @PostMapping("/signUp")
    public  ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest){
        memberService.signUp(signUpRequest);
        return  ResponseEntity.ok("註冊成功");
    }

    @GetMapping("/member")
    public List<Member> getMembers(){
        return memberService.getMembers();
    }
}

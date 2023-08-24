package tw.com.cha102.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest){
        memberService.login(loginRequest);
        return  ResponseEntity.ok("登入成功");
    }
    @PostMapping("/signUp")
    public  ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        memberService.signUp(signUpRequest);
        return  ResponseEntity.ok("註冊成功");
    }

    @GetMapping("/member")
    public List<Member> getMembers(){
        return memberService.getMembers();
    }
}

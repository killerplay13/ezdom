package tw.com.cha102.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.dto.MemberStateResponse;
import tw.com.cha102.member.service.MemberService;

@RestController
@RequestMapping("/beckend/member")
@CrossOrigin("*")
public class BackendMember {

    @Autowired
    MemberService memberService;

    @GetMapping("/memberState")
    public ResponseEntity<MemberStateResponse> getMemberState(@RequestParam("memberAccount") String memberAccount){
        MemberStateResponse response = memberService.getMemberState(memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


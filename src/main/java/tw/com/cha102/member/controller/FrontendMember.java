package tw.com.cha102.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import tw.com.cha102.core.service.MailService;
import tw.com.cha102.member.dto.*;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class FrontendMember {
    @Autowired
    private MemberService memberService;


    @PostMapping("/signUp")
    public CommonResponse<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return new CommonResponse<>("註冊成功");
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody @Valid LoginRequest loginRequest,
                                        HttpServletRequest request,
                                        HttpServletResponse response
    ) {
        memberService.login(loginRequest, request, response);
        return new CommonResponse<>("登入成功");
    }

    @PostMapping("/checkEmailAccount")
    public AccountEmailResponse checkEmailAccount(@RequestBody @Valid CheckEmailAccountRequest checkEmailAccountRequest,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response
    ) {
        return memberService.checkEmailAccount(checkEmailAccountRequest, request, response);
    }

    @PostMapping("/sendAuthenticationCode")
    public CommonResponse<String> sendAuthenticationCode(@RequestBody @Valid CheckEmailAccountRequest checkEmailAccountRequest, HttpServletRequest request) {
        memberService.sendAuthenticationCode(checkEmailAccountRequest, request); // 調用 sendCheckCode 方法發送
        return new CommonResponse<>("傳送成功");
    }

    @PostMapping("/checkAuthCode")
    public CommonResponse<String> checkAuthCode(@RequestParam String authCode, HttpSession httpSession, HttpServletResponse response) {
        memberService.checkAuthCode(authCode, httpSession, response);
        return new CommonResponse<>("驗證成功");
    }

    @PostMapping("/resetPassword")
    public CommonResponse<String> resetPassword(@RequestParam String newPassword, HttpServletRequest request, HttpServletResponse response) {
        memberService.resetPassword(newPassword, request, response);
        return new CommonResponse<>("密碼更新成功");
    }

    @GetMapping("/logout")
    public Member logout(HttpServletRequest request, HttpServletResponse response) {
        // 清除會話數據
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Member member = new Member();
        member.setSuccessful(true);

        return member;
    }


    @PostMapping("/uploadPhoto")
    public CommonResponse<String> uploadPhoto(@RequestBody UploadPhotoRequest uploadPhotoRequest,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 處理上傳的圖片，將 photo 儲存到你的資料庫
        memberService.uploadPhoto(uploadPhotoRequest, request, response);
        return new CommonResponse<>("圖片上傳成功");
    }

    @GetMapping("/getPhoto")
    public ResponseEntity<MemberPhotoResponse> getMemberPhoto(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer) session.getAttribute("memberId");
        MemberPhotoResponse response = memberService.getMebmerPhoto(memberId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/profile")
    public CommonResponse<String> uploadProfile(@RequestBody ProfileRequest profileRequest,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        memberService.uploadProfile(profileRequest, request, response);
        return new CommonResponse<>("個資上傳成功");

    }

    @GetMapping("/getProfile")
    public ResponseEntity<MemberProfileResponse> getMemberProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer) session.getAttribute("memberId");
        MemberProfileResponse response = memberService.getMemberProfile(memberId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/modifyPw")
    public CommonResponse<String> modifyPw(@RequestBody String modifyPw, HttpServletRequest request) {
        memberService.modifyPw(modifyPw, request);
        return new CommonResponse<>("密碼更改成功");
    }

    @GetMapping("/member")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping
    public ResponseEntity<Member> getMember(HttpSession session) {
        // TODO: 用來獲取已登入的member訊息
        Integer memberId = (Integer) session.getAttribute("memberId");


        //目前預設為1
        //Integer memberId = 1;
        Member member = memberService.findById(memberId);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }


}

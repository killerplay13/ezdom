package tw.com.cha102.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
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
        return new CommonResponse("註冊成功");
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody @Valid LoginRequest loginRequest,
                                        HttpServletRequest request,
                                        HttpServletResponse response
    ) {
        memberService.login(loginRequest, request, response);
        return new CommonResponse("登入成功");
    }

    @PostMapping("/checkEmailPassword")
    public AccountEmailResponse checkEmailPassword(@RequestBody @Valid CheckEmailPasswordRequest checkEmailPasswordRequest,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response
                                                     ){
       return memberService.checkEmailPassword(checkEmailPasswordRequest, request, response);
    }

    @PostMapping("/sendCheckCode")
    public CommonResponse<String> sendCheckCode(@RequestParam String memberEmail){
        return new CommonResponse("傳送成功");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        // 清除會話數據
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 重導到登入頁面
        return new RedirectView("/frontendmember/account-signin.html");
    }


    @PostMapping("/uploadPhoto")
    public CommonResponse<String> uploadPhoto(@RequestBody UploadPhotoRequest uploadPhotoRequest,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 處理上傳的圖片，將 photo 儲存到你的資料庫
        memberService.uploadPhoto(uploadPhotoRequest, request, response);
        return new CommonResponse("圖片上傳成功");
    }

    @GetMapping("/getPhoto")
    public ResponseEntity<MemberPhotoResponse> getMemberPhoto(@RequestParam("memberAccount") String memberAccount){
        MemberPhotoResponse response = memberService.getMebmerPhoto(memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/profile")
    public CommonResponse<String> uploadProfile(@RequestBody ProfileRequest profileRequest,
                                                HttpServletRequest request,
                                                HttpServletResponse response)
    {
        memberService.uploadProfile(profileRequest, request, response);
        return new CommonResponse("個資上傳成功");

    }

    @GetMapping("/getProfile")
    public ResponseEntity<MemberProfileResponse> getMemberProfile(@RequestParam("memberAccount") String memberAccount){
        MemberProfileResponse response = memberService.getMemberProfile(memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/member")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }


}

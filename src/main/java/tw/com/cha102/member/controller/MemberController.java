package tw.com.cha102.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.com.cha102.member.dto.CommonResponse;
import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.dto.UploadPhotoRequest;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 清除會話數據
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 可選：重定向到登出成功頁面或登入頁面
        return "redirect:/login"; // 或其他適當的重定向
    }

    @PostMapping("/uploadPhoto")
    public CommonResponse<String> uploadPhoto(@RequestParam("uploadPhotoRequest") UploadPhotoRequest uploadPhotoRequest,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 處理上傳的圖片，將 photo 儲存到你的資料庫
        memberService.uploadPhoto(uploadPhotoRequest, request, response);
        return new CommonResponse("圖片上傳成功");
    }

    @GetMapping("/member")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping //我的揪團 映射會員資料需要
    public ResponseEntity<Member> getMember(HttpSession session){
        // TODO: 用來獲取已登入的member訊息
        // Member member = (Member) session.getAttribute("member");
        // Integer memberId = member.getMemberId();

        //目前預設為1
        Integer memberId = 1;
        Member member = memberService.findById(memberId);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}

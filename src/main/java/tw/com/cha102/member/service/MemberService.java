package tw.com.cha102.member.service;

import tw.com.cha102.member.dto.*;
import tw.com.cha102.member.model.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface MemberService {
    void login(LoginRequest loginRequest, HttpServletRequest request,HttpServletResponse response);
    void signUp(SignUpRequest signUpRequest);
    void uploadProfile(ProfileRequest profileRequest, HttpServletRequest request, HttpServletResponse response);
    void uploadPhoto(UploadPhotoRequest uploadPhotoRequest, HttpServletRequest request, HttpServletResponse response);

    void sendAuthenticationCode(CheckEmailPasswordRequest checkEmailPasswordRequest, HttpServletRequest request);

    CommonResponse<String> checkAuthCode(String authCode, HttpSession httpSession);

    AccountEmailResponse checkEmailPassword(CheckEmailPasswordRequest checkEmailPasswordRequest, HttpServletRequest request, HttpServletResponse response);

    List<Member> getMembers();




    MemberProfileResponse getMemberProfile(String memberAccount);

    MemberPhotoResponse getMebmerPhoto(String memberAccount);

    MemberStateResponse getMemberState(String memberAccount);


    Member findById(Integer memberId);

}

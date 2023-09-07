package tw.com.cha102.member.service;

import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.dto.UploadPhotoRequest;
import tw.com.cha102.member.model.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface MemberService {
    void  login(LoginRequest loginRequest , HttpServletRequest request,HttpServletResponse response);
    void signUp(SignUpRequest signUpRequest);
    void uploadPhoto(UploadPhotoRequest uploadPhotoRequest, HttpServletRequest request, HttpServletResponse response);
    List<Member> getMembers();

    Member findById(Integer memberId);//我的揪團 映射會員資料需要
}

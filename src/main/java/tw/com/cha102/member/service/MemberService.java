package tw.com.cha102.member.service;

import org.springframework.stereotype.Service;
import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.model.entity.Member;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface MemberService {
    void  login(LoginRequest loginRequest , HttpServletResponse response);
    void signUp(SignUpRequest signUpRequest);
    List<Member> getMembers();


}

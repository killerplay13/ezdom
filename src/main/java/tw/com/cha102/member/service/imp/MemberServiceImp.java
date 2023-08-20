package tw.com.cha102.member.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.model.dao.MemberRepository;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void login(LoginRequest loginRequest) {

    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        Member member=memberRepository.findByMemberAccount(signUpRequest.getAccount());
        if(member!=null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"已有使用者註冊");


        Member newMem =new Member();
        newMem.setMemberAccount(signUpRequest.getAccount());
        String hashPwd=sha256Hash(signUpRequest.getPassword());//把傳遞的密碼加密
        newMem.setMemberPassword(hashPwd);
        newMem.setMemberName(signUpRequest.getName());
        newMem.setMemberAddress(signUpRequest.getAddress());
        newMem.setMemberPhone(signUpRequest.getPhone());
        newMem.setMemberEmail(signUpRequest.getEmail());
        newMem.setMemberUid(signUpRequest.getUid());
        memberRepository.save(newMem);

    }


    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
    /**
     * sha 256 加密
     * */
    public String sha256Hash(String data){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String sha256Hash = hexString.toString();
            return sha256Hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"密碼格式異常");
    }
}

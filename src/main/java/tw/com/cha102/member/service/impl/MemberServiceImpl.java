package tw.com.cha102.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tw.com.cha102.member.dto.LoginRequest;
import tw.com.cha102.member.dto.SignUpRequest;
import tw.com.cha102.member.dto.UploadPhotoRequest;
import tw.com.cha102.member.model.dao.MemberRepository;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        Member member = memberRepository.findByMemberAccount(loginRequest.getAccount());
        if(member ==null)
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"無此使用者");
        String hashReqPwd=sha256Hash(loginRequest.getPassword()) ;//傳入密碼加密
        //比較帳號密碼
        if(!member.getMemberAccount().equals(loginRequest.getAccount()) || !member.getMemberPassword().equals(loginRequest.getPassword()))
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"帳號密碼錯誤");
        HttpSession httpSession  =request.getSession();
//        httpSession.setAttribute("loggedInMember", member.getMemberId());
        // 添加 Cookie 到回應中
        Cookie sessionCookie = new Cookie("JSESSIONID", httpSession.getId());
        sessionCookie.setMaxAge(30 * 60); // 30 分鐘的過期時間
        sessionCookie.setPath("/"); // 設置 Cookie 的路徑
        response.addCookie(sessionCookie);
        httpSession.setAttribute("memberId", member.getMemberId()); // 保存目前登入的會員id，供後續使用

    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        Member member = memberRepository.findByMemberAccount(signUpRequest.getAccount());
        if (member != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "已有使用者註冊");


        Member newMem = new Member();
        newMem.setMemberAccount(signUpRequest.getAccount());
        String hashPwd = sha256Hash(signUpRequest.getPassword());//把傳遞的密碼加密
        newMem.setMemberPassword(hashPwd);
        newMem.setMemberEmail(signUpRequest.getEmail());
        memberRepository.save(newMem);
    }


    @Override
    public void uploadPhoto(UploadPhotoRequest uploadPhotoRequest, HttpServletRequest request, HttpServletResponse response) {
        // 獲取會員的 ID，從 session 中獲取
        HttpSession httpSession = request.getSession();
        Integer memberId = (Integer) httpSession.getAttribute("memberId");

        // 根據會員的 ID 從資料庫中獲取會員實體
        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到該會員");
        }

        // 將 Base64 編碼的圖片資料轉換為 byte 陣列
        byte[] decodedPhoto = Base64.getDecoder().decode(uploadPhotoRequest.getMemberPhoto());

        // 將解碼後的照片數據設置到會員實體的 memberPhoto 屬性中
        member.setMemberPhoto(decodedPhoto);

        // 更新會員實體到資料庫中
        memberRepository.save(member);
    }

    public void rewardPointForLogin(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            member.setPoint(member.getPoint() + 1); // 每次登入給予一點
            memberRepository.save(member);
        }
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override //我的揪團 映射會員資料需要
    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId).get();
    }

    /**
     * sha 256 加密
     */
    public String sha256Hash(String data) {
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
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼格式異常");
    }
}

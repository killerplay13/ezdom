package tw.com.cha102.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ResponseStatusException;
import tw.com.cha102.coachmember.model.dao.CoachMemberRepository;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.member.dto.*;
import tw.com.cha102.member.model.dao.MemberRepository;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.member.service.MemberService;
import tw.com.cha102.core.service.MailService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CoachMemberRepository coachMemberRepository;


    @Override
    public void signUp(SignUpRequest signUpRequest) {
        Member member = memberRepository.findByMemberAccount(signUpRequest.getAccount());
        if (member != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "已有使用者註冊");


        Member newMem = new Member();
        newMem.setMemberName(signUpRequest.getName());
        newMem.setMemberAccount(signUpRequest.getAccount());
        String hashPwd = sha256Hash(signUpRequest.getPassword());//把傳遞的密碼加密
        newMem.setMemberPassword(hashPwd);
        newMem.setMemberEmail(signUpRequest.getEmail());
        memberRepository.save(newMem);
    }

    @Override
    public void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        // 從資料庫中查找使用者
        Member member = memberRepository.findByMemberAccount(loginRequest.getAccount());
        if (member == null) {
            // 如果使用者不存在，返回 400 Bad Request
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "無此使用者");
        }
        else if(member.getMemberStatus() == 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "使用者帳號被停權");
        }

        // 對使用者提供的密碼進行雜湊處理，然後與資料庫中儲存的雜湊密碼進行比較
        String hashReqPwd = sha256Hash(loginRequest.getPassword());
        if (!member.getMemberPassword().equals(hashReqPwd)) {
            // 如果密碼不匹配，返回 400 Bad Request
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號密碼錯誤");
        }
        Integer memberId = member.getMemberId();
        CoachMemberVO coachMemberVO = coachMemberRepository.findByMemberId(memberId);


        // 獲取 HttpSession
        HttpSession httpSession = request.getSession();

        // 添加 Cookie 到回應中，以維護會話
        Cookie sessionCookie = new Cookie("ROYEZDOM", httpSession.getId());
        sessionCookie.setMaxAge(60 * 60); // 60 分鐘的過期時間
        sessionCookie.setPath("/"); // 設置 Cookie 的路徑
        response.addCookie(sessionCookie);

        if(coachMemberVO!=null && coachMemberVO.getStatus() == 2){
            httpSession.setAttribute("coachId", coachMemberVO.getCoachId());
        }

        // 將使用者的會員 ID 儲存在會話中，以供後續使用
        httpSession.setAttribute("memberId", member.getMemberId());
    }


    @Override
    public AccountEmailResponse checkEmailAccount(CheckEmailAccountRequest checkEmailAccountRequest, HttpServletRequest request, HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        //  memberRepository 可以根據帳號來查找會員
        Member member = memberRepository.findByMemberAccount(checkEmailAccountRequest.getAccount());


        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "無此使用者");
        }

        if (!member.getMemberAccount().equals(checkEmailAccountRequest.getAccount()) || !member.getMemberEmail().equals(checkEmailAccountRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號信箱錯誤");
        }

        // memberId儲存到 Session 中
        httpSession.setAttribute("memberId", member.getMemberId());

        // 創建並返回包含 account 和 email 的 Response 對象
        AccountEmailResponse accountEmailResponse = new AccountEmailResponse();
        accountEmailResponse.setAccount(member.getMemberAccount());
        accountEmailResponse.setEmail(member.getMemberEmail());

        return accountEmailResponse;
    }

    public void sendAuthenticationCode(@Valid CheckEmailAccountRequest checkEmailAccountRequest, HttpServletRequest request) {
        // 從 HttpSession 中獲取帳號
        HttpSession httpSession = request.getSession();
        Integer memberId = (Integer) httpSession.getAttribute("memberId");

        // 根據會員帳號查詢會員信息
        Member member = memberRepository.findByMemberId(memberId);


        if (member != null) {
            // 生成驗證碼
            String verificationCode = generateVerificationCode();

            // 郵件主題
            String subject = "驗證碼";

            // 郵件內容，包含驗證碼
            String messageText = "您的驗證碼是：" + verificationCode;

            MailService mailService = new MailService();

            // 發送郵件，使用 mailService 的 sendMail 方法
            mailService.sendMail(checkEmailAccountRequest.getEmail(), subject, messageText);

            // 將驗證碼存入Session
            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode);
            System.out.println(verificationCode);

        } else {
            // 如果未找到會員信息，您可以處理相應的錯誤或返回提示
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "未找到相應的會員信息。");
        }
    }


    @Override
    public CommonResponse<String> checkAuthCode(String authCode, HttpSession httpSession,HttpServletResponse response) {
        // 從 HttpSession 中獲取後端存储的驗證碼
        String verificationCode = (String) httpSession.getAttribute("verificationCode");

        // 比較前端的驗證碼和後端儲存的驗證碼
        if (verificationCode != null && verificationCode.equals(authCode)) {
            // 驗證碼匹配
            return new CommonResponse<>("驗證碼匹配");
        } else {
            // 驗證碼不匹配
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new CommonResponse<>("驗證碼不匹配。");
        }
    }

    private String generateVerificationCode() {

        int length = 8; // 驗證碼長度
        StringBuilder authCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // 0 ~ 61 的隨機數字
            int randIndex = (int) (Math.random() * 62);
            char randChar;
            if (randIndex < 26) {
                // 英文大寫字母
                randChar = (char) ('A' + randIndex);
            } else if (randIndex < 52) {
                // 英文小寫字母
                randChar = (char) ('a' + randIndex - 26);
            } else {
                // 數字
                randChar = (char) ('0' + randIndex - 52);
            }
            authCode.append(randChar);
        }
        return authCode.toString();
    }

    @Override
    public void resetPassword(String newPassword, HttpServletRequest request, HttpServletResponse response) {
        // 從 HttpSession 中獲取帳號
        HttpSession httpSession = request.getSession();
        Integer memberId = (Integer) httpSession.getAttribute("memberId");

        // 根據帳號查詢會員信息
        Member member = memberRepository.findByMemberId(memberId);

        if (member != null) {
            // 如果找到會員，將新密碼加密並設置為會員的密碼
            String hashPwd = sha256Hash(newPassword); // 假設有一個 sha256Hash 方法用於密碼加密
            member.setMemberPassword(hashPwd);

            // 保存更新後的會員信息
            memberRepository.save(member);
        } else {
            // 如果找不到會員，你可以根據情況執行錯誤處理邏輯
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "未找到相應的會員信息。");
        }
    }

    @Override
    public void modifyPw(String modifyPw, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        Integer memberId = (Integer) httpSession.getAttribute("memberId");

        // 根据會員的帳戶從數據庫中獲取會員實體
        Member member = memberRepository.findByMemberId(memberId);

        if (member != null) {
            // 如果找到會員，將新密碼加密並設置為會員的密碼
            String hashPwd = sha256Hash(modifyPw); // sha256Hash 密碼加密
            member.setMemberPassword(hashPwd);

            // 郵件主題
            String subject = "會員更改密碼";

            // 郵件內容
            String messageText = "親愛的用戶，\n\n您的密碼已成功更改。\n\n感謝您的使用！";

            MailService mailService = new MailService();

            // 發送郵件，使用 mailService 的 sendMail 方法
            mailService.sendMail(member.getMemberEmail(), subject, messageText);

            // 保存更新後的會員信息
            memberRepository.save(member);

        } else {
            // 如果找不到會員，你可以根據情況執行錯誤處理邏輯
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "未找到相應的會員信息。");
        }

    }

    public void uploadProfile(ProfileRequest profileRequest, HttpServletRequest request, HttpServletResponse response) {
        // 從會話中獲取會員的帳戶
        HttpSession httpSession = request.getSession();
        Integer memberId = (Integer) httpSession.getAttribute("memberId");

        // 根据會員的帳戶從數據庫中獲取會員實體
        Member member = memberRepository.findByMemberId(memberId);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到该會員");
        }

        // 更新會員信息
        if (profileRequest.getName() != null) {
            member.setMemberName(profileRequest.getName());
        }

        if (profileRequest.getUid() != null) {
            member.setMemberUid(profileRequest.getUid());
        }

        if (profileRequest.getEmail() != null) {
            member.setMemberEmail(profileRequest.getEmail());
        }

        if (profileRequest.getPhone() != null) {
            member.setMemberPhone(profileRequest.getPhone());
        }

        if (profileRequest.getBirth() != null) {
            member.setMemberBirthday(profileRequest.getBirth());
        }

        if (profileRequest.getGender() != null) {
            member.setMemberGender(profileRequest.getGender());
        }

        if (profileRequest.getIntroduction() != null) {
            member.setIntroduction(profileRequest.getIntroduction());
        }

        if (profileRequest.getAddress() != null) {
            member.setMemberAddress(profileRequest.getAddress());
        }


        // 保存會員信息到資料庫
        memberRepository.save(member);
    }

    public MemberProfileResponse getMemberProfile(Integer memberId) {

        Member member = memberRepository.findByMemberId(memberId);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到該會員");
        }

        MemberProfileResponse response = new MemberProfileResponse();
        response.setName(member.getMemberName());
        response.setMemberUid(member.getMemberUid());
        response.setEmail(member.getMemberEmail());
        response.setPhone(member.getMemberPhone());
        response.setGender(member.getMemberGender());
        response.setBirth(member.getMemberBirthday());
        response.setIntroduction(member.getIntroduction());
        response.setPoint(member.getPoint());
        response.setAddress(member.getMemberAddress());

        return response;
    }

    @Override
    public void uploadPhoto(UploadPhotoRequest uploadPhotoRequest, HttpServletRequest request, HttpServletResponse response) {
        // 獲取會員的 ID，從 session 中獲取
        HttpSession httpSession = request.getSession();
        Integer memberId = (Integer) httpSession.getAttribute("memberId");


        // 根據會員的 ID 從資料庫中獲取會員實體
        Member member = memberRepository.findByMemberId(memberId);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到該會員");
        }

        // 從 UploadPhotoRequest 中獲取 Base64 編碼的圖片資料
        String base64Image = uploadPhotoRequest.getMemberPhoto();

        try {
            // 解碼 Base64 字符串為位元組數據
            byte[] decodedPhoto = Base64.getDecoder().decode(base64Image);

            // 將解碼後的照片數據設置到會員實體的 memberPhoto 屬性中
            member.setMemberPhoto(decodedPhoto);

            // 更新會員實體到資料庫中
            memberRepository.save(member);
        } catch (IllegalArgumentException e) {
            // 如果解碼失敗，處理異常情況
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "圖片解碼失敗：" + e.getMessage());
        }
    }


    @Override
    public MemberPhotoResponse getMebmerPhoto(Integer memberId) {

        Member member = memberRepository.findByMemberId(memberId);

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到該會員");
        }

        // 假設 Member 類別中有一個 memberPhoto 成員變數代表會員的照片
        byte[] memberPhoto = member.getMemberPhoto();

        // 使用Base64編碼將位元組陣列轉換為Base64字串
        String base64Photo = Base64Utils.encodeToString(memberPhoto);


        // 創建一個 MemberPhotoResponse 物件並設定照片資料
        MemberPhotoResponse response = new MemberPhotoResponse();
        response.setPhotoToBase64(base64Photo);

        return response;

    }


    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
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

    @Override
    public List<Member> getMemberState() {

        List<Member> member = memberRepository.findAll();

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到該會員");
        }

        return member;
    }

    @Override
    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Override
    @Transactional
    public boolean updateMemberStatus(Integer memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Byte memberStatus = member.getMemberStatus();
        if(memberStatus == 0){
            memberStatus =1;
        }else if(memberStatus ==1 ){
            memberStatus =0;
        }


        return memberRepository.updateMemberStatus(memberStatus,memberId)>0;
    }


}

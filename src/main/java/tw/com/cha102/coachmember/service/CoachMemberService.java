package tw.com.cha102.coachmember.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.coachmember.model.dao.CoachMemberRepository;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.member.model.dao.MemberRepository;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;
import java.util.Optional;

@Service
public class CoachMemberService {

    @Autowired
    private CoachMemberRepository coachMemberRepository;

    @Autowired
    private MemberRepository memberRepository;

    // ==================== 教練註冊 ==================== //
    public CoachMemberVO register(CoachMemberVO registerCoachMember){
        // 檢查此會員ID是否存在
        Optional<Member> checkMember = memberRepository.findById(registerCoachMember.getMemberId());
        // 檢查是否已註冊過教練
        CoachMemberVO checkCoachMember = coachMemberRepository.findByMemberId(registerCoachMember.getMemberId());

        // 如果會員ID不存在，需先註冊會員
        if(!checkMember.isPresent()){
            CoachMemberVO coachMember = new CoachMemberVO();
            coachMember.setMessage("尚未成為會員，請先完成會員註冊");
            coachMember.setSuccessful(false);
            return coachMember;
        }

        // 如果已存在教練資訊，則不能重複註冊
        if(checkCoachMember != null){
            checkCoachMember.setMessage("此會員ID已註冊為教練，不能重複註冊");
            checkCoachMember.setSuccessful(false);
            return checkCoachMember;
        }

        registerCoachMember = coachMemberRepository.save(registerCoachMember);
        registerCoachMember.setMessage("註冊成功");
        registerCoachMember.setSuccessful(true);
        return registerCoachMember;
    }

    // ==================== 檢查教練身分 ==================== //
    public CoachMemberVO getCoachStatus(Integer memberId){

        CoachMemberVO coachMemberVO = coachMemberRepository.findByMemberId(memberId);

        if(coachMemberVO == null){
            CoachMemberVO coachMember = new CoachMemberVO();
            coachMember.setMessage("此會員不為教練會員");
            coachMember.setSuccessful(false);
            return coachMember;
        }

        if(coachMemberVO != null && coachMemberVO.getStatus() == 1){
            coachMemberVO.setMessage("此會員已申請成為教練，待審核中");
            coachMemberVO.setSuccessful(false);
            return coachMemberVO;
        }

        if(coachMemberVO != null && coachMemberVO.getStatus() == 0){
            coachMemberVO.setMessage("此會員的教練身分，已遭到停權");
            coachMemberVO.setSuccessful(false);
            return coachMemberVO;
        }

        coachMemberVO.setMessage("此會員為教練會員");
        coachMemberVO.setSuccessful(true);
        return coachMemberVO;
    }

    // ==================== 教練審核 ==================== //

    // 查詢教練列表
    public List<CoachMemberVO> getCoachMemberList(){
        return coachMemberRepository.findAll();
    }

    // 查詢教練資訊
    public CoachMemberVO getCoachMember(Integer coachId){
        Optional<CoachMemberVO> check = coachMemberRepository.findById(coachId);

        if(check.isPresent()){
            CoachMemberVO coachMember = check.get();
            coachMember.setMessage("顯示該教練的資訊");
            coachMember.setSuccessful(true);
            return coachMember;
        }else {
            CoachMemberVO coachMemberVO = new CoachMemberVO();
            coachMemberVO.setMessage("找不到此教練的資訊");
            coachMemberVO.setSuccessful(false);
            return coachMemberVO;
        }
    }

    // 修改教練狀態
    public CoachMemberVO updateStatus(Integer coachId, CoachMemberVO updateCoachMember){
        Optional<CoachMemberVO> check = coachMemberRepository.findById(coachId);

        if(check.isPresent()){
            CoachMemberVO coachMember = check.get();

            switch (updateCoachMember.getStatus()){
                case 0:
                    coachMember.setStatus((byte) 0);
                    coachMember.setMessage("已將該名教練會員停權!");
                    coachMember.setSuccessful(true);
                    coachMemberRepository.save(coachMember);
                    break;
                case 2:
                    coachMember.setStatus((byte) 2);
                    coachMember.setMessage("已啟用該名教練會員資格!");
                    coachMember.setSuccessful(true);
                    coachMemberRepository.save(coachMember);
                    break;
                case 3:
                    coachMember.setStatus((byte) 3);
                    coachMember.setMessage("已取消該名教練會員的申請!");
                    coachMember.setSuccessful(true);
                    coachMemberRepository.save(coachMember);
                    break;
            }
            return coachMember;
        }else {
            CoachMemberVO coachMemberVO = new CoachMemberVO();
            coachMemberVO.setMessage("此申請已不存在");
            coachMemberVO.setSuccessful(false);
            return coachMemberVO;
        }
    }

    // ==================== 教練修改個人資訊 ==================== //

    // 修改自我介紹
    public CoachMemberVO updateDetails(Integer coachId, CoachMemberVO updateDetails){
        Optional<CoachMemberVO> check = coachMemberRepository.findById(coachId);

        if(check.isPresent()){
            CoachMemberVO coachMember = check.get();
            coachMember.setIntroduction(updateDetails.getIntroduction());
            coachMember.setMessage("修改資訊成功");
            coachMember.setSuccessful(true);
            coachMemberRepository.save(coachMember);
            return coachMember;
        }else {
            CoachMemberVO coachMemberVO = new CoachMemberVO();
            coachMemberVO.setMessage("修改資訊失敗");
            coachMemberVO.setSuccessful(false);
            return coachMemberVO;
        }
    }

}

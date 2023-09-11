package tw.com.cha102.coachmember.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tw.com.cha102.coachmember.model.entity.CoachMessageVO;
import tw.com.cha102.coachmember.model.dao.CoachMessageRepository;
import tw.com.cha102.coachmember.model.dto.CoachMessage;
import tw.com.cha102.coachmember.model.dto.CoachDetails;
import tw.com.cha102.coachmember.model.dto.CoachList;
import tw.com.cha102.coachmember.model.dao.CoachMemberRepository;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;
import tw.com.cha102.member.model.dao.MemberRepository;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachMemberRepository coachMemberRepository;

    @Autowired
    private CoachMessageRepository coachMessageRepository;

    @Autowired
    private MemberRepository memberRepository;

    // ==================== 教練註冊 ==================== //
    public CoachMemberVO register(CoachMemberVO registerCoachMember){
        // 檢查此會員ID是否存在
        Optional<Member> checkMember = memberRepository.findById(registerCoachMember.getMemberId());
        // 檢查是否已註冊過教練
        CoachMemberVO checkCoachMember = coachMemberRepository.findByMemberId(registerCoachMember.getMemberId());

        if(checkCoachMember != null){
            // 如果會員ID不存在，需先註冊會員
            if(!checkMember.isPresent()){
                CoachMemberVO coachMember = new CoachMemberVO();
                coachMember.setMessage("尚未成為會員，請先完成會員註冊");
                coachMember.setSuccessful(false);
                return coachMember;
            }

            if(checkCoachMember.getStatus() == 0){
                checkCoachMember.setMessage("此會員ID的教練身分已被停權");
                checkCoachMember.setSuccessful(false);
                return checkCoachMember;
            }
            if(checkCoachMember.getStatus() == 1){
                checkCoachMember.setMessage("此會員ID已註冊為教練，待審核中");
                checkCoachMember.setSuccessful(false);
                return checkCoachMember;
            }
            if(checkCoachMember.getStatus() == 2){
                checkCoachMember.setMessage("此會員ID已註冊為教練，不能重複註冊");
                checkCoachMember.setSuccessful(false);
                return checkCoachMember;
            }
            if(checkCoachMember.getStatus() == 3){
                checkCoachMember.setMessage("註冊成功，請等待審核!");
                checkCoachMember.setSuccessful(true);
                checkCoachMember.setNickname(registerCoachMember.getNickname());
                checkCoachMember.setGender(registerCoachMember.getGender());
                checkCoachMember.setSkills(registerCoachMember.getSkills());
                checkCoachMember.setIntroduction(registerCoachMember.getIntroduction());
                checkCoachMember.setPicture(registerCoachMember.getPicture());
                checkCoachMember.setStatus((byte) 1);
                coachMemberRepository.save(checkCoachMember);
                return checkCoachMember;
            }
        }
        registerCoachMember = coachMemberRepository.save(registerCoachMember);
        registerCoachMember.setMessage("註冊成功，請等待審核!");
        registerCoachMember.setSuccessful(true);
        return registerCoachMember;
    }

    // ==================== 檢查教練身分 ==================== //
    public CoachMemberVO getCoachStatus(Integer memberId){

        CoachMemberVO coachMemberVO = coachMemberRepository.findByMemberId(memberId);

        if(coachMemberVO == null && coachMemberVO.getStatus() == 3){
            CoachMemberVO coachMember = new CoachMemberVO();
            coachMember.setMessage("此會員不為教練會員");
            coachMember.setSuccessful(false);
            return coachMember;
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

    // ==================== 教練資訊 ==================== //

    // 查詢教練審核列表
    public List<CoachDetails> getVerifyCoachList(Integer status, Integer page){
        Page<CoachDetails> pageResult = coachMemberRepository.getVerifyCoachList(status,
                PageRequest.of(page, 5, Sort.by("createTime").ascending()));

        return pageResult.getContent();

//        return coachMemberRepository.getVerifyCoachList(status);
    }

    // 查詢教練審核列表的頁數
    public Integer getListPage(Integer status){
        Page<CoachDetails> pageResult = coachMemberRepository.getVerifyCoachList(status,
                PageRequest.of(0, 5, Sort.by("createTime").ascending()));
        System.out.println("Total Elements: " + pageResult.getTotalElements());
        System.out.println("Total Pages: " + pageResult.getTotalPages());
        return pageResult.getTotalPages();

//        return pageResult.getContent();

//        return coachMemberRepository.getVerifyCoachList(status);
    }

    // 查詢教練列表
    public List<CoachList> getCoachList(){
        return coachMemberRepository.getCoachList();
    }

    // 查詢教練個人頁面
    public CoachDetails getCoachDetails(Integer coachId){
        return coachMemberRepository.getCoachDetails(coachId);
    }

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
    public CoachMemberVO updateStatus(Integer coachId, byte status){
        Optional<CoachMemberVO> check = coachMemberRepository.findById(coachId);

        if(check.isPresent()){
            CoachMemberVO coachMember = check.get();
            coachMember.setStatus(status);
            coachMemberRepository.save(coachMember);
            return coachMember;
        }else {
            CoachMemberVO coachMemberVO = new CoachMemberVO();
            coachMemberVO.setMessage("此申請已不存在");
            coachMemberVO.setSuccessful(false);
            return coachMemberVO;
        }
    }

    // ==================== 教練修改個人資訊 ==================== //

    // 修改個人資訊
    public CoachMemberVO updateDetails(Integer coachId, CoachMemberVO updateDetails){
        Optional<CoachMemberVO> check = coachMemberRepository.findById(coachId);

        if(check.isPresent()){
            CoachMemberVO coachMember = check.get();
            coachMember.setNickname(updateDetails.getNickname());
            coachMember.setSkills(updateDetails.getSkills());
            coachMember.setIntroduction(updateDetails.getIntroduction());
            coachMember.setPicture(updateDetails.getPicture());
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

    // ==================== 教練留言板 ==================== //

    // 新增留言
    public CoachMessageVO addMessage(CoachMessageVO message){
        message.setMessage("新增留言成功");
        message.setSuccessful(true);
        return coachMessageRepository.save(message);
    }

    // 刪除留言
    public String deleteMessage(Integer id){
        coachMessageRepository.deleteById(id);
        return "刪除留言成功";
    }

    // 修改留言
    public CoachMessageVO updateMessage(Integer id, String content){
        Optional<CoachMessageVO> check = coachMessageRepository.findById(id);

        if(check.isPresent()){
            CoachMessageVO message = check.get();
            message.setContent(content);
            message.setMessage("修改留言成功");
            message.setSuccessful(true);
            return coachMessageRepository.save(message);
        }else {
            return null;
        }

    }

    // 查詢留言板
    public List<CoachMessage> getCoachMessage(Integer coachId){
        return coachMessageRepository.getCoachMessage(coachId);
    }

}

package tw.com.cha102.coachmember.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coachmember.model.dto.CoachDetails;
import tw.com.cha102.coachmember.model.dto.CoachList;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;

import java.util.List;

@Repository
public interface CoachMemberRepository extends JpaRepository<CoachMemberVO, Integer> {

    public CoachMemberVO findByMemberId(Integer memberId);

    // verify-coach-List
    @Query(value = "SELECT CM.COACH_ID as coachID, CM.MEMBER_ID as memberId, M.MEMBER_NAME as memberName, " +
            "CM.INTRODUCTION as introduction, CM.PICTURE as picture, CM.STATUS as status, CM.CREATE_TIME as createTime " +
            "FROM coach_member CM LEFT JOIN `MEMBER` M ON CM.MEMBER_ID = M.MEMBER_ID " +
            "WHERE CM.STATUS = 1 ORDER BY CM.COACH_ID", nativeQuery = true)
    public List<CoachList> getVerifyCoachList();

    // coach-List
    @Query(value = "SELECT CM.COACH_ID as coachId, CM.MEMBER_ID as memberId, M.MEMBER_NAME as memberName, " +
            "CM.INTRODUCTION as introduction, CM.PICTURE as picture, CM.STATUS as status, CM.NICKNAME as nickname " +
            "FROM coach_member CM LEFT JOIN `MEMBER` M ON CM.MEMBER_ID = M.MEMBER_ID " +
            "WHERE status = 2 ORDER BY CM.COACH_ID", nativeQuery = true)
    public List<CoachList> getCoachList();

    // coach-details
    @Query(value = "SELECT CM.COACH_ID as coachId, CM.MEMBER_ID as memberId, M.MEMBER_NAME as memberName, " +
            "CM.INTRODUCTION as introduction, CM.PICTURE as picture, M.MEMBER_EMAIL as email, " +
            "M.MEMBER_PHONE as phone, CM.GENDER as gender, CM.SKILLS as skills, CM.NICKNAME as nickname " +
            "FROM coach_member CM LEFT JOIN `MEMBER` M ON CM.MEMBER_ID = M.MEMBER_ID " +
            "WHERE CM.COACH_ID = ?1", nativeQuery = true)
    public CoachDetails getCoachDetails(Integer coachId);

}

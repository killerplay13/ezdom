package tw.com.cha102.coachmember.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coachmember.model.dto.CoachMessage;
import tw.com.cha102.coachmember.model.entity.CoachMessageVO;

import java.util.List;

@Repository
public interface CoachMessageRepository extends JpaRepository<CoachMessageVO, Integer> {

    @Query(value = "SELECT CM.MESSAGE_ID as messageId, CM.COACH_ID as coachId, M.MEMBER_NAME as memberName, " +
            "CM.CONTENT as content, CM.CREATE_TIME as createTime, CMEM.PICTURE as coachPicture, " +
            "M.MEMBER_PHOTO as memberPicture, M.MEMBER_ID as memberId, CMEM.NICKNAME as nickname " +
            "FROM coach_message CM " +
            "LEFT JOIN `MEMBER` M ON CM.MEMBER_ID = M.MEMBER_ID " +
            "LEFT JOIN COACH_MEMBER CMEM ON CMEM.COACH_ID = CM.COACH_ID " +
            "WHERE CM.COACH_ID = ?1 ORDER BY CM.MESSAGE_ID",nativeQuery = true)
    public List<CoachMessage> getCoachMessage(Integer coachId);

}

package tw.com.cha102.coach.model.dao;

import tw.com.cha102.coach.model.entity.CoachMessageVO;
import tw.com.cha102.coachregister.model.dao.CoachMemberDAO;

import java.util.List;

public interface CoachMessageDAO {
    int add(CoachMessageVO coachMessageVO);
    int update(CoachMessageVO coachMessageVO);
    int delete(Integer id);
//    CoachMessageVO findeById(InternalError id);
    List<CoachMessageVO> findAll(Integer coachId);
}

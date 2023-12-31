package tw.com.cha102.coachmember.model.dao;

import tw.com.cha102.coachmember.model.entity.CoachMemberVO;

import java.util.List;

public interface CoachMemberDAO {
    int add(CoachMemberVO coachMemberVO);
    int update(CoachMemberVO coachMemberVO);
    int delete (Integer id);
    CoachMemberVO findById(Integer id);
    List<CoachMemberVO> findAll();
}

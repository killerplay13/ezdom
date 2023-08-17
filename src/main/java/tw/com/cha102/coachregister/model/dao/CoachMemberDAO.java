package tw.com.cha102.coachregister.model.dao;

import java.util.List;

public interface CoachMemberDAO {
    void add(CoachMemberDAO coachMemberDAO);
    void update(CoachMemberDAO coachMemberDAO);
    void delete (CoachMemberDAO coachMemberDAO);
    CoachMemberDAO findById(Integer id);
    List<CoachMemberDAO> findAll();
}

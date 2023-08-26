package tw.com.cha102.coach.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coach.model.entity.CoachMessageVO;

import java.util.List;

@Repository
public interface CoachMessageRepository extends JpaRepository<CoachMessageVO, Integer> {

    public List<CoachMessageVO> findByCoachIdOrderByCreateTime(Integer coachId);

}

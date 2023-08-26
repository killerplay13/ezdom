package tw.com.cha102.coachmember.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.coachmember.model.entity.CoachMemberVO;

@Repository
public interface CoachMemberRepository extends JpaRepository<CoachMemberVO, Integer> {

    public CoachMemberVO findByMemberId(Integer memberId);

}

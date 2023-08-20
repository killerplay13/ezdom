package tw.com.cha102.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.member.model.entity.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findByMemberAccount(String account);
}

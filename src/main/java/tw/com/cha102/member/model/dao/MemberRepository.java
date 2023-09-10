package tw.com.cha102.member.model.dao;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.member.model.entity.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findByMemberAccount(String account);
    Member findByMemberId(Integer memberId);
    @Modifying
    @Query("UPDATE Member m SET m.memberStatus = ?1 WHERE m.memberId = ?2 ")
    int updateMemberStatus(Byte memberStatus, Integer memberId);
}

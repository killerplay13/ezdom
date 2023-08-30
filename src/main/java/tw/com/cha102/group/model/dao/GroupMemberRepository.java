package tw.com.cha102.group.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tw.com.cha102.group.model.GroupMember;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Integer> {

    GroupMember findByGroupIdAndMemberId(Integer groupId, Integer memberId);

    @Query(value = "select groupId from GroupMember where memberId=:memberId and groupApplyStatus = :groupApplyStatus")
    List<Integer> findGroupIdsByMemberIdAndStatus(Integer memberId,Byte groupApplyStatus);
}

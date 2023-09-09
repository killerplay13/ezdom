package tw.com.cha102.groupcreate.model;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;

public interface GroupVerifyRepository extends JpaRepository<GroupVerifyVO,Integer> {
    @Query(
            value = "select* From cha102g4_test.group_member where GROUP_ID IN (:groupIds)",
            nativeQuery = true
    )
    List<GroupVerifyVO> findAllGroupVerify(@Param("groupIds") List<String> groupIds);

    @Query(
            value = "select * from cha102g4_test.group_member where MEMBER_ID = :memberId and GROUP_APPLY_STATUS=4;",
            nativeQuery = true
    )
    List<GroupVerifyVO> findGroupJoined(@Param("memberId") Integer memberId);

    @Query(
            value = "select * from cha102g4_test.group_member where GROUP_APPLY_STATUS = 4 and GROUP_ID = :groupId",
            nativeQuery = true
    )
    List<GroupVerifyVO> findGroupMember(@Param("groupId") Integer groupId);
}

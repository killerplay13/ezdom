package tw.com.cha102.groupcreate.model;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;


public interface GroupCreateRepository extends JpaRepository<GroupCreateVO, Integer> {

    @Query(
            value = "SELECT * FROM cha102g4_test.group WHERE CREATE_MEMBER_ID= :createMemberId",
            nativeQuery = true)
    List<GroupCreateVO> findAllGroupCreate(@Param("createMemberId") Integer createMemberId);

    @Query(
            value = "select * FROM cha102g4_test.group where GROUP_STATUS=1 order by GROUP_CREATE desc limit 4 ",
            nativeQuery = true)
    List<GroupCreateVO> showLatestGroupCreate();

    @Query(value = "select GROUP_ID from cha102g4_test.group where GROUP_STATUS=1 and CREATE_MEMBER_ID = :memberId",
    nativeQuery = true)
    List findGroupId(@Param("memberId") Integer memberId);


    @Query(
            value = " select * from cha102g4_test.group where GROUP_STATUS=1 and GROUP_DATE > curdate() limit 4 ",
            nativeQuery = true
    )
    List<GroupCreateVO> showUpcomingGroupCreate();
}

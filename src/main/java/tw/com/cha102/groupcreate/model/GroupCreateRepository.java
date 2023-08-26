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
}

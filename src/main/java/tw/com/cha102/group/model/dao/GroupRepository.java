package tw.com.cha102.group.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.group.model.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByGroupStatus(int status);//判斷揪團狀態是審核成功才會列在前台揪團列表上

    List<Group> findByGroupStatusAndRegisteredNumberIsGreaterThanEqual(int groupStatus,int  registeredNumber); //

    List<Group> findByGroupStatusAndGroupNameStartingWith(int status,String groupName);//
}
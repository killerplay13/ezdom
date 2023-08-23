package tw.com.cha102.group.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.group.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}
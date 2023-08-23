package tw.com.cha102.groupcreate.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GroupCreateRepository extends JpaRepository<GroupCreateVO, Integer> {

}

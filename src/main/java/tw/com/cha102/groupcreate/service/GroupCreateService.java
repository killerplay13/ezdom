package tw.com.cha102.groupcreate.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import tw.com.cha102.groupcreate.model.GroupCreateVO;

import java.util.List;

@Service
@SpringBootApplication
public interface GroupCreateService {
 GroupCreateVO create(GroupCreateVO groupCreateVO);

 List<GroupCreateVO> findAllGroupCreateByMemberId(Integer createMemberId);
 GroupCreateVO update(GroupCreateVO groupCreateVO);

// List<GroupCreateVO> findOneGroupCreateByMemberIdAndStatus();
}



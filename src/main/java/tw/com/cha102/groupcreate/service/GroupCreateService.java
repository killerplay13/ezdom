package tw.com.cha102.groupcreate.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import tw.com.cha102.groupcreate.model.GroupCreateVO;

@Service
@SpringBootApplication
public interface GroupCreateService {
 GroupCreateVO create(GroupCreateVO groupCreateVO);
}

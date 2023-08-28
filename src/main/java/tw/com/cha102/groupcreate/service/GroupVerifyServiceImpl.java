package tw.com.cha102.groupcreate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.groupcreate.model.GroupVerifyRepository;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;

@Service
@Transactional
public class GroupVerifyServiceImpl implements GroupVerifyService{

    @Autowired
    public final GroupVerifyRepository groupVerifyRepository;

    public GroupVerifyServiceImpl(GroupVerifyRepository groupVerifyRepository) {
        this.groupVerifyRepository = groupVerifyRepository;
    }

    @Override
    public List<GroupVerifyVO> findAllGroupVerifyByGroupId(List<String> groupIds) {
        return groupVerifyRepository.findAllGroupVerify(groupIds);
    }

}

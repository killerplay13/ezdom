package tw.com.cha102.groupcreate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.groupcreate.model.GroupVerifyRepository;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;

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

    public GroupVerifyVO updateGroupApplyStatus(Integer groupMemberId, Integer GroupApplyStatus) {
        GroupVerifyVO existingGroupVerify = groupVerifyRepository.findById(groupMemberId).orElse(null);
        if (existingGroupVerify != null) {
            existingGroupVerify.setGroupApplyStatus(GroupApplyStatus);
            return groupVerifyRepository.save(existingGroupVerify);
        } else {
            return null;
        }
    }

    @Override
    public List<GroupVerifyVO> findGroupJoined(Integer memberId) {
        return groupVerifyRepository.findGroupJoined(memberId);
    }

}

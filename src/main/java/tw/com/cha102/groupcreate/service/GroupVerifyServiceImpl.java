package tw.com.cha102.groupcreate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.groupcreate.model.GroupCreateRepository;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.model.GroupVerifyRepository;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;

import java.util.List;

@Service
@Transactional
public class GroupVerifyServiceImpl implements GroupVerifyService{

    @Autowired
    public final GroupVerifyRepository groupVerifyRepository;
    @Autowired
    public final GroupCreateRepository groupCreateRepository;

    public GroupVerifyServiceImpl(GroupVerifyRepository groupVerifyRepository, GroupCreateRepository groupCreateRepository) {
        this.groupVerifyRepository = groupVerifyRepository;
        this.groupCreateRepository = groupCreateRepository;
    }

    @Override
    public List<GroupVerifyVO> findAllGroupVerifyByGroupId(List<String> groupIds) {
        return groupVerifyRepository.findAllGroupVerify(groupIds);
    }

@Override
    public GroupVerifyVO updateGroupApplyStatus(Integer groupMemberId, Integer groupApplyStatus) {
        GroupVerifyVO existingGroupVerify = groupVerifyRepository.findById(groupMemberId).orElse(null);
        if (existingGroupVerify != null) {
            existingGroupVerify.setGroupApplyStatus(groupApplyStatus);
            return groupVerifyRepository.save(existingGroupVerify);

        } else {
            return null;
        }

    }

    @Override
    public List<GroupVerifyVO> findGroupJoined(Integer memberId) {
        return groupVerifyRepository.findGroupJoined(memberId);
    }

    @Override
    public List<GroupVerifyVO> findGroupMember(Integer groupId) {
        return groupVerifyRepository.findGroupMember(groupId);
    }

}

package tw.com.cha102.groupcreate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tw.com.cha102.groupcreate.model.GroupCreateDAO;
import tw.com.cha102.groupcreate.model.GroupCreateRepository;
import tw.com.cha102.groupcreate.model.GroupCreateVO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupCreateServiceImpl implements GroupCreateService{


    @Autowired
    public final GroupCreateRepository groupCreateRepository;

    public GroupCreateServiceImpl(GroupCreateRepository groupCreateRepository) {
        this.groupCreateRepository = groupCreateRepository;
    }

    public GroupCreateVO saveGroupPhoto(GroupCreateVO groupCreateVO, byte[] photoBytes) {
        groupCreateVO.setGroupPhoto(photoBytes);
        return groupCreateRepository.save(groupCreateVO);
    }

    @Override
    public List<GroupCreateVO> findAllGroupCreateByMemberId(Integer createMemberId) {
        return groupCreateRepository.findAllGroupCreate(createMemberId);
    }

    @Override
    public GroupCreateVO update(Integer memberId, GroupCreateVO groupCreateVO) {
        groupCreateVO.setCreateMemberId(memberId);
        return groupCreateRepository.save(groupCreateVO);
    }

    @Override
    public GroupCreateVO updateRegisterNumber(Integer groupId) {
        GroupCreateVO existingGroupCreate = groupCreateRepository.findById(groupId).orElse(null);
        if (existingGroupCreate != null){
            if (existingGroupCreate.getRegisteredNumber() < existingGroupCreate.getLimitNumber()){
                existingGroupCreate.setRegisteredNumber(existingGroupCreate.getRegisteredNumber()+1);
            }
            return groupCreateRepository.save(existingGroupCreate);
        }else {
            return null;
        }
    }

    @Override
    public Optional<GroupCreateVO> groupInfo(Integer groupId) {
        return groupCreateRepository.findById(groupId);
    }

    @Override
    public List<GroupCreateVO> showLatestGroupCreate() {
        return groupCreateRepository.showLatestGroupCreate();
    }

    @Override
    public List<GroupCreateVO> showUpcomingGroupCreate() {
        return groupCreateRepository.showUpcomingGroupCreate();
    }

    @Override
    public List findGroupId(Integer memberId) {
        return groupCreateRepository.findGroupId(memberId);
    }

//    @Override
//    public List<GroupCreateVO> findOneGroupCreateByMemberIdAndStatus() {
//        return null;
//    }
    

    @Override
    public GroupCreateVO create(Integer memberId,GroupCreateVO groupCreateVO) {

            groupCreateVO.setGroupStatus(0);
            groupCreateVO.setCreateMemberId(memberId);
            groupCreateVO.setRegisteredNumber(0);
            final GroupCreateVO groupCreateResult = groupCreateRepository.save(groupCreateVO);
            groupCreateVO.setMessage("揪團申請已送出，我們將盡速審核");
            groupCreateVO.setSuccessful(true);
            return groupCreateVO;
    }
}

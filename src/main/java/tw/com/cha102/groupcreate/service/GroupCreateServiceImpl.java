package tw.com.cha102.groupcreate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tw.com.cha102.groupcreate.model.GroupCreateDAO;
import tw.com.cha102.groupcreate.model.GroupCreateRepository;
import tw.com.cha102.groupcreate.model.GroupCreateVO;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;

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
    public GroupCreateVO update(GroupCreateVO groupCreateVO) {
        return groupCreateRepository.save(groupCreateVO);
    }


    @Override
    public GroupCreateVO create(GroupCreateVO groupCreateVO) {



            groupCreateVO.setGroupStatus(0);
            groupCreateVO.setCreateMemberId(1);
            groupCreateVO.setRegisteredNumber(0);
            final GroupCreateVO groupCreateResult = groupCreateRepository.save(groupCreateVO);
            groupCreateVO.setMessage("揪團申請已送出，我們將盡速審核");
            groupCreateVO.setSuccessful(true);
            return groupCreateVO;
    }
}

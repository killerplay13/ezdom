package tw.com.cha102.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.group.model.GroupCollect;
import tw.com.cha102.group.model.dao.GroupCollectRepository;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class GroupCollectService {

    private final GroupCollectRepository groupCollectRepository;


    @Autowired
    public GroupCollectService(GroupCollectRepository groupCollectRepository) {
        this.groupCollectRepository = groupCollectRepository;
    }


    public GroupCollect findByGroupIdAndMemberId(Integer groupId, Integer memberId){
        return groupCollectRepository.findByGroupIdAndMemberId(groupId, memberId);
    }

    public List<GroupCollect> findByMemberId(Integer memberId){
        return groupCollectRepository.findByMemberId(memberId);
    }



    public void addCollect(Integer groupId, Integer memberId) {

        GroupCollect groupCollect = new GroupCollect();
        groupCollect.setGroupId(groupId);
        groupCollect.setMemberId(memberId);

        GroupCollect groupCollect1 = groupCollectRepository.findByGroupIdAndMemberId(groupId, memberId);
        if (Objects.nonNull(groupCollect1)){
            return;
        }
        groupCollectRepository.save(groupCollect);
    }

    public void deleteCollect(Integer groupId, Integer memberId) {
        GroupCollect groupCollect = groupCollectRepository.findByGroupIdAndMemberId(groupId, memberId);
        groupCollectRepository.delete(groupCollect);
    }
}

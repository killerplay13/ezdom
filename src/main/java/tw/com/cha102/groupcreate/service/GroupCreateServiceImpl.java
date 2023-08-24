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
    public GroupCreateVO create(GroupCreateVO groupCreateVO) {
       if(groupCreateVO.getGroupDate()==null){
           groupCreateVO.setMessage("揪團時間未輸入");
           groupCreateVO.setSuccessful(false);
           return groupCreateVO;
       }
       int compareGroupDate = groupCreateVO.getGroupDate().compareTo(groupCreateVO.getEndDate());
       if (compareGroupDate<0){
           groupCreateVO.setMessage("揪團日期不能晚於報名截止日期");
           groupCreateVO.setSuccessful(false);
           return groupCreateVO;
       }
        if(groupCreateVO.getGroupLocation()==null){
            groupCreateVO.setMessage("揪團地點未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getGroupTitle()==null){
            groupCreateVO.setMessage("揪團主題未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getGroupNotice()==null){
            groupCreateVO.setMessage("揪團注意事項未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getGroupContent()==null){
            groupCreateVO.setMessage("揪團內容未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getGroupName()==null){
            groupCreateVO.setMessage("揪團名稱未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getStartDate()==null){
            groupCreateVO.setMessage("報名開始日未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getEndDate()==null){
            groupCreateVO.setMessage("報名截止日未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        int compareStartDate = groupCreateVO.getStartDate().compareTo(groupCreateVO.getEndDate());
        if (compareStartDate>0){
            groupCreateVO.setMessage("報名開始日不能晚於報名截止日");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getLimitNumber()==null || groupCreateVO.getLimitNumber()<0){
            groupCreateVO.setMessage("人數上限未輸入或小於0");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getGroupDeposit()==null || groupCreateVO.getGroupDeposit()<0){
            groupCreateVO.setMessage("揪團押金未輸入或小於0");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }


            groupCreateVO.setGroupStatus(0);
            groupCreateVO.setCreateMemberId(1);
            groupCreateVO.setRegisteredNumber(0);
            final GroupCreateVO groupCreateResult = groupCreateRepository.save(groupCreateVO);
//            if (groupCreateResult <1){
//                groupCreateVO.setMessage("新增錯誤");
//                groupCreateVO.setSuccessful(false);
//                return groupCreateVO;
//            }
            groupCreateVO.setMessage("揪團申請已送出，我們將盡速審核");
            groupCreateVO.setSuccessful(true);
            return groupCreateVO;
    }
}

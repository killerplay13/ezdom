package tw.com.cha102.groupcreate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.groupcreate.model.GroupCreateDAO;
import tw.com.cha102.groupcreate.model.GroupCreateVO;

import javax.transaction.Transactional;

@Service
@Transactional
public class GroupCreateServiceImpl implements GroupCreateService{
@Autowired
private GroupCreateDAO dao;


    @Override
    public GroupCreateVO create(GroupCreateVO groupCreateVO) {
       if(groupCreateVO.getGroupDate()==null){
           groupCreateVO.setMessage("揪團時間未輸入");
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
        if(groupCreateVO.getLimitNumber()==null){
            groupCreateVO.setMessage("人數上限未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        if(groupCreateVO.getGroupDeposit()==null){
            groupCreateVO.setMessage("揪團押金未輸入");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }

        //測試用
        groupCreateVO.setCreateMemberId(1);

            groupCreateVO.setGroupStatus(0);

            groupCreateVO.setMessage("揪團申請已送出，我們將盡速審核");
            groupCreateVO.setSuccessful(true);
            return groupCreateVO;
    }
}

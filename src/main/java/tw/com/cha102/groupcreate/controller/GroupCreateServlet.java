package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupcreate.model.GroupCreateRepository;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.service.GroupCreateService;
import tw.com.cha102.groupcreate.service.GroupCreateServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/frontend/groupcreate")
public class GroupCreateServlet {

    @Autowired
    private GroupCreateService groupCreateService;

    @PostMapping("/create") //建立揪團
    public ResponseEntity<String> createGroup(HttpServletRequest request ,@RequestBody GroupCreateVO groupCreateVO) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        if(groupCreateVO.getGroupDate()==null){
            groupCreateVO.setMessage("揪團時間未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        int compareGroupDate = groupCreateVO.getGroupDate().compareTo(groupCreateVO.getEndDate());
        if (compareGroupDate<0){
            groupCreateVO.setMessage("揪團日期不能晚於報名截止日期");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupLocation()==null){
            groupCreateVO.setMessage("揪團地點未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupTitle()==null){
            groupCreateVO.setMessage("揪團主題未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupNotice()==null){
            groupCreateVO.setMessage("揪團注意事項未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupContent()==null){
            groupCreateVO.setMessage("揪團內容未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupName()==null){
            groupCreateVO.setMessage("揪團名稱未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getStartDate()==null){
            groupCreateVO.setMessage("報名開始日未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getEndDate()==null){
            groupCreateVO.setMessage("報名截止日未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        int compareStartDate = groupCreateVO.getStartDate().compareTo(groupCreateVO.getEndDate());
        if (compareStartDate>0){
            groupCreateVO.setMessage("報名開始日不能晚於報名截止日");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getLimitNumber()==null || groupCreateVO.getLimitNumber()<0){
            groupCreateVO.setMessage("人數上限未輸入或小於0");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupDeposit()==null || groupCreateVO.getGroupDeposit()<0){
            groupCreateVO.setMessage("揪團押金未輸入或小於0");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
            if (groupCreateVO.getGroupPhoto() != null && groupCreateVO.getGroupPhoto().length > 0) {

                byte[] photoBytes = groupCreateVO.getGroupPhoto();

                groupCreateVO.setGroupPhoto(photoBytes);
            }

            GroupCreateVO result = groupCreateService.create(memberId,groupCreateVO);

            if (result.isSuccessful()) {
                return ResponseEntity.ok("新增揪團成功");
            } else {
                return ResponseEntity.badRequest().body(result.getMessage());
            }
    }

    @GetMapping("/listAllGroupCreate") //查全部自己創建的揪團
    public List<GroupCreateVO> listAllGroupCreate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        List<GroupCreateVO> groupCreateList = groupCreateService.findAllGroupCreateByMemberId(memberId);
        return groupCreateList;
    }

    @PutMapping("/updateGroup/{groupId}") //修改揪團資訊用
    public ResponseEntity<String> updateGroup(@RequestBody GroupCreateVO groupCreateVO){
        if(groupCreateVO.getGroupName()==null){
            groupCreateVO.setMessage("揪團名稱未輸入");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        int compareGroupDate = groupCreateVO.getGroupDate().compareTo(groupCreateVO.getEndDate());
        if (compareGroupDate<0){
            groupCreateVO.setMessage("揪團日期不能晚於報名截止日期");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        int compareEndDate = groupCreateVO.getEndDate().compareTo(groupCreateVO.getStartDate());
        if (compareEndDate<0){
            groupCreateVO.setMessage("報名開始日不能晚於報名截止日");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getGroupDeposit()==null || groupCreateVO.getGroupDeposit()<0){
            groupCreateVO.setMessage("揪團押金未輸入或小於0");
            groupCreateVO.setSuccessful(false);
            return ResponseEntity.badRequest().body(groupCreateVO.getMessage());
        }
        if(groupCreateVO.getRegisteredNumber()>=groupCreateVO.getLimitNumber()){
            return ResponseEntity.badRequest().body("超過人數上限");
        }
        GroupCreateVO result = groupCreateService.update(groupCreateVO);
        return ResponseEntity.ok("修改成功");
    }

    @PutMapping("/updateRegisterNumber/{groupId}")
    public ResponseEntity<String> updateRegisterNumber(@PathVariable Integer groupId){
        GroupCreateVO result = groupCreateService.updateRegisterNumber(groupId);
        return ResponseEntity.ok("新增成功");
}

    @GetMapping("/findGroupId") //查會員建立且審核過的揪團
    public List findGroupId(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        List groupCreateList = groupCreateService.findGroupId(memberId);
        return groupCreateList;
    }

    @GetMapping("/showLatestGroupCreate")
    public List<GroupCreateVO> showLatestGroupCreate(){
        List<GroupCreateVO> LatestGroupCreateList = groupCreateService.showLatestGroupCreate();
        return LatestGroupCreateList;
    }

    @GetMapping("/showUpcomingGroupCreate")
    public List<GroupCreateVO> showUpcomingGroupCreate(){
        List<GroupCreateVO> UpcomingGroupCreateList = groupCreateService.showUpcomingGroupCreate();
        return UpcomingGroupCreateList;
    }
}

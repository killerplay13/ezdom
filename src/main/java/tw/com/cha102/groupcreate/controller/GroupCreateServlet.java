package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.service.GroupCreateService;

@RestController
@RequestMapping("/groupcreate")
public class GroupCreateServlet {

    @Autowired
    private GroupCreateService service;

    @PostMapping("/create")
    public GroupCreateVO create(@RequestBody GroupCreateVO groupCreateVO){
        if(groupCreateVO == null){
            groupCreateVO = new GroupCreateVO();
            groupCreateVO.setMessage("無揪團資訊");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        return service.create(groupCreateVO);
    }
    @GetMapping("/test")
    public int test(){
        return 1;
    }
}

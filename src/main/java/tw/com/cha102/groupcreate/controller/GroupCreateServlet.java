package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.service.GroupCreateService;
import tw.com.cha102.groupcreate.service.GroupCreateServiceImpl;

@RestController
@RequestMapping("/groupcreate")
public class GroupCreateServlet {

    @Autowired
    private GroupCreateService groupCreateService;

    @PostMapping("/create")
    public GroupCreateVO create(@RequestBody GroupCreateVO groupCreateVO){
        return groupCreateService.create(groupCreateVO);
    }
    @GetMapping("/test")
    public int test(){
        return 1;
    }
}

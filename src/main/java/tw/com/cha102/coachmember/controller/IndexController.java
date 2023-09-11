package tw.com.cha102.coachmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.coachmember.model.dto.CoachList;
import tw.com.cha102.coachmember.service.CoachService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @Autowired
    private CoachService coachService;

    // ==================== 瀏覽教練列表 ==================== //
    @GetMapping("/browse/list")
    public List<CoachList> browseCoachList(){
        return coachService.getCoachList();
    }
}

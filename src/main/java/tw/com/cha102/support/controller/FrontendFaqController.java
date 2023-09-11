package tw.com.cha102.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.support.model.entity.FaqVO;
import tw.com.cha102.support.service.FaqService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faq")
public class FrontendFaqController {

    @Autowired
    private FaqService faqService;

    // ==================== 瀏覽常見問題 ==================== //
    @GetMapping("/list")
    public List<FaqVO> faqList(@RequestParam(required = false) String faqTag){
        return faqService.getFaqList(faqTag);
    }

    // ==================== 瀏覽常見問題 ==================== //
    @GetMapping("/member")
    public Member getMember(@RequestParam(required = false) Integer memberId){
        return faqService.getMember(memberId);
    }
}

package tw.com.cha102.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.message.service.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;
    @GetMapping("/getSelf")
    public Optional<Member> getSelf (HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        return profileService.getMemberProfile(memberId);
    }

    @GetMapping("/getOther/{memberId}")
    public Optional<Member> getOther(@PathVariable Integer memberId){
        return profileService.getMemberProfile(memberId);
    }
}

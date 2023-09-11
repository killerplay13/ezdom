package tw.com.cha102.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.message.model.MessageProfileRepo;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    MessageProfileRepo messageProfileRepo;
    public Optional<Member> getMemberProfile(Integer memberId){return messageProfileRepo.findById(memberId);}
}

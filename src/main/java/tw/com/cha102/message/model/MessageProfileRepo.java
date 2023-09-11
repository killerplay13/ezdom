package tw.com.cha102.message.model;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.member.model.entity.Member;

public interface MessageProfileRepo extends JpaRepository<Member, Integer> {
}

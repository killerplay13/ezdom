package tw.com.cha102.support.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.support.model.entity.FaqVO;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<FaqVO, Integer> {

    public List<FaqVO> findAllByFaqTag(String faqTag);
}

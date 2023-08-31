package tw.com.cha102.ad.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.ad.model.entity.AdVO;

public interface AdRepository extends JpaRepository<AdVO, Integer> {
    
}


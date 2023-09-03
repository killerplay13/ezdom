package tw.com.cha102.reserve.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
@Repository
public interface ReserveItemRepository extends JpaRepository <ReserveItemVO, Integer> {
}

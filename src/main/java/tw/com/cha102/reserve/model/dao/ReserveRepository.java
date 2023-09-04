package tw.com.cha102.reserve.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.reserve.model.entity.ReserveVO;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveVO, Integer> {
}

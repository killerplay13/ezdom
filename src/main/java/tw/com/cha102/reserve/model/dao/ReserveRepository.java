package tw.com.cha102.reserve.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.reserve.model.entity.ReserveVO;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveVO, Integer> {

    @Modifying
    @Query("UPDATE ReserveVO re SET re.orderStatus = ?1 WHERE re.reserveId = ?2")
    int updateReserveStatusByReserveId(byte orderStatus, Integer reserveId);
}

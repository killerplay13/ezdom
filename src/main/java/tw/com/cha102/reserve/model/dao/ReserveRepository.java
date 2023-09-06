package tw.com.cha102.reserve.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.reserve.model.dto.ReserveDTO;
import tw.com.cha102.reserve.model.entity.ReserveVO;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveVO, Integer> {

    @Modifying
    @Query("UPDATE ReserveVO re SET re.orderStatus = ?1 WHERE re.reserveId = ?2")
    int updateReserveStatusByReserveId(byte orderStatus, Integer reserveId);

    @Query(value = "select ri.RESERVE_ITEM as reserveItem, ri.PLACE as place,ri.CONTENT as content,"+
            "r.RESERVE_DATE as reserveDate, r.RESERVE_TIME as reserveTime, r.ORDER_STATUS as orderStatus,"+
            "m.MEMBER_NAME as memberName from reserve r left join reserve_item ri "+
            "on r.RESERVE_ITEM_ID = ri.RESERVE_ITEM_ID left join member m on r.MEMBER_ID=m.MEMBER_ID "+
            "where ri.COACH_ID = ?1 AND RESERVE_DATE=?2 AND RESERVE_TIME=?3 ",nativeQuery = true)
    ReserveDTO getCoachReservationFormByCoachId(Integer coachId, String reserveDate, byte reserveTime);
}

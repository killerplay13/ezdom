package tw.com.cha102.reserve.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.reserve.model.dto.ReserveItemDTO;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveItemRepository extends JpaRepository <ReserveItemVO, Integer> {
    List<ReserveItemVO> findByCoachId(Integer coachId);

    List<ReserveItemVO> findByReserveItemStatusAndCoachId(byte reserveItemStatus,Integer ccoachId);

    Optional<ReserveItemVO> findByReserveItemId(Integer reserveItemId);

    @Modifying
    @Query("UPDATE ReserveItemVO ri SET ri.reserveItemStatus = ?1 WHERE ri.reserveItemId = ?2")
    int updateReserveItemStatus(byte reserveItemStatus, Integer reserveItemId);


    @Query(value = "SELECT RE.RESERVE_ID as reserveId ,RE.RESERVE_DATE as reserveDate,RE.RESERVE_TIME as reserveTime,"+
            "RE.ORDER_STATUS as orderStatus,RI.RESERVE_ITEM as reserveItem,RI.PLACE as place,RI.CONTENT as content,"+
            "RI.AMOUNTS as amounts,CM.NICKNAME as nickname FROM reserve RE LEFT JOIN reserve_item RI ON RE.RESERVE_ITEM_ID"+
            "=RI.RESERVE_ITEM_ID LEFT JOIN COACH_MEMBER CM ON CM.COACH_ID=RI.COACH_ID WHERE RE.MEMBER_ID= ?1 ORDER BY"+ " RE.RESERVE_ID DESC;",nativeQuery = true)
            List<ReserveItemDTO> getMemberReservationFormByMemberId(Integer memberId);
}

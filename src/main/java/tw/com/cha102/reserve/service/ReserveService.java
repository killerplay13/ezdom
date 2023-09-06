package tw.com.cha102.reserve.service;


import tw.com.cha102.reserve.model.dto.ReserveDTO;
import tw.com.cha102.reserve.model.dto.ReserveItemDTO;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import tw.com.cha102.reserve.model.entity.ReserveTimeVO;
import tw.com.cha102.reserve.model.entity.ReserveVO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ReserveService {

    ReserveItemVO insertReserveItem(ReserveItemVO reserveItemVO);

    List<ReserveItemVO> selectReserveItemByCoachId(Integer coachId);

    List<ReserveTimeVO> selectReserveTimeByCoachId(Integer coachId);

    ReserveVO insertReserve(ReserveVO reserveVO);

    boolean updateAppointmentStatus(Integer appointmentStatus, Timestamp date, Integer classTime);

    List<ReserveItemVO> selectByReserveItemStatusAndCoachId(byte reserveItemStatus,Integer coachId);

    boolean updateReserveItemStatus(ReserveItemVO reserveItemVO);

    List<ReserveItemDTO> findMemberReservationFormByMemberId(Integer memberId);

    boolean updateReserveStatusByReserveId(Integer reserveId);

    boolean updateAppointmentStatusByDateAndClassTimeAndCoachId(Timestamp date, Integer classTime, Integer coachId);

    ReserveDTO getCoachReservationFormByCoachId(Integer coachId, String reserveDate, byte reserveTime);

    ReserveTimeVO insetReserveTime(ReserveTimeVO reserveTimeVO);
}


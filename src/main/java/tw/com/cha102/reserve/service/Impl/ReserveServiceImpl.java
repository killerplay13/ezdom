package tw.com.cha102.reserve.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.reserve.model.dao.ReserveItemRepository;
import tw.com.cha102.reserve.model.dao.ReserveRepository;
import tw.com.cha102.reserve.model.dao.ReserveTimeRepository;
import tw.com.cha102.reserve.model.dto.ReserveDTO;
import tw.com.cha102.reserve.model.dto.ReserveItemDTO;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import tw.com.cha102.reserve.model.entity.ReserveTimeVO;
import tw.com.cha102.reserve.model.entity.ReserveVO;
import tw.com.cha102.reserve.service.ReserveService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private ReserveItemRepository reserveItemRepository;

    @Autowired
    private ReserveTimeRepository reserveTimeRepository;


    @Override
    public ReserveItemVO insertReserveItem(ReserveItemVO reserveItemVO) {
        return reserveItemRepository.save(reserveItemVO);
    }

    @Override
    public List<ReserveItemVO> selectReserveItemByCoachId(Integer coachId) {
        byte reserveItemStatus=1;
        return reserveItemRepository.findByCoachIdAndReserveItemStatus(coachId,reserveItemStatus);
    }

    @Override
    public List<ReserveTimeVO> selectReserveTimeByCoachId(Integer coachId) {
        return reserveTimeRepository.findByCoachId(coachId);
    }

    @Override
    public ReserveVO insertReserve(ReserveVO reserveVO) {
        return reserveRepository.save(reserveVO);
    }

    @Override
    public boolean updateAppointmentStatus(Integer appointmentStatus, Timestamp date, Integer classTime) {
        return reserveTimeRepository.updateAppointmentStatus(appointmentStatus,date,classTime)>0;
    }

    @Override
    public List<ReserveItemVO> selectByReserveItemStatusAndCoachId(byte reserveItemStatus,Integer coachId) {
        return reserveItemRepository.findByReserveItemStatusAndCoachId(reserveItemStatus,coachId);
    }

    @Override
    public boolean updateReserveItemStatus(ReserveItemVO reserveItemVO) {
        //專門給刪除按鈕用的
        byte reserveItemStatus=0;
        Integer reserveItemId=reserveItemVO.getReserveItemId();
    return reserveItemRepository.updateReserveItemStatus(reserveItemStatus,reserveItemId)>0;

    }

    @Override
    public List<ReserveItemDTO> findMemberReservationFormByMemberId(Integer memberId) {
        return reserveItemRepository.getMemberReservationFormByMemberId(memberId);
    }

    @Override
    public boolean updateReserveStatusByReserveId(Integer reserveId) {
        //會員將訂單完成
        byte orderStatus=1;
        return reserveRepository.updateReserveStatusByReserveId(orderStatus,reserveId)>0;

    }

    @Override
    public boolean updateAppointmentStatusByDateAndClassTimeAndCoachId(Timestamp date, Integer classTime, Integer coachId) {
        Integer appointmentStatus=3;
        return reserveTimeRepository.updateAppointmentStatus(appointmentStatus,date,classTime,coachId)>0;
    }

    @Override
    public ReserveDTO getCoachReservationFormByCoachId(Integer coachId, String reserveDate, byte reserveTime) {
        return reserveRepository.getCoachReservationFormByCoachId(coachId,reserveDate,reserveTime);
    }

    @Override
    public ReserveTimeVO insetReserveTime(ReserveTimeVO reserveTimeVO) {
        return reserveTimeRepository.save(reserveTimeVO);
    }

    @Override
    public boolean deleteReserveTime(Integer reserveTimeId){
        reserveTimeRepository.deleteById(reserveTimeId);
        return true;
    }

}

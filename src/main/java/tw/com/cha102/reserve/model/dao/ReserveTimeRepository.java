package tw.com.cha102.reserve.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.reserve.model.entity.ReserveTimeVO;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReserveTimeRepository extends JpaRepository<ReserveTimeVO, Integer> {
    List<ReserveTimeVO> findByCoachId(Integer coachId);

    @Modifying
    @Query("UPDATE ReserveTimeVO rt SET rt.appointmentStatus = ?1 WHERE rt.date = ?2 AND rt.classTime = ?3")
    int updateAppointmentStatus(Integer appointmentStatus, Timestamp date, Integer classTime);
}

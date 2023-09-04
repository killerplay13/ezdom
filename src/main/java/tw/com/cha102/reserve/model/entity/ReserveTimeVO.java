package tw.com.cha102.reserve.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "reserve_time")
public class ReserveTimeVO  extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVE_TIME_ID")
    private Integer reserveTimeId;

    @Column(name = "COACH_ID", nullable = false)
    private Integer coachId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Timestamp date;

    @Column(name = "CLASS_TIME", nullable = false)
    private Integer classTime;

    @Column(name = "APPOINTMENT_STATUS", insertable = false)
    private Integer appointmentStatus;
}

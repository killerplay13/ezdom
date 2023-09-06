package tw.com.cha102.reserve.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
public class ReserveTimeDTO {

    @NotNull
    private String dateStr;

    @NotNull
    private Integer classTime;

    @NotNull
    private Integer coachId;
}

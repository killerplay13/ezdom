package tw.com.cha102.reserve.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
public class ReserveTime {
    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Timestamp date;

    @NotNull
    private Integer classTime;

    @NotNull
    private Integer appointmentStatus;
}

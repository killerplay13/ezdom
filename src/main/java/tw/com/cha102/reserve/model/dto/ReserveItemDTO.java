package tw.com.cha102.reserve.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public interface ReserveItemDTO {

    Integer getReserveId();

    String getReserveDate();

    byte getReserveTime();

    byte getOrderStatus();

    String getReserveItem();

    String getPlace();
    String getContent();
    Integer getAmounts();
    String getNickname();
    Integer getCoachId();
}

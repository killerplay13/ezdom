package tw.com.cha102.coach.model.dto;

import java.sql.Timestamp;

public interface ByCoachMessage {
    Integer getMessageId();

    Integer getCoachId();

    String getMemberName();

    String getContent();

    Timestamp getCreateTime();
}

package tw.com.cha102.coach.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public interface ByCoachMessage {
    Integer getMessageId();

    Integer getCoachId();

    String getMemberName();

    String getContent();

//    待測試 TimeStamp型別
    String getCreateTime();

    byte[] getCoachPicture();

    byte[] getMemberPicture();

    Integer getMemberId();

    String getNickname();
}

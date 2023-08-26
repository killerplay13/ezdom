package tw.com.cha102.coachmember.model.dto;

import java.sql.Timestamp;

public interface CoachList {
    Integer getCoachId();

    Integer getMemberId();

    String getMemberName();

    String getIntroduction();

    byte[] getPicture();

    Integer getStatus();

    Timestamp getCreateTime();
}

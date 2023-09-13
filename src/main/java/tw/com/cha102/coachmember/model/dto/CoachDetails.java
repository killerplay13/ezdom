package tw.com.cha102.coachmember.model.dto;

import java.sql.Timestamp;

public interface CoachDetails {
    Integer getCoachId();

    Integer getMemberId();

    String getMemberName();

    String getIntroduction();

    byte[] getPicture();

    String getEmail();

    String getPhone();

    String getGender();

    String getSkills();

    String getNickname();

    Integer getStatus();

    String getCreateTime();

}

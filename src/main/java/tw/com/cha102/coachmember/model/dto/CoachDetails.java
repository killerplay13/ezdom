package tw.com.cha102.coachmember.model.dto;

public interface CoachDetails {
    Integer getCoachId();

    Integer getMemberId();

    String getMemberName();

    String getIntroduction();

    byte[] getPicture();

    String getEmail();

    Integer getPhone();

    String getGender();

    String getSkills();

    String getNickname();

}

package tw.com.cha102.coachmember.model.dto;

public interface CoachMessage {
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

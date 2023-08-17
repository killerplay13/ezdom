package tw.com.cha102.message.model;

import java.sql.Timestamp;

public class MessageVO {

    private String type;
    private Integer memberIdA;
    private Integer memberIdB;
    private String messageContent;
    private boolean messageStatus;
    private Timestamp messageTime;

    public MessageVO(String type, Integer memberIdA, Integer memberIdB, String messageContent, boolean messageStatus, Timestamp messagetime) {
        this.type = type;
        this.memberIdA = memberIdA;
        this.memberIdB = memberIdB;
        this.messageContent = messageContent;
        this.messageStatus = messageStatus;
        this.messageTime = messagetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMemberIdA() {
        return memberIdA;
    }

    public void setMemberIdA(Integer memberIdA) {
        this.memberIdA = memberIdA;
    }

    public Integer getMemberIdB() {
        return memberIdB;
    }

    public void setMemberIdB(Integer memberIdB) {
        this.memberIdB = memberIdB;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(boolean messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messagetime) {
        this.messageTime = messagetime;
    }
}

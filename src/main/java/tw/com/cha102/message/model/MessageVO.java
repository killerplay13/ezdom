package tw.com.cha102.message.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tw.com.cha102.member.model.entity.Member;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Base64;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MessageVO {

    private String type;

    private String memberIdA;

    private String memberIdB;

    private String messageContent;
    private boolean messageStatus;
    private String messageTime;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemberIdA() {
        return memberIdA;
    }

    public void setMemberIdA(String memberIdA) {
        this.memberIdA = memberIdA;
    }

    public String getMemberIdB() {
        return memberIdB;
    }

    public void setMemberIdB(String memberIdB) {
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

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messagetime) {
        this.messageTime = messagetime;
    }
}

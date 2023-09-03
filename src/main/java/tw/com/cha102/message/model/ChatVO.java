package tw.com.cha102.message.model;

import java.sql.Timestamp;

public class ChatVO {
    private String type;
    private String sender;
    private String groupId;
    private String chatContent;
    private Integer readNum;
    private String chatTime;

    public ChatVO(String type, String sender, String groupId, String chatContent, Integer readNum, String chatTime) {
        this.type = type;
        this.sender = sender;
        this.groupId = groupId;
        this.chatContent = chatContent;
        this.readNum = readNum;
        this.chatTime = chatTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }
}

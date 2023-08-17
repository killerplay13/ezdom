package tw.com.cha102.message.model;

import java.sql.Timestamp;

public class ChatVO {
    private String type;
    private Integer groupId;
    private String chatContent;
    private Integer readNum;
    private Timestamp chatTime;

    public ChatVO(String type, Integer groupId, String chatContent, Integer readNum, Timestamp chatTime) {
        this.type = type;
        this.groupId = groupId;
        this.chatContent = chatContent;
        this.readNum = readNum;
        this.chatTime = chatTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
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

    public Timestamp getChatTime() {
        return chatTime;
    }

    public void setChatTime(Timestamp chatTime) {
        this.chatTime = chatTime;
    }
}

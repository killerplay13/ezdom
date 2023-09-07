package tw.com.cha102.member.dto;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class ProfileRequest {
    private String name;
    private String uid;
    private String email;
    private String phone;
    private Timestamp birth;
    private Byte gender;
    private String introduction;
    private String address;
}

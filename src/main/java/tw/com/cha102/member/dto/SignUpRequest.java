package tw.com.cha102.member.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private  String account;
    private  String password;
    private  String name;
    private  String address;

    private  String phone;

    private  String uid;

    private  String email;


}

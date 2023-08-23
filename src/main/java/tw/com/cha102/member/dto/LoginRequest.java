package tw.com.cha102.member.dto;

import lombok.Data;

@Data
public class LoginRequest {
    //todo  maven 加入 validation
    private  String account;
    private  String password;
}

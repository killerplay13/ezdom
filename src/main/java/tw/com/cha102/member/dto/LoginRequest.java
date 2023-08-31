package tw.com.cha102.member.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private  String account;
    @NotBlank
    private  String password;
}

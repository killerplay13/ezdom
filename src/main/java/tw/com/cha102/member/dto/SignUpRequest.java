package tw.com.cha102.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private  String account;
    @NotBlank
    private  String email;
    @NotBlank
    private  String password;
}

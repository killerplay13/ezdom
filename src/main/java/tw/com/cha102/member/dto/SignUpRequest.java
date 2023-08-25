package tw.com.cha102.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private  String account;
    @NotBlank
    private  String password;
    @NotBlank
    private  String name;
    @NotBlank
    private  String address;
    @NotBlank
    private  String phone;
    @NotBlank
    private  String uid;
    @Email
    private  String email;


}

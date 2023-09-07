package tw.com.cha102.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CheckEmailPasswordRequest {
    @NotBlank
    private String account;
    @Email
    @NotBlank
    private String email;
}

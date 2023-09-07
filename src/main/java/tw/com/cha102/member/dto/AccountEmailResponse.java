package tw.com.cha102.member.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class AccountEmailResponse {
    private String account;
    private String email;
}

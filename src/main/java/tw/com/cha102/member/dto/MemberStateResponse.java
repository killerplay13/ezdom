package tw.com.cha102.member.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class MemberStateResponse {

    private String account;
    private String name;
    private Integer point;
    private Byte status;
}

package tw.com.cha102.member.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

@Data
public class MemberProfileResponse {
    private String memberUid;
    private String name;
    private String phone;
    private String email;
    private Timestamp birth;
    private Byte gender;
    private String introduction;
    private Integer point;
    private String address;
}

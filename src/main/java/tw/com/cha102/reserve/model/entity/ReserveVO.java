package tw.com.cha102.reserve.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "reserve")
public class ReserveVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVE_ID")
    private Integer reserveId;

    @Column(name = "MEMBER_ID")
    private Integer memberId;

    @Column(name = "RESERVE_ITEM_ID")
    private Integer reserveItemId;

    @Column(name = "RESERVE_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Timestamp reserveDate;

    @Column(name = "RESERVE_TIME")
    private byte reserveTime;

    @Column(name = "ORDER_STATUS", insertable = false)
    private byte orderStatus;

    @Column(name = "CREAT_TIME",insertable = false)
    private Timestamp createTime;
}

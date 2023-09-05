package tw.com.cha102.reserve.model.entity;

import lombok.Data;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reserve_item")
public class ReserveItemVO extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVE_ITEM_ID")
    private Integer reserveItemId;

    @Column(name = "COACH_ID", nullable = false)
    private Integer coachId;

    @Column(name = "RESERVE_ITEM", nullable = false)
    private String reserveItem;

    private String place;

    private String content;

    private Integer amounts;
    @Column(name = "RESERVE_ITEM_STATUS", insertable = false)
    private byte reserveItemStatus;
}

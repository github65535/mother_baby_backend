package com.ypika.mp.domain;

import com.ypika.mp.enumer.FoodType;
import com.ypika.mp.enumer.NoteType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Data
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 记录类型
     */
    @Enumerated(EnumType.STRING)
    private NoteType noteType;
    /**
     * 食物类型
     */
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    /**
     * 食用时长
     */
    private Long eatDuration;
    /**
     * 食用总量
     */
    private Long eatSum;
    /**
     * 吐槽内容
     */
    private String speakContent;
    /**
     * 饮食时间
     */
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eatDate;
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiDate;

    private Boolean delFlag;

}

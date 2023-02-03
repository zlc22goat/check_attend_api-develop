package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
@ApiModel(value="VacationRecord对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VacationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "假期申请表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "请假开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "请假结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "请假时长(天)")
    @JsonIgnore
    private Integer day;

    @ApiModelProperty(value = "请假时长(小时)")
    @JsonIgnore
    private Integer hour;

    @ApiModelProperty(value = "请假类别")
    @JsonIgnore
    private String vacateCategoryId;

    @ApiModelProperty(value = "请假原因")
    private String reason;

    @ApiModelProperty(value = "请假人id")
    private String employeId;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "审核人")
    private String auditorId;

}

package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@ApiModel(value="RestRecord对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "调休表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "申请时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "调休开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "调休结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "未休时长")
    private Float restResidue;

    @ApiModelProperty(value = "调休时长")
    private Float duration;

    @ApiModelProperty(value = "调休原因")
    private String reason;

    @ApiModelProperty(value = "申请人id")
    private String employeId;

    @ApiModelProperty(value = "申请状态")
    private Integer status;

    @ApiModelProperty(value = "审批人id")
    private String auditorId;
}

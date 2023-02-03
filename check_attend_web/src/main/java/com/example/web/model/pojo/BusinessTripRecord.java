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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value="BusinessTripRecord对象", description="")
public class BusinessTripRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出差表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "出差时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "出差天数")
    private Integer duration;

    @ApiModelProperty(value = "同行人员")
    private String companion;

    @ApiModelProperty(value = "目的地")
    private String destination;

    @ApiModelProperty(value = "出行方式")
    @JsonIgnore
    private String tripModeId;

    @ApiModelProperty(value = "出差事由")
    private String reason;

    @ApiModelProperty(value = "员工id")
    private String employeId;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "审核人id")
    private String auditorId;
}

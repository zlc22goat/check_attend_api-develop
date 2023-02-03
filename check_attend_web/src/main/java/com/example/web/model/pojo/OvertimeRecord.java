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
@ApiModel(value="OvertimeRecord对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OvertimeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "加班表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "申请时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "加班时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "加班时长")
    private Double duration;

    @ApiModelProperty(value = "加班原因")
    private String reason;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "审核人id")
    private String auditorId;

    @ApiModelProperty(value = "发起人id")
    private String employeId;

}

package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
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
@ApiModel(value="CheckRecord对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CheckRecord implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考勤表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "考勤状态")
    @JsonIgnore
    private String checkCategoryId;

    @ApiModelProperty(value = "登记时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "员工id")
    private String employeId;

    @ApiModelProperty(value = "考勤时段")
    @JsonIgnore
    private String launchTimeId;

    @ApiModelProperty(value = "考勤说明")
    private String description;

    @ApiModelProperty(value = "记录人")
    private String recorderId;

}

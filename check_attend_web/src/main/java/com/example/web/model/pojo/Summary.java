package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-05-22
 */
@ApiModel(value="Summary对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Summary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "汇总表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "员工id")
    @JsonIgnore
    private String employeId;

    @ApiModelProperty(value = "请假总时长")
    private Float vacationTimeSum;

    @ApiModelProperty(value = "出差次数")
    private Integer businessTripFreq;

    @ApiModelProperty(value = "加班总时长")
    private Float overTimeSum;

    @ApiModelProperty(value = "调休总时长")
    private Float restTimeSum;

    @ApiModelProperty(value = "迟到次数")
    private Integer lateFreq;

    @ApiModelProperty(value = "早退次数")
    private Integer earlyFreq;

    @ApiModelProperty(value = "漏打卡次数")
    private Integer leakageFreq;

    @ApiModelProperty(value = "早会未到次数")
    private Integer morningAbsent;

    @ApiModelProperty(value = "早操未到次数")
    private Integer morningExerciseAbsent;

    @ApiModelProperty(value = "会议缺席次数")
    private Integer meetingAbsent;
}

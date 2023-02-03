package com.example.web.model.vo;

import com.example.web.model.pojo.VacationRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author lincheon
 * @since 2022/5/19 22:13
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VacationRecordVo extends VacationRecord {
    @ApiModelProperty(value = "请假类型")
    public String vacationCategory;
    @ApiModelProperty(value = "申请人名称")
    public String employeName;
    @ApiModelProperty(value = "审核人名称")
    public String auditorName;
    @ApiModelProperty(value = "请假时长")
    public Double duration;

}

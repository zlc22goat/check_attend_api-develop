package com.example.web.model.vo;

import com.example.web.model.pojo.CheckRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lincheon
 * @since 2022/5/19 15:29
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CheckRecordVo extends CheckRecord {
    @ApiModelProperty(value = "考勤状况")
    public String checkCategory;
    @ApiModelProperty(value = "考勤对象")
    public String employeName;
    @ApiModelProperty(value = "考勤时段")
    public String launchTime;
    @ApiModelProperty(value = "记录人")
    public String recorderName;
}

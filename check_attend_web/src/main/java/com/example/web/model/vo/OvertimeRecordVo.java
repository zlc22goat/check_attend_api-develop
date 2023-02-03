package com.example.web.model.vo;

import com.example.web.model.pojo.OvertimeRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lincheon
 * @since 2022/5/19 23:58
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OvertimeRecordVo extends OvertimeRecord {
    public String employeName;
    public String auditorName;
}

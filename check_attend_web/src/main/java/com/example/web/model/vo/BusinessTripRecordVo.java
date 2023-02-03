package com.example.web.model.vo;

import com.example.web.model.pojo.BusinessTripRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lincheon
 * @since 2022/5/19 23:13
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BusinessTripRecordVo extends BusinessTripRecord {
    public String tripMode;
    public String employeName;
    public String auditorName;
}

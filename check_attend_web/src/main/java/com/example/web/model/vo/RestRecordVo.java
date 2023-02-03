package com.example.web.model.vo;

import com.example.web.model.pojo.RestRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lincheon
 * @since 2022/5/20 0:14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestRecordVo extends RestRecord {
    public String employeName;
    public String auditorName;
}

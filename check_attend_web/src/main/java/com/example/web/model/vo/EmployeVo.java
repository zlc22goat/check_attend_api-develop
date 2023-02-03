package com.example.web.model.vo;

import com.example.web.model.pojo.Employe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lincheon
 * @since 2022/5/22 15:42
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EmployeVo extends Employe {
    public String department;
}

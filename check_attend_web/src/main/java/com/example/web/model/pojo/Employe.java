package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.example.core.model.dto.SelectableEntity;
import com.example.core.model.dto.UnrepeatableEntity;
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
@ApiModel(value="Employe对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Employe implements Serializable, SelectableEntity, UnrepeatableEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工表编号")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "员工性别")
    private String gender;

    @ApiModelProperty(value = "员工年龄")
    private Integer age;

    @ApiModelProperty(value = "员工邮箱")
    private String email;

    @ApiModelProperty(value = "员工学历")
    private String education;

    @ApiModelProperty(value = "员工联系方式")
    private String phone;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "部门Id")
    @JsonIgnore
    private String departmentId;

    @Override
    public String selectValue() {
        return name;
    }

    @Override
    public boolean unAble() {
        return false;
    }

    @Override
    public String judgeProperty() {
        return "code";
    }
}

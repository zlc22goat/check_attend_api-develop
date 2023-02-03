package com.example.core.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.core.model.dto.SelectableEntity;
import com.example.core.model.dto.UnrepeatableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tzy
 * @since 2022-01-02
 */
@ApiModel(value="User对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable, UnrepeatableEntity, SelectableEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "角色Id，role表主键")
    @JsonIgnore
    private String roleId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "加密盐值")
    @JsonIgnore
    private String salt;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态：正常（1）、锁定（2）、离职（0）")
    private Boolean state;

    @ApiModelProperty(value = "员工id")
    @JsonIgnore
    private String employeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Override
    public String judgeProperty() {
        return "userName";
    }

    @Override
    public String selectValue() {
        return getUserName();
    }

    @Override
    public boolean unAble() {
        return false;
    }
}

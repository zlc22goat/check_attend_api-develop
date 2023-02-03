package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.example.core.model.dto.SelectableEntity;
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
@ApiModel(value="AttendTime对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AttendTime implements Serializable, SelectableEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考勤时段表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "考勤时段")
    private String attendTime;

    @Override
    public String selectValue() {
        return attendTime;
    }

    @Override
    public boolean unAble() {
        return false;
    }

}

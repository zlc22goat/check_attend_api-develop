package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author lincheon
 * @since 2022-05-19
 */
@ApiModel(value="RestCategory对象", description="")
public class RestCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请假类别表id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "请假类别")
    private String restCategory;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestCategory() {
        return restCategory;
    }

    public void setRestCategory(String restCategory) {
        this.restCategory = restCategory;
    }

    @Override
    public String toString() {
        return "RestCategory{" +
        "id=" + id +
        ", restCategory=" + restCategory +
        "}";
    }
}

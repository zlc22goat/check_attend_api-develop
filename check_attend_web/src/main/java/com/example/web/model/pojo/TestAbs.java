package com.example.web.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.core.model.dto.SelectableEntity;
import com.example.core.model.dto.UnrepeatableEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tzy
 * @since 2021-09-24
 */
@TableName("Test_Abs")
@ApiModel(value="TestAbs对象", description="")
public class TestAbs implements Serializable, UnrepeatableEntity, SelectableEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "picture")
    private String picture;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String selectValue() {
        return picture;
    }

    @Override
    public boolean unAble() {
        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "TestAbs{" +
        "id=" + id +
        ", name=" + name +
        ", url=" + url +
        ", picture=" + picture +
        "}";
    }

    @Override
    public String judgeProperty() {
        return "name";
    }
}

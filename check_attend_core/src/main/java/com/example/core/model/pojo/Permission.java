package com.example.core.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-01-02
 */
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable, UnrepeatableEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @ApiModelProperty(value = "英文名称，与vue中route的name一致")
    private String name;

    @ApiModelProperty(value = "中文名称，显示到权限配置界面中的名称，可以与vue中的title一致")
    private String title;

    @ApiModelProperty(value = "功能的级别")
    private Boolean level;

    @ApiModelProperty(value = "排序，同一父级别下的排序")
    private Boolean sort;


    public String getId() {
        return id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
    }

    public Boolean getSort() {
        return sort;
    }

    public void setSort(Boolean sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Permission{" +
        "id=" + id +
        ", name=" + name +
        ", title=" + title +
        ", level=" + level +
        ", sort=" + sort +
        "}";
    }

    @Override
    public String judgeProperty() {
        return "name";
    }
}

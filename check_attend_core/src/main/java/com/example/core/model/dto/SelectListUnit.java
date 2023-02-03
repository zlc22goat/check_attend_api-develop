package com.example.core.model.dto;

public class SelectListUnit {

    public SelectListUnit() {}

    public SelectListUnit(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 主键
     */
    private String id;
    /**
     * 类型名称
     */
    private String name;

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
}

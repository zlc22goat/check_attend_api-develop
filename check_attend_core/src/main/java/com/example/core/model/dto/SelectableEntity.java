package com.example.core.model.dto;

/**
 * @author lincheon
 */
public interface SelectableEntity {
    /**
     * 返回对应的id值
     * @return 对应的id值
     */
    String getId();

    /**
     * 下拉的哪个属性值
     * @return 对应的属性值
     */
    String selectValue();

    /**
     * 判断是否不可使用（默认是false）
     * @return 是否是不可下拉
     */
    boolean unAble();
}
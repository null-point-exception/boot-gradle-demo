package com.practice.bean.query;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 排序枚举.
 *
 * @author kexin.ding
 */
public enum TypeEnum implements IEnum<String> {

    //升序
    ASC("ASC"),
    //降序
    DESC("DESC");

    private String value;

    TypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
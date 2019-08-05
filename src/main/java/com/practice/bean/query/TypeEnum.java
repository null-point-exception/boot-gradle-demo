package com.practice.bean.query;

/**
 * 排序枚举.
 *
 * @author kexin.ding
 */
public enum TypeEnum {

    //升序
    ASC("ASC"),
    //降序
    DESC("DESC");

    private String value;

    TypeEnum(String value) {
        this.value = value;
    }
}
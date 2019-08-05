package com.practice.base;

/**
 * 状态响应
 *
 * @author kexin.ding
 */
public enum ResponseCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    /**
     * 请求失败
     */
    FAIL(1, "请求失败"),
    /**
     * 未认证
     */
    UNAUTHORIZED(401, "未认证"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404, "接口不存在"),
    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务内部错误");

    /**
     * 响应码
     */
    private final int code;
    /**
     * 响应码说明
     */
    private final String name;

    ResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取响应码
     *
     * @return 响应码
     */
    public int code() {
        return code;
    }

    /**
     * 获取响应码说明
     *
     * @return 响应码说明
     */
    public String message() {
        return name;
    }

}

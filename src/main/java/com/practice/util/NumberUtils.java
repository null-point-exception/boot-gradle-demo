package com.practice.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 数字工具类.
 *
 * @author kexin.ding
 */
public class NumberUtils {

    private NumberUtils(){
        throw new IllegalStateException("NumberUtils");
    }

    public static String genUUID() {
        return IdWorker.get32UUID();
    }

    public static String genUUID(int n) {
        return genUUID().substring(0, n);
    }

}

package com.practice.util;

import cn.hutool.core.util.StrUtil;
import com.practice.bean.query.Sort;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询工具类.
 *
 * @author kexin.ding
 */
public class QueryUtils {

    /**
     * 设置排序字段
     */
    public static void setField(@Nullable Sort sort, @NonNull Class clazz) {
        if (null != sort && StrUtil.isNotBlank(sort.getField())) {
            Assert.notNull(BeanUtils.getPropertyDescriptor(clazz, sort.getField()), "排序字段有误：不存在" + sort.getField() + "字段");
            sort.setField(humpToLine(sort.getField()).toUpperCase());
        }
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     */
    private static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}

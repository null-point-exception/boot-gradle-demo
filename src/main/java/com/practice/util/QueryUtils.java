package com.practice.util;

import com.practice.bean.query.Sort;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryUtils {

    /**
     * 设置排序字段
     * @param sort
     * @param clazz
     */
    public static void setField(Sort sort, Class clazz) {
        if (null != sort && null != sort.getField()) {
            String name = null;
            //根据Class对象获得属性 私有的也可以获得
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (sort.getField().equals(field.getName())) {
                    name = field.getName();
                    break;
                }
            }
            Assert.notNull(name, "排序字段有误");
            sort.setField(humpToLine(name).toUpperCase());
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

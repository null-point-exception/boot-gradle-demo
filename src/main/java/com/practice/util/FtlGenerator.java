package com.practice.util;

import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker自动生成文件.
 *
 * @author kexin.ding
 */
@SuppressWarnings("ALL")
public class FtlGenerator {
    private static class Path {
        private static final String PROJECT_PATH = System.getProperty("user.dir");
        private static final String JAVA_PATH = "src/main/java";
        private static final String RESOURCE_PATH = "src/main/resources";
        private static final String TEMPLATE_FILE_PATH = RESOURCE_PATH + "/templates";
    }

    private static final String CHARSET = "UTF-8";
    private static Configuration cfg = getConfiguration();

    public static void main(String[] args) {
        // 生成文件目录
        String outputDir = "E:\\";
        // 模板
        String ftl = "index.html.ftl";
        // 模板所需要的参数
        Map<String, Object> data = new HashMap<>(16);
        data.put("title", "首页");
        data.put("content", "这是一段freemarker生成的内容");
        // 文件名称
        String fileName = "test.html";

        genFtl(ftl, outputDir, fileName, data);
    }

    private static void genFtl(String ftl, String outputDir, String fileName, Map<String, Object> data) {
        try {
            File file = new File(outputDir, fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(ftl).process(data, new OutputStreamWriter(new FileOutputStream(file), CHARSET));
            System.out.println(fileName + " 生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(fileName + " 生成失败");
        }

    }

    private static Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setDirectoryForTemplateLoading(new File(Path.TEMPLATE_FILE_PATH));
            cfg.setDefaultEncoding(CHARSET);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.isTrue(false, "配置有误");
        }
        return cfg;
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertLowerHyphen(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableName.toLowerCase());
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}

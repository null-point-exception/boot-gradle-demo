package com.practice.util;

import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker自动生成api/service/mapper/xml文件.
 *
 * @author kexin.ding
 */
@SuppressWarnings("ALL")
public class FtlGenerator {
    /**
     * JDBC配置
     */
    private static class Jdbc {
        private static final String URL = "jdbc:mysql://localhost:3333/demo";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "123456";
        private static final String DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    }

    /**
     * 路径
     */
    private static class Path {
        private static final String JAVA_PATH = "src/main/java";
        private static final String RESOURCE_PATH = "src/main/resources";
        private static final String TEMPLATE_FILE_PATH = RESOURCE_PATH + "/templates";
        private static final String XML_PATH = RESOURCE_PATH + "/mapper";
    }

    /**
     * 包
     */
    private static class Package {
        private static final String PARENT_PACKAGE = "com.test";
        private static final String PACKAGE_CONTROLLER = PARENT_PACKAGE + ".controller";
        private static final String PACKAGE_SERVICE_IMPL = PARENT_PACKAGE + ".service.impl";
        private static final String PACKAGE_MAPPER = PARENT_PACKAGE + ".dao";
        private static final String PACKAGE_ENTITY = PARENT_PACKAGE + ".bean.entity";
        private static final String PACKAGE_QUERY = PARENT_PACKAGE + ".bean.query";
    }

    /**
     * 注释
     */
    private static class Note {
        private static final String AUTHOR = "dkx";
        private static final String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 文件名称后缀
     */
    private static class Suffix {
        private static final String XML = "Mapper";
        private static final String ENTITY = "";
        private static final String QUERY = "Query";
        private static final String MAPPER = "Mapper";
        private static final String SERVICEIMPL = "Service";
        private static final String CONTROLLER = "Api";
    }

    private static final String CHARSET = "UTF-8";
    private static Configuration cfg = getConfiguration();


    @Data
    @AllArgsConstructor
    static class Param {
        String tableName;
        String desc;
        boolean selectAll;
        boolean findPage;
    }

    public static void main(String[] args) {
        Param[] params = {
                new Param("USER_DEPT", "用户部门", true, true),
                new Param("DEPT", "部门", true, false),
                new Param("AREA", "所属区域", false, true),
                new Param("NATION", "民族", false, false),
        };
        for (Param param : params) {
            if (param.selectAll || param.findPage) {
                genQuery(param);
            }
            genEntity(param);
            genMapperAndXml(param);
            genService(param);
            genApi(param);
        }
    }
    private static void genQuery(Param param) {
        //模板所需要的参数
        Map<String, Object> data = initData(param);
        String fileName = (String) data.get("model") + Suffix.QUERY  + ".java";
        genFtl("query.java.ftl", Path.JAVA_PATH + "/" + packageConvertPath(Package.PACKAGE_QUERY), fileName, data);
    }

    private static void genEntity(Param param) {
        //模板所需要的参数
        Map<String, Object> data = initData(param);
        String fileName = (String) data.get("model") + Suffix.ENTITY + ".java";
        genFtl("entity.java.ftl", Path.JAVA_PATH + "/" + packageConvertPath(Package.PACKAGE_ENTITY), fileName, data);
    }

    private static void genMapperAndXml(Param param) {
        //模板所需要的参数
        Map<String, Object> data = initData(param);
        String fileName = (String) data.get("model") + Suffix.MAPPER;
        genFtl("mapper.java.ftl", Path.JAVA_PATH + "/" + packageConvertPath(Package.PACKAGE_MAPPER), fileName + ".java", data);
        genFtl("mapper.xml.ftl", Path.XML_PATH, fileName + ".xml", data);
    }

    private static void genService(Param param) {
        //模板所需要的参数
        Map<String, Object> data = initData(param);
        String fileName = (String) data.get("model")  + Suffix.SERVICEIMPL + ".java";
        genFtl("service.java.ftl", Path.JAVA_PATH + "/" + packageConvertPath(Package.PACKAGE_SERVICE_IMPL), fileName, data);
    }

    private static void genApi(Param param) {
        //模板所需要的参数
        Map<String, Object> data = initData(param);
        String fileName = (String) data.get("model") + Suffix.CONTROLLER + ".java";
        data.put("url", "api/" + tableNameConvertLowerHyphen(param.tableName));
        genFtl("api.java.ftl", Path.JAVA_PATH + "/" + packageConvertPath(Package.PACKAGE_CONTROLLER), fileName, data);
    }

    private static void genFtl(String ftl, String filePath, String fileName, Map<String, Object> data) {
        try {
            File file = new File(filePath, fileName);
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

    private static Map<String, Object> initData(Param param) {
        String model = tableNameConvertUpperCamel(param.tableName);

        Map<String, Object> data = new HashMap<>(20);
        data.put("author", Note.AUTHOR);
        data.put("date", Note.DATE);
        data.put("desc", param.desc);
        data.put("selectAll", param.selectAll);
        data.put("findPage", param.findPage);
        data.put("model", model);
        data.put("tableName", param.tableName);

        data.put("controllerPackage", Package.PACKAGE_CONTROLLER);
        data.put("servicePackage", Package.PACKAGE_SERVICE_IMPL);
        data.put("queryPackage", Package.PACKAGE_QUERY);
        data.put("mapperPackage", Package.PACKAGE_MAPPER);
        data.put("entityPackage", Package.PACKAGE_ENTITY);
        data.put("queryPackage", Package.PACKAGE_QUERY);

        data.put("controllerSuffix", Suffix.CONTROLLER);
        data.put("serviceSuffix", Suffix.SERVICEIMPL);
        data.put("mapperSuffix", Suffix.MAPPER);
        data.put("querySuffix", Suffix.QUERY);
        data.put("entitySuffix", Suffix.ENTITY);
        data.put("xmlSuffix", Suffix.XML);
        return data;
    }

    private static Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setDirectoryForTemplateLoading(new File(Path.TEMPLATE_FILE_PATH));
            cfg.setDefaultEncoding(CHARSET);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        } catch (IOException e) {
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

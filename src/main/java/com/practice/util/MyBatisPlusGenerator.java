package com.practice.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * 利用mp+freemarker自动生成api/service/mapper/entity/xml文件.
 *
 * @author kexin.ding
 */
@SuppressWarnings("ALL")
public class MyBatisPlusGenerator {
    /**
     * JDBC配置
     */
    private static class Jdbc {
        private static final DbType DB_TYPE = DbType.MYSQL;
        private static final String DIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "1234";
    }

    /**
     * 路径
     */
    private static class Path {
        private static final String PROJECT_PATH = System.getProperty("user.dir");
        private static final String JAVA_PATH = "src/main/java";
        private static final String RESOURCE_PATH = "src/main/resources";
        private static final String MAPPER_PATH = RESOURCE_PATH + "/mapper";
    }

    /**
     * 包
     */
    private static class Package {
        private static final String PARENT_PACKAGE = "com";
        private static final String MODULE_NAME = "test";
        private static final String CONTROLLER_PACKAGE = "controller";
        private static final String SERVICE_PACKAGE = "service";
        private static final String SERVICE_IMPL_PACKAGE = "service.impl";
        private static final String MAPPER_PACKAGE = "dao";
        private static final String ENTITY_PACKAGE = "bean.entity";
        private static final String QUERY_PACKAGE = "bean.query";
    }

    /**
     * 文件名称后缀
     */
    private static class Name {
        private static final String XML = "%sMapper";
        private static final String ENTITY = "%s";
        private static final String QUERY = "%sQuery";
        private static final String MAPPER = "%sMapper";
        private static final String SERVICEIMPL = "%sService";
        private static final String CONTROLLER = "%sApi";
    }

    /**
     * 要生成的表名
     */
    private static final String TABLE = "DEPT";
    private static final String AUTHOR = "dkx";
    private static final TableFill[] TABLE_FILLS = {
            new TableFill("create_date", FieldFill.INSERT),
            new TableFill("update_date", FieldFill.INSERT_UPDATE),
    };

    public static void main(String[] args) {
        genCode();
    }

    public static void genCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        /**
         * 指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
         */
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        /**
         * 模板配置
         */
        mpg.setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig()
                        .setXml(null)
                        .setService(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用copy至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // 不想生成下面哪个，放开注释设置为null即可。
                //.setController(null)
                //.setEntity(null)
                //.setMapper(null)
                //.setServiceImpl(null)
        );

        /**
         * 注入自定义配置
         */
        // 注入自定义配置，可以在模板中使用cfg.queryPackage 设置的值
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("queryPackage", mpg.getPackageInfo().getParent() + "." + Package.QUERY_PACKAGE);
                map.put("querySuffix", String.format(Name.QUERY, ""));
                //是否查询列表
                map.put("selectAll", true);
                //是否分页查询
                map.put("findPage", true);
                this.setMap(map);
            }
        };
        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.add(new FileOutConfig("/templates/query.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                System.out.println(tableInfo);
                //tableInfo.setImportPackages("com.practice.bean.query.Sort");
                return Path.PROJECT_PATH + "/" + Path.JAVA_PATH + packageConvertPath(mpg.getPackageInfo().getParent() + "/" + Package.QUERY_PACKAGE) + String.format(Name.QUERY, tableInfo.getEntityName()) + StringPool.DOT_JAVA;
            }
        });
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                tableInfo.setName(TABLE);
                return Path.PROJECT_PATH + "/" + Path.MAPPER_PATH + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        ic.setFileOutConfigList(fileOutList);
        mpg.setCfg(ic);

        /**
         * 全局配置
         */
        mpg.setGlobalConfig(new GlobalConfig()
                //输出目录
                .setOutputDir(Path.PROJECT_PATH + "/" + Path.JAVA_PATH)
                // 是否覆盖文件
                .setFileOverride(true)
                // 开启 activeRecord 模式
                //.setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(false)
                // XML columList
                .setBaseColumnList(false)
                //生成后打开文件夹
                .setOpen(false)
                .setSwagger2(true)
                .setAuthor(AUTHOR)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName(Name.MAPPER)
                //.setServiceName("%sService")
                .setXmlName(Name.XML)
                .setServiceImplName(Name.SERVICEIMPL)
                .setControllerName(Name.CONTROLLER)
        );

        /**
         * 数据库配置
         */
        mpg.setDataSource(new DataSourceConfig()
                .setDbType(Jdbc.DB_TYPE)
                .setDriverName(Jdbc.DIVER_CLASS_NAME)
                .setUrl(Jdbc.URL)
                .setUsername(Jdbc.USERNAME)
                .setPassword(Jdbc.PASSWORD)
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                        if (fieldType.toLowerCase().contains("datetime")) {
                            return DbColumnType.DATE_SQL;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                }));

        /**
         * 包配置
         */
        mpg.setPackageInfo(new PackageConfig()
                //模块名
                .setModuleName(Package.MODULE_NAME)
                // 自定义包路径
                .setParent(Package.PARENT_PACKAGE)
                // 这里是控制器包名，默认 web
                .setController(Package.CONTROLLER_PACKAGE)
                .setEntity(Package.ENTITY_PACKAGE)
                .setMapper(Package.MAPPER_PACKAGE)
                .setService("service")
                .setServiceImpl(Package.SERVICE_IMPL_PACKAGE)
                .setXml("mapper")
        );

        /**
         * 策略配置
         */
        mpg.setStrategy(new StrategyConfig()
                // 全局大写命名
                // .setCapitalMode(true)
                // 全局下划线命名
                //.setDbColumnUnderline(true)
                // 此处可以修改为您的表前缀
                //.setTablePrefix(new String[]{"t_"})
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(TABLE)
                .setRestControllerStyle(true)
                // 排除生成的表
                //.setExclude(new String[]{"test"})
                // 自定义实体父类
                // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                // 自定义实体，公共字段
                //.setSuperEntityColumns("id","create_time","create_user","update_time","update_user")
                .setTableFillList(Arrays.asList(TABLE_FILLS))
                // 自定义 mapper 父类 默认BaseMapper
                .setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
                // 自定义 service 父类 默认IService
                // .setSuperServiceClass("com.baomidou.demo.TestService")
                // 自定义 service 实现类父类 默认ServiceImpl
                .setSuperServiceImplClass("com.practice.service.BaseService")
                // 自定义 controller 父类
                //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")
                // 【实体】是否生成字段常量（默认 false）
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）
                .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                //.setLogicDeleteFieldName("is_delete")
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(false)
        );

        // 执行生成
        mpg.execute();
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}

package com.yjs.cloud.learning.member.common.util.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.yjs.cloud.learning.member.common.entity.BaseEntity;
import com.yjs.cloud.learning.member.common.util.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;
import java.util.Scanner;

/**
 * Mybatis Plus代码生成器
 *
 * @author Bill.lai
 * @since 2020/5/28
 */
public class CodeGenerator {

    /**
     * 读取控制台内容
     * @param tip 提示
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String serviceDirName = scanner("服务文件夹名称");
        String projectPath = System.getProperty("user.dir") + "/" + serviceDirName;
        String outputDir = projectPath + "/src/main/java";
        gc.setOutputDir(outputDir);
        gc.setAuthor("bill.lai");
        gc.setOpen(false);

        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        String dbName = scanner("数据库名称");
        dsc.setUrl("jdbc:mysql://localhost:3306/" + dbName);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        String serviceName = scanner("服务包名");
        String basePackage = "com.yjs.cloud.learning";
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("生成模块的包名"));
        pc.setParent(basePackage + "." + serviceName + ".biz");
        pc.setController("web");
        pc.setService("service");
        pc.setServiceImpl("service");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperMapperClass(basePackage + ".common.mapper.IBaseMapper");
        strategy.setSuperServiceClass(basePackage + ".common.service.IBaseService");
        strategy.setSuperServiceImplClass(basePackage + ".common.service.BaseServiceImpl");
        // Entity公共父类
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setEntityLombokModel(true);
        // Controller公共父类
        strategy.setSuperControllerClass(basePackage + ".common.controller.BaseController");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id", "create_time", "update_time");
        // 表名前缀
        strategy.setTablePrefix("t_");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static String getPropertiesValue(String key){
        Properties properties;
        try {
            Resource resource = new ClassPathResource("application.yml");
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
            if (properties == null) {
                return null;
            }
            String profilesSuffix = properties.getProperty("spring.profiles.active");
            if (profilesSuffix == null) {
                return null;
            }

            resource = new ClassPathResource("application-" + profilesSuffix + ".yml");
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
            if (properties != null) {
                return properties.getProperty(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

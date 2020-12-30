package com.licc.cat;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: 李臣臣
 * @Date: 2020/01/06 0006 14:21
 * @Description: 生成实体类
 */
@SpringBootTest
public class MybatisplusTest {


    //项目路径
    private static String canonicalPath = System.getProperty("user.dir");

    private static String outputDir = "E:\\mybatisplustest";
    private static String author = "李臣臣";

    //基本包名
    private static String basePackage = "com.licc.cat";

    //要生成的表名
    private static String[] tables = {"attaches", "cat_dynamic", "location_info", "pet_info", "user_cat_relation", "user_info", "user_relation", "variety_dic"};
    //数据库配置四要素
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://47.101.11.64:33310/db_pet?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT";
    private static String username = "root";
    private static String password = "123456";


    @Test
    public void MybatisPlusGenerator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);

        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 自定义公共实体父类
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);

        strategy.setInclude(tables);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}

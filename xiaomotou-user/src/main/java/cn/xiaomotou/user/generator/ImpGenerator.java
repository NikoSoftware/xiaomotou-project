package cn.xiaomotou.user.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

public class ImpGenerator {


    public static void main(String[] args)
    {

        DataSourceConfig.Builder resource = new DataSourceConfig
                .Builder("jdbc:mysql://127.0.0.1:3306/poetry?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8", "root", "123456");


        FastAutoGenerator.create(resource)
                // 全局配置
                //	.globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称")))
                .globalConfig((scanner, builder) ->
                        builder.outputDir("/Users/niko/IdeaProjects/xiaomotou-project/xiaomotou-user/src/main/java/")
                                .author(scanner.apply("请输入作者名称"))
                )
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("cn.xiaomotou.user"))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名，多个表名用,隔开")))
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

}

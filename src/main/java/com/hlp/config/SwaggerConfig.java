package com.hlp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 韩帅比
 * @create 2022-06-27 20:51
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        /**
         * 创建API应用
         * apiInfo() 增加API相关信息
         * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
         * 本例采用指定扫描的包路径来定义指定要建立API的目录。
         *
         * @return
         */
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准接口")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hlp.controller"))//配置控制层位置
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://ip:port/swagger-ui/index.html#/
     *
     */
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("zjyt","https://gitee.com/zjyt", "1103953171@qq.com");
        return new ApiInfo(
                "zjyt的SwaggerAPI文档",
                "后台管理项目API",
                "1.0.0",
                "https://gitee.com/zjyt",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}

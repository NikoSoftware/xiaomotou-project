package cn.xiaomotou.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class JsonConfig {



        @Bean
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
            FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
            FastJsonConfig config = new FastJsonConfig();

            config.setCharset(StandardCharsets.UTF_8);
            config.setSerializerFeatures(

                    //输出map中value为null的数据
                    SerializerFeature.WriteMapNullValue,
                    //json格式化
                    SerializerFeature.PrettyFormat,
                    //输出空list为[]，而不是null
                    SerializerFeature.WriteNullListAsEmpty
                    //输出空string为""，而不是null
                  //  SerializerFeature.WriteNullStringAsEmpty
                //    SerializerFeature.WriteEnumUsingToString
            );
            converter.setFastJsonConfig(config);
            return converter;
        }
}

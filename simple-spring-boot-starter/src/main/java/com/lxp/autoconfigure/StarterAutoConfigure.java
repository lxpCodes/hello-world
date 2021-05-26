package com.lxp.autoconfigure;

import com.lxp.myself.StartService;
import com.lxp.myself.StarterServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName StarterAutoConfigure
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/8 16:10
 **/
@Configuration
@ConditionalOnClass(StartService.class)
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {

    @Autowired
    private StarterServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "com.lxp.myself", value = "enabled", havingValue = "true")
    StartService startService(){
        return new StartService(properties.getConfig());
    }
}

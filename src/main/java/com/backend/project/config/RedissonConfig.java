package com.backend.project.config;


import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//1-从application.yml文件中读取前缀为“spring.redis”的配置项
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {
    private Integer database;

    private String host;

    private Integer port;

    //如果redis设置了密码，则写上
    //private String password;

    @Bean
    //2-spring启动时，会自动创建一个RedissonClient对象。Redission依赖提供的。
    public RedissonClient redissonClient() {
        //3-创建配置对象
        Config config = new Config();
        config.useSingleServer()
                //设置数据库
                .setDatabase(database)
                //设置redis地址
                .setAddress("redis://" + host + ":" + port);
                //设置redis密码
                //.setPassword(password);
        //4-创建reddission实例
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

}

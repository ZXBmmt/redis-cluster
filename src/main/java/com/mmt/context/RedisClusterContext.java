package com.mmt.context;

import com.mmt.config.ClusterConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisClusterContext {
    @Autowired
    private ClusterConfigurationProperties clusterConfigurationProperties;

    @Bean
    public RedisClusterConfiguration getClusterConfig() {
        RedisClusterConfiguration rcc = new RedisClusterConfiguration(clusterConfigurationProperties.getNodes());
        rcc.setMaxRedirects(clusterConfigurationProperties.getMaxRedirects());
        return rcc;
    }

    @Bean
    public JedisConnectionFactory getConnectionFactory(RedisClusterConfiguration cluster) {
        return new JedisConnectionFactory(cluster);
    }

    @Bean
    public StringRedisTemplate getStringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringTemplate = new StringRedisTemplate();
        stringTemplate.setConnectionFactory(factory);
        return stringTemplate;
    }

}

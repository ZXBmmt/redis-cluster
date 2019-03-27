package com.mmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class SpringbootRedisClusterSample {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    public Object test(){
        for(int i=0;i<100000;i++){
            String key= i+"";
            try {
                redisTemplate.opsForValue().set(key, key + "-" + key);
                String value = redisTemplate.opsForValue().get(key);
                System.out.println(value);
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       return null;
    }

    public static void main(String[] args){
        SpringApplication.run(SpringbootRedisClusterSample.class, args).close();
    }
}

package com.mmt;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterSample {

    public static void main(String[] args){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7000));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7001));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7002));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7003));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7004));
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7005));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        for(int i=0;i<100000;i++){
            String key= i+"";
            try{
                jc.set(key, key);
                String value = jc.get(key);
                System.out.println(value);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

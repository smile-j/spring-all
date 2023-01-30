package com.dong.demo.config;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author Oneby
 * @Date 2021/2/2 18:55
 * @Version 1.0
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        // 新建 RedisTemplate 对象，key 为 String 对象，value 为 Serializable（可序列化的）对象
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        // key 值使用字符串序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value 值使用 json 序列化器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 传入连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 返回 redisTemplate 对象
        return redisTemplate;
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://r-wewewew2323.redis.rds.aliyuncs.com:6379");
        singleServerConfig.setPassword("&q&ewewe");


        RedissonClient client = Redisson.create(config);
        RKeys keys = client.getKeys();
        Iterable<String> keys1 = keys.getKeys();
        keys1.forEach(e->{
            System.out.println("key:"+e);
        });
//        RedissonBloomFilter bloomFilter2 = (RedissonBloomFilter)client.getBloomFilter("KEY2");
        RBloomFilter<Object> bloomFilter2 = client.getBloomFilter("addressList");
        //RBloomFilter
        bloomFilter2.tryInit(100, 0.01);
        bloomFilter2.add("123");
        System.out.println("count --->"+bloomFilter2.contains("D"));
        System.out.println("count --->"+bloomFilter2.contains("123"));
        System.out.println("---->"+bloomFilter2.count());
        try{
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            test1(jedis,"BF.EXISTS KEY1 A");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void test1(Jedis jedis, String command, String... args) throws InvocationTargetException, IllegalAccessException {
        Protocol.Command cmd = Protocol.Command.valueOf(command.toUpperCase());
        Client client = jedis.getClient();
        Method method = MethodUtils.getMatchingMethod(Client.class, "sendCommand", Protocol.Command.class, String[].class);
        method.setAccessible(true);
        Object invoke = method.invoke(client, cmd, args);
//        System.out.println(JSONObject.toJSONString(invoke));
    }

    private void pipeSaveRedis(String key, List<String> list, RedisTemplate<String, Object> stringListRedisTemplate) {
        Random random = new Random();
        stringListRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                for (String dto : list) {
                    redisConnection.lPush(key.getBytes(), (dto + "_" + random.nextInt(4)).getBytes());
                }
                return null;
            }
        });
    }
}

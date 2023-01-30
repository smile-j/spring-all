package com.dong.demo.controller;


import com.dong.demo.util.ModuleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestBloomFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test")//test01
    public Boolean test () {
        redisTemplate.opsForValue().set("aa","bb");
       return true;
    }
    @GetMapping("/testLua")//test01
    public Object testLua () {
//        String script = "if redis.call('exists', KEYS[1]) == 1 then return redis.call('hset', KEYS[1],KEYS[2],KEYS[3]) else return -1 end";
        String script = "redis.call('hset', KEYS[1],KEYS[2],KEYS[3]) ";
        DefaultRedisScript redisScript = new DefaultRedisScript(script);
        redisScript.setResultType(Object.class);
        ArrayList keys = new ArrayList<>();
        keys.add("test111");
        keys.add("name");
        keys.add("abc");

        Object result = redisTemplate.execute(redisScript,keys);
        return result;
    }
    @GetMapping("/initBloomFilterLua2")//test01
    public Object initBloomFilterLua2 (@RequestParam String filterName) {
//        DefaultRedisScript<Boolean> luaScript = new DefaultRedisScript<>();
//        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/bf_create_bf.lua")));

        String script = "redis.call('BF.RESERVE', {KEYS[1],KEYS[2],KEYS[3]}') ";
        DefaultRedisScript luaScript = new DefaultRedisScript(script);
        luaScript.setResultType(Object.class);
        //封装传递脚本参数
        List<String> params = new ArrayList<>();
        params.add(filterName);
        params.add("0.01");
        params.add("100");

        return redisTemplate.execute(luaScript,params);
    }

    @GetMapping("/initBloomFilterLua")//test01
    public Object initBloomFilterLua (@RequestParam String filterName) {
//        DefaultRedisScript<Boolean> luaScript = new DefaultRedisScript<>();
//        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/bf_create_bf.lua")));

        String script = "redis.call('BF.RESERVE', KEYS[1],'0.01','100' }) ";
        DefaultRedisScript luaScript = new DefaultRedisScript(script);
        luaScript.setResultType(Object.class);
        //封装传递脚本参数
        List<Object> params = new ArrayList<>();
        params.add(filterName);
        params.add("100");
        params.add("0.01");
        return redisTemplate.execute(luaScript,params);
    }

    @GetMapping("/addBloomFilterLua")
    public Object bloomFilterLua (@RequestParam Integer id) {
        DefaultRedisScript<Boolean> luaScript = new DefaultRedisScript<>();
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/bf_add.lua")));
        luaScript.setResultType(Boolean.class);
        //封装传递脚本参数
        List<String> params = new ArrayList<>();
        params.add("{test02}");
        params.add(id.toString());
        return redisTemplate.execute(luaScript,params);
    }

    @GetMapping("/exitBloomFilterLua")
    public Object exitBloomFilterLua (@RequestParam Integer id) {
        DefaultRedisScript<Boolean> LuaScript = new DefaultRedisScript<>();
        LuaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/bf_exist.lua")));
        LuaScript.setResultType(Boolean.class);
        //封装传递脚本参数
        ArrayList<String> params = new ArrayList<>();
        params.add("script/bloom-filter-test");
        params.add(String.valueOf(id));
        return  redisTemplate.execute(LuaScript, params);
    }

    private void testCmd(){

    }

    //jedis 操作 redis 脚本
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.sendCommand(ModuleCommand.AVGADD,"key1".getBytes(),"".getBytes(),"".getBytes());
    }

}

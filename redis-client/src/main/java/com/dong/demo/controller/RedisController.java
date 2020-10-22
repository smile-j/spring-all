package com.dong.demo.controller;

import com.dong.demo.conf.RedisUtil;
import com.dong.demo.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("set")
    public boolean redisObj(String key, String value) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        UserEntity userEntity =new UserEntity();
        userEntity.setId(Long.valueOf(1));
        userEntity.setName("zhangsan");
        userEntity.setAdd(value);
        userEntity.setAge(20);
        userEntity.setBorn(new Date());
        log.info("****************,{}",om.writeValueAsString(userEntity));
        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisGet(String key){
        Object o = redisUtil.get(key);
        log.info("-----"+o);
        return o;
    }



    /**
     * String 操作
     * @param key
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("string")
    public Object redisSet(String key, String value) throws JsonProcessingException {
        //添加
        redisTemplate.opsForValue().set(key, value);
        //添加并设置过期时间
        redisTemplate.opsForValue().set("kak","4552",7200, TimeUnit.SECONDS);
        //自增
        Long resLong = redisTemplate.opsForValue().increment("user_count",1);
        log.info("***resLong**  "+resLong);
        //获取重新设置value
        Object oldVaule  = redisTemplate.opsForValue().getAndSet("kak","hello");
        log.info("***oldVaule**  "+oldVaule);
        //追加到字符串的末尾
        Integer s  = redisTemplate.opsForValue().append("kak","world");
        log.info("***s**  "+s);
        //获取字符长度
        Long strLong  = redisTemplate.opsForValue().size("kak");
        log.info("***strLong**  "+strLong);
//        redisUtil.set("","");
        return redisTemplate.opsForValue().get(key);

    }

    /***
     * hash操作
     * @return
     */
    @GetMapping("/hash")
    public Map<Object, Object> hashRedis(){
        //添加单个
        redisTemplate.opsForHash().put("user","id","001");
        //点加多个
        Map<String,Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("sex","男");
        map.put("age","20");
        redisTemplate.opsForHash().putAll("user",map);

        //键集合
        Set<Object> keys =  redisTemplate.opsForHash().keys("user");
        System.out.println("keys:"+keys);
        //value集合
        List<Object> values =  redisTemplate.opsForHash().values("user");
        System.out.println("values:"+values);
        //遍历map
        Cursor<Map.Entry<Object,Object>> entryCursor  = redisTemplate.opsForHash().scan("user", ScanOptions.NONE);
        while (entryCursor.hasNext()){
            Map.Entry<Object,Object> entry =entryCursor.next();
            System.out.println("键："+entry.getKey()+"值："+entry.getValue());
        }
        //获取整个map
        return redisTemplate.opsForHash().entries("user");
    }


    /**
     * list操作
     * @return
     */
    @GetMapping("/list")
    public List<String> listRedis(){
        //表头插入单个
        redisTemplate.opsForList().leftPush("left-list","java");
        //表头插入多个
        String [] arr = new String[]{"js","html","c#","C++"};
        redisTemplate.opsForList().leftPushAll("left-list",arr);
        //表尾插入单个
        redisTemplate.opsForList().rightPush("rught-list","java");
        //表尾插入多个
        redisTemplate.opsForList().rightPushAll("rught-list",arr);
        //设置位置
        redisTemplate.opsForList().set("rught-list",0,"第一个");
        //删除:count> 0：删除等于从头到尾移动的值的元素。count <0：删除等于从尾到头移动的值的元素。count = 0：删除等于value的所有元素。
        redisTemplate.opsForList().remove("rught-list",1,"js");//

        return  redisTemplate.opsForList().range("left-list",0,100);
    }

    /**
     * set操作
     * @param key
     * @return
     */
    @GetMapping("/set")
    public Set<String> setRedis(@RequestParam(required = false) String key){
        String lkl = key == null ?"tel":key;
        //添加一个或者多个
        String[] ste = new String[]{"123","456","789","45","6"};
        redisTemplate.opsForSet().add(lkl,ste);
        //移除一个或多个
        ste= new String[]{"123"};
        redisTemplate.opsForSet().remove(lkl,ste);
        //遍历
        Cursor<String> cursor = redisTemplate.opsForSet().scan(lkl,ScanOptions.NONE);
        while (cursor.hasNext()){
            System.out.println("set成员元素："+cursor.next());
        }
        //获取所有元素
        return  redisTemplate.opsForSet().members(key);
    }


    @GetMapping("/zset")
    public Object Zset(){
        System.out.println(redisTemplate.opsForZSet().add("zset1","zset-1",1.0));
        //新增一个有序集合
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        //从有序集合中移除一个或者多个元素
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().remove("zset1","zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        // Long rank(K key, Object o);
        // 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().rank("zset1","zset-2"));
        // [zset-2, zset-1, zset-3, zset-4, zset-5]
        // 0   //表明排名第一

        return "";
    }
}
